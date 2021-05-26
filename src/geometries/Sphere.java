package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Sphere in the 3D space
 * field1 center - the center point of the Sphere
 * field2 radius - the radius of the Sphere
 */
public class Sphere extends RadialGeometry {
    final Point3D center;

    /**
     * creates a new Sphere
     * @param center - the center point of the Sphere
     * @param radius - the radius of the Sphere
     */
    public Sphere(double radius, Point3D center) {
        super(radius);
        this.center = center;
    }

    /**
     * @return the center point of the Sphere
     */
    public Point3D getCenter() {
        return this.center;
    }

    @Override
    public String toString() {
        return "center= " + this.center.toString() + ", radius= " + radius;
    }

    /**
     * @param p point on the Sphere from where we calculate the normal
     * @return the normal vector to the Sphere
     */
    @Override
    public Vector getNormal(Point3D p) {
        Vector v = p.subtract(this.center);
        return v.normalize();
    }

    //@Override
    //public List<Point3D> findIntersections(Ray ray) {
    //    Vector u;
//
    //    try {
    //        u = this.center.subtract(ray.getP0());
    //    }
//
    //    catch (IllegalArgumentException e) {
    //        return List.of(ray.getPoint(this.radius));
    //    }
//
    //    Vector v = ray.getDir();
    //    double tm = alignZero(u.dotProduct(v));
    //    double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));
//
    //    if(d > this.radius)
    //        return  null;
//
    //    double th = alignZero(Math.sqrt(radius * radius - d * d));
//
    //    //P is on the surface of the sphere
    //    if(isZero(th))
    //        return null;
//
    //    double t1 = alignZero(tm + th);
    //    double t2 = alignZero(tm - th);
//
    //    if(t1 > 0 && t2 > 0)
    //        return List.of(ray.getPoint(t1), ray.getPoint(t2));
//
    //    if(t1 > 0)
    //        return List.of(ray.getPoint(t1));
//
    //    if(t2 > 0)
    //        return List.of(ray.getPoint(t2));
//
    //    return  null;
 //   }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> l = null;
        Vector u;

        try {
            u = this.center.subtract(ray.getP0());
        }

        catch (IllegalArgumentException e) {
            return List.of(new GeoPoint(this,ray.getPoint(this.radius)));
        }

        Vector v = ray.getDir();
        double tm = alignZero(u.dotProduct(v));
        double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));

        if(d > this.radius)
            return  null;

        double th = alignZero(Math.sqrt(radius * radius - d * d));

        //P is on the surface of the sphere
        if(isZero(th))
            return null;

        double t1 = alignZero(tm + th);
        double t2 = alignZero(tm - th);

        if(t1 > 0 && t2 > 0)
            return List.of(new GeoPoint(this,ray.getPoint(t1)), new GeoPoint(this,ray.getPoint(t2)));

        if(t1 > 0)
            return List.of(new GeoPoint(this,ray.getPoint(t1)));

        if(t2 > 0)
            return List.of(new GeoPoint(this,ray.getPoint(t2)));

        return  null;
    }
}