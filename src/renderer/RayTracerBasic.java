package renderer;

import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * DELTA - size of first moving rays for shading rays
 * MAX_CALC_COLOR_LEVEL - const for the stopping condition in the recursion of transparency and reflection
 * MIN_CALC_COLOR_K - const for the stopping condition in the recursion of transparency and reflection
 * INITIAL_K -
 */
public class RayTracerBasic extends RayTracerBase {
    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;

    /**
     * constructor
     *
     * @param scene - the scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * @param ray - the ray that pass in the middle of the pixel
     * @return - the color of the pixel
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }


    /**
     * @param closestPoint -
     * @param ray          -
     * @return
     */
    private Color calcColor(GeoPoint closestPoint, Ray ray) {
        return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * @param intersection -
     * @param ray          -
     * @param level        -
     * @param k            -
     * @return
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, k));

        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));
    }

    /**
     * @param intersection -
     * @param ray          -
     * @param k            -
     * @return
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));

        if (nv == 0)
            return Color.BLACK;

        Material material = intersection.geometry.getMaterial();
        int nShininess = material.nShininess;
        double kd = material.kD, ks = material.kS;
        Color color = Color.BLACK;

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));

            if (nl * nv > 0) {
                double ktr = transparency(lightSource, l, n, intersection);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }

        return color;
    }

    /**
     * @param ks             - specular factor
     * @param l              - the direction vector of the light
     * @param n              - the normal to the geometry in the intersection point
     * @param v              - the direction vector of the camera
     * @param nShininess     - the shininess of the material the geometry is made of
     * @param lightIntensity - the intensity of the light
     * @return
     */
    public Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(2 * l.dotProduct(n)));
        double factor = -v.dotProduct(r);
        return lightIntensity.scale(ks * Math.pow(factor, nShininess));
    }

    /**
     * @param kd             - diffusion factor
     * @param l              - the direction vector of the light
     * @param n              - the normal to the geometry in the intersection point
     * @param lightIntensity - the intensity of the light
     * @return
     */
    public Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        double factor = kd * Math.abs(l.dotProduct(n));
        return lightIntensity.scale(factor);
    }

    /**
     * @param light    - a light source
     * @param l        - the direction vector of the light
     * @param n        - the normal to the geometry in the intersection point
     * @param geopoint -the point we want to check if is shaded or not
     * @return true if the point is unshaded, false if the point is shaded
     */
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n); // refactored ray head move
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        if (intersections == null)
            return true;

        double lightDistance = light.getDistance(geopoint.point);

        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0 &&
                    gp.geometry.getMaterial().kT == 0)
                return false;
        }

        return true;
    }

    /**
     * @param light    - a light source
     * @param l        - the direction vector of the light
     * @param n        - the normal to the geometry in the intersection point
     * @param geopoint -
     * @return
     */
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        double lightDistance = light.getDistance(geopoint.point);
        var intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return 1.0;
        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().kT;
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }
        }
        return ktr;
    }

    /**
     * @param gp    -
     * @param v     -
     * @param level -
     * @param k     -
     * @return
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.kR;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kR, kkr);
        double kkt = k * material.kT;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kT, kkt));
        return color;
    }

    /**
     * @param point -
     * @param v     -
     * @param n     -
     * @return
     */
    private Ray constructReflectedRay(Point3D point, Vector v, Vector n) {
        Vector r = v.subtract(n.scale(2 * v.dotProduct(n)));
        return new Ray(point, r, n);
    }

    /**
     * @param point -
     * @param v     -
     * @param n     -
     * @return
     */
    private Ray constructRefractedRay(Point3D point, Vector v, Vector n) {
        return new Ray(point, v, n); // direction of r = direction of v, just the beginning point is different
    }

    /**
     * @param ray   -
     * @param level -
     * @param kx    -
     * @param kkx   -
     * @return
     */
    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection(ray);

        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx))
                .scale(kx);
    }

    /**
     * @param ray - the ray with we want to find intersections
     * @return
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        var points = scene.geometries.findGeoIntersections(ray);

        if (points == null)
            return null;

        return ray.findClosestGeoPoint(points);
    }
}