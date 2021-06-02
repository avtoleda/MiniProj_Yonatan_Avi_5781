package renderer;
import elements.LightSource;
import geometries.Geometry;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
    private static final double DELTA = 0.1;

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        var intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            //ray didn't intersect any geometrical object
            return scene.background;
        //else
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcLocalEffects(closestPoint, ray);
    }


   /* private Color calcColor(GeoPoint intersection, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(intersection.geometry.getEmission())
// add calculated light contribution from all light sources)
                .add(calcLocalEffects(intersection, ray));
    }*/

    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir ();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        Material material = intersection.geometry.getM();
        int nShininess = material.nShininess;
        double kd = material.kD, ks = material.kS;
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if(nl * nv > 0 && unshaded(lightSource, l, n, intersection)) {//if (nl * nv > 0) {
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }//refractored calcolor so ad this calculation instead
        return color.add(intersection.geometry.getEmission(),scene.ambientLight.getIntensity());
    }


    public Color calcSpecular(double ks,Vector l,Vector n,Vector v,int nShininess,Color lightIntensity){
        Vector r = l.subtract(n.scale(2 * l.dotProduct(n)));
        double factor =  -v.dotProduct(r);
        return lightIntensity.scale(ks * Math.pow(factor,nShininess));
    }

    public Color calcDiffusive(double kd,Vector l,Vector n,Color lightIntensity){
        double factor=kd*Math.abs(l.dotProduct(n));
        return lightIntensity.scale(factor);
    }

    private boolean unshaded(LightSource lightSource, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n, DELTA);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        if (intersections == null)
            return true;

        double lightDistance = lightSource.getDistance(geopoint.point);

        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0)
                return false;
        }

        return true;
    }
}