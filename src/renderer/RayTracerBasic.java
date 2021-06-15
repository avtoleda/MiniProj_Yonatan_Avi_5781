package renderer;
import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
    private static int WITH_SUPER_STUFF = 1;
    private static int AMM_OF_RAYS = 20;
    private static double RADIUS = 10;
    private static double MULTI_RAY_DEFAULT_DISTANCE = 50;

    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }


    private Color calcColor(GeoPoint closestPoint, Ray ray) {
        /*if(WITH_SUPER_STUFF==1)
            return calcCoolGolss(ray,RADIUS,AMM_OF_RAYS,closestPoint);*/
        return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, k));

        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));
    }

    private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k) {
        Vector v = ray.getDir ();
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

            if(nl * nv > 0) {
                double ktr = transparency(lightSource, l, n, intersection);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }

        return  color;
    }

    public Color calcSpecular(double ks,Vector l,Vector n,Vector v,int nShininess,Color lightIntensity){
        Vector r = l.subtract(n.scale(2 * l.dotProduct(n)));
        double factor =/* Math.abs(v.dotProduct(r));*/ -v.dotProduct(r);
        return lightIntensity.scale(ks * Math.pow(factor,nShininess));
    }

    public Color calcDiffusive(double kd,Vector l,Vector n,Color lightIntensity){
        double factor=kd*Math.abs(l.dotProduct(n));
        return lightIntensity.scale(factor);
    }

    /**
     * calcs the color for each ray and calculates the average
     * it gets a ray and it uses the global radius to send off to get the list of rays
     * then for each one it determines its color and adds it to factor which calculates an average
     * we also get a bunch of things to pass on
     * @param r the original ray
     * @return
     */
    //(constructReflectedRay(gp.point, v, n), level, material.kR, kkr);
    public Color CalcDiffGloEff(Ray r, int level, double kX, double kkx) {
        List<Ray> vecList = multVecs(r,RADIUS);
        Color factor=Color.BLACK;
        for (Ray v:vecList) {
            factor = factor.add(calcGlobalEffect(r,level, kX, kkx));
        }

        return factor.reduce(AMM_OF_RAYS +1)/*.add(scene.ambientLight.getIntensity())*/;
    }
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
     * multVecs
     * we make a list of rays that start at the rays start and they finish in a circle
     * we get a ray and we find its first intesection if it doesnt exist we make it a point far away
     * we then make a perpandicular vec and run in a loop
     * each time creating a ray from our starting point to the edge of the ray which rotates around our main ray
     * and we make its length be equal to the radius then we add the original ray
     * we then send back the list of all the rays
     * @param orig
     * @param radius
     * @return a list of rays
     */
    private List<Ray> multVecs(Ray orig, double radius){
        orig.getDir().normalize();
        List<Ray> rays= new LinkedList<>();
        Point3D origIntersection = findClosestIntersection(orig)==null?
                orig.getP0().add(orig.getDir().scale(MULTI_RAY_DEFAULT_DISTANCE)) : findClosestIntersection(orig).point;
       // if(origIntersection==null)
         //   origIntersection=
       // Vector origExtended = orig.getDir().normalized().scale(origIntersection == null? MULTI_RAY_DEFAULT_DISTANCE : origIntersection.point.distance(orig.getP0()));
        //        //Point3D origIntersection = findClosestIntersection(orig) == .point;
        Vector ort=  perVec(orig.getDir()).normalize().scale(radius);/*new Vector(origIntersection == null ? orig.getP0()/*.add(origExtended) : origIntersection.point,*/
       // ort.getDir().normalize();
        for(int i=0 ; i<AMM_OF_RAYS ; i++){
            ort = ort.RotateByRadians(orig.getDir(),(2*Math.PI)/(double)AMM_OF_RAYS);
            //rays.add(new Ray(orig.getP0(),orig.getDir().add(ort.getDir()).normalize()));
            rays.add(new Ray(orig.getP0(), origIntersection.add(ort).subtract(orig.getP0()).normalize()));
        }
        rays.add(orig);
        return rays;
    }

    /**
     * perVec
     * gets a vector and returns a vector that is perpendicular to the vec
     * ie that their dot product equals 0 ;)
     * @param v
     * @return
     */
    private Vector perVec(Vector v){
        Vector perVec;
        //if the two first coordinates equal 0 so we cant make perVec equals (y,-x,0)
        // because we'll get the vec 0 which doesnt exist so we make it (z,0,-x)
        //else we do (y,-x,0)
        //same for the last one we just dont want to get to (0,0,0)
        if (v.getHead().getX().getCoord()==0 && v.getHead().getY().getCoord()==0){
            perVec=new Vector(new Point3D(v.getHead().getZ().getCoord(),0,-v.getHead().getX().getCoord()));
        }
        else if(v.getHead().getY().getCoord()==0 && v.getHead().getZ().getCoord()==0)
            perVec=new Vector(new Point3D(v.getHead().getY().getCoord(),-v.getHead().getX().getCoord(),0));
        else
            perVec=new Vector(new Point3D(0,v.getHead().getZ().getCoord(),-v.getHead().getY().getCoord()));

        return perVec;
    }


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


    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.kR;
        if (kkr > MIN_CALC_COLOR_K){
            //if we are using our addition
        if(WITH_SUPER_STUFF==1)
            color = CalcDiffGloEff(constructReflectedRay(gp.point, v, n), level, material.kR, kkr);
        else
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kR, kkr);

        }

        double kkt = k * material.kT;
        if (kkt > MIN_CALC_COLOR_K){
            //if we are using our addition
            if(WITH_SUPER_STUFF==1)
            color = color.add(
                    CalcDiffGloEff(constructRefractedRay(gp.point, v, n), level, material.kT, kkt));
            else
                color = color.add(calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kT, kkt));

        }
        return color;
    }

    private Ray constructReflectedRay(Point3D point, Vector v, Vector n) {
        Vector r = v.subtract(n.scale(2 * v.dotProduct(n)));
        return new Ray(point, r, n);
    }

    private Ray constructRefractedRay(Point3D point, Vector v, Vector n) {
        return new Ray(point, v, n); // direction of r = direction of v, just the beginning point is different
    }

    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection (ray);

        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }

    private GeoPoint findClosestIntersection(Ray ray) {
        var points = scene.geometries.findGeoIntersections(ray);

        if(points == null)
            return null;

        return ray.findClosestGeoPoint(points);
    }
}