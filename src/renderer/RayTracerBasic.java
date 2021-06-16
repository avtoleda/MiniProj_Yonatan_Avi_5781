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
   // private int raysperpix=0;
   // private static int AMM_OF_RAYS = 8;
   // private static double RADIUS = 8;
   // private static double MULTI_RAY_DEFAULT_DISTANCE = 50;

    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;

    /**
     * sends to the constructor of scene
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * traces the ray to the closest point and then calculates the color
     * @param ray - the ray that pass in the middle of the pixel
     * @return the color of the ray
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    /**
     * sends us to the "real calculation and ands the ambient light
     * @param closestPoint-first intersection for the ray
     * @param ray-the ray we are working with
     * @return-the color from the next func+the ambient lighting
     */
    private Color calcColor(GeoPoint closestPoint, Ray ray) {

        return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * we calculate more of the color we add the emission of the point and its local affects then
     * if there are more levels to go down to, we go down to the global effects
     * @param intersection-the first intersection
     * @param ray- the actual ray
     * @param level-the level we are at of the light bouncing
     * @param k- we dont care anymore
     * @return a color with the result of the func for the local things
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, k));

        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));
    }

    /**
     * here we calculate all the local things that will change the pixals color
     * @param intersection-the first intesection
     * @param ray-the ray
     * @param k- where we dont care anymore
     * @return-a color
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k) {
        Vector v = ray.getDir ();
        Vector n = intersection.geometry.getNormal(intersection.point);
        //avoid weird double errors
        double nv = alignZero(n.dotProduct(v));
        //checks if the ray's vector is parallel to the geometry
        if (nv == 0)
            return Color.BLACK;

        Material material = intersection.geometry.getMaterial();
        int nShininess = material.nShininess;
        double kd = material.kD, ks = material.kS;
        Color color = Color.BLACK;
        //run over all the light sources and add their affect on the pixels color
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            //check if the light id from the right side
            if(nl * nv > 0) {
                double ktr = transparency(lightSource, l, n, intersection);
                //if we still care about the change that it will make to our pixel
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }

        return  color;
    }

    /**
     * adds specular from a particular light source
     * @param ks- the variable that defines the amount of specular in an object
     * @param l- the vector that is from the light source to the object
     * @param n- the normal vector
     * @param v- the camera vector
     * @param nShininess-it changes the shininess so the bigger it is the smaller the specular dot will be
     * @param lightIntensity- the light intensity at the point
     * @return- a color that took in to account the specular from a specific object
     */
    public Color calcSpecular(double ks,Vector l,Vector n,Vector v,int nShininess,Color lightIntensity){
        Vector r = l.subtract(n.scale(2 * l.dotProduct(n)));
        double factor =/* Math.abs(v.dotProduct(r));*/ -v.dotProduct(r);
        return lightIntensity.scale(ks * Math.pow(factor,nShininess));
    }

    /**
     * adds deffusion to the pixel from a particular light
     * @param kd-the variable that defines the amount of diffuse in an object
     * @param l- the vector that is from the light source to the object
     * @param n- the normal vector
     * @param lightIntensity- the light intensity at the point
     * @return - the color with the deffusion from a specific light source
     */
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
    public Color CalcDiffGloEff(Ray r, int level, double kX, double kkx,double XRad) {
        List<Ray> vecList = multVecs(r,XRad);
        Color factor=Color.BLACK;
        for (Ray v:vecList) {
            factor = factor.add(calcGlobalEffect(v,level, kX, kkx));
        }
        return factor.reduce(scene.RayAmount+1);
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
        if(findClosestIntersection(orig)!=null){
            Point3D origIntersection = findClosestIntersection(orig).point;
            Vector ort=  perVec(orig.getDir()).normalize().scale(radius);
            for(int i=0 ; i<scene.RayAmount ; i++){
                ort = ort.RotateByRadians(orig.getDir(),(2*Math.PI)/(double)scene.RayAmount);
                rays.add(new Ray(orig.getP0(), origIntersection.add(ort).subtract(orig.getP0()).normalize()));
            }
        }

        rays.add(orig);
        return rays;
    }

    /**
     *
     * @param light
     * @param l- the vector that is from the light source to the object
     * @param n- the normal vector
     * @param geopoint
     * @return
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
     * perVec
     * gets a vector and returns a vector that is perpendicular to the vec
     * ie that their dot product equals 0 ;)
     * @param v-the v we want to find a perp for
     * @return - the perp vec
     */
    private Vector perVec(Vector v){
        //Vector perVec;
            double x = v.getHead().getX().getCoord();
            double y = v.getHead().getY().getCoord();
            double z = v.getHead().getZ().getCoord();

            double xP, yP, zP;
            if (x == 0) {
                xP = 1;
                yP = z;
                zP = -y;
            }
            else if (y == 0) {
                yP = 1;
                xP = z;
                zP = -x;
            }
            else if (z == 0) {
                zP = 1;
                xP = y;
                yP = -x;
            }
            else {
                xP = 1;
                yP = 1;
                zP = -x * y / z;
            }
            return new Vector(xP, yP, zP);

    }

    /**
     * checks how transparent the pixel is
     * @param light- the light source we are dealing with
     * @param l- the vector that is from the light source to the object
     * @param n- the normal vector
     * @param geopoint- the point that we are dealing with
     * @return- the amount of transparency the point has
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
     * calculates the global effects on this pixel
     * @param gp- the point
     * @param v
     * @param level- the bouncing level we are at right now
     * @param k- the number that we count as negligble
     * @return-the color of the pixel with regard to its global effects
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.kR;
        double GRad=gp.geometry.getMaterial().GlossRadius;
        double DRad=gp.geometry.getMaterial().DeffRadius;

        if (kkr > MIN_CALC_COLOR_K){
            //if we are using our addition
        if(WITH_SUPER_STUFF==1)
            color = CalcDiffGloEff(constructReflectedRay(gp.point, v, n), level, material.kR, kkr,GRad);
        else
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kR, kkr);

        }

        double kkt = k * material.kT;
        if (kkt > MIN_CALC_COLOR_K){
            //if we are using our addition
            if(WITH_SUPER_STUFF==1)
            color = color.add(
                    CalcDiffGloEff(constructRefractedRay(gp.point, v, n), level, material.kT, kkt,DRad));
            else
                color = color.add(calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kT, kkt));

        }
        return color;
    }

    /**
     * costructs the reflected ray
     * @param point-point where we need to reflect
     * @param v-incoming vector
     * @param n-the normal
     * @return-a ray that is reflected of the object
     */
    private Ray constructReflectedRay(Point3D point, Vector v, Vector n) {
        Vector r = v.subtract(n.scale(2 * v.dotProduct(n)));
        return new Ray(point, r, n);
    }

    /**
     * constructs refracted ray
     * @param point-point where we need to refract
     * @param v-incoming vector
     * @param n-the normal
     * @return - the refracted ray
     */
    private Ray constructRefractedRay(Point3D point, Vector v, Vector n) {
        return new Ray(point, v, n); // direction of r = direction of v, just the beginning point is different
    }

    /**
     * sends reflected/refracted ray back to calcolor
     * @param ray- the reflected/refracted ray
     * @param level- bouncing level
     * @param kx- the reflected/refracted parameter
     * @param kkx- Kx times k
     * @return- the color of the reflected/refracted ray
     */
    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection (ray);

        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }

    /**
     * finds closest intersection
     * @param ray- the ray we are looking at
     * @return -the point we are looking at
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        var points = scene.geometries.findGeoIntersections(ray);

        if(points == null)
            return null;

        return ray.findClosestGeoPoint(points);
    }
}