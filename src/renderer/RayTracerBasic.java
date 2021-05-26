package renderer;
import elements.LightSource;
import geometries.Geometry;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
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
        return calcLocalEffects(closestPoint,ray);
    }


    /*private Color calcColor(GeoPoint intersection) {
        return scene.ambientLight.getIntensity()
                .add(intersection.geometry.getEmission());
    }*/
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir ();

        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        Material material = intersection.geometry.getM();
        int nShininess = material.getnShininess();
        double kd = material.getkD(), ks = material.getkS();
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }
    public Color calcSpecular(double ks,Vector l,Vector n,Vector v,int nShininess,Color lightIntensity){
        Vector r=n.scale(l.dotProduct(n)*-2);
        double factor=v.dotProduct(r)*-1;
        return lightIntensity.scale(ks*Math.pow(Math.max(0,factor),nShininess));
    }

    public Color calcDiffusive(double kd,Vector l,Vector n,Color lightIntensity){
        double factor=kd*Math.abs(l.dotProduct(n));
        return lightIntensity.scale(factor);
    }
}
