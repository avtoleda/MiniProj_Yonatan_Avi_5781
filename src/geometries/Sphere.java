package geometries;

import primitives.Box;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

import geometries.Intersectable.GeoPoint;

/**
 * Sphere in the 3D space
 * center - the center point of the Sphere
 * radius - the radius of the Sphere
 * box - bounding box of the sphere
 */
public class Sphere extends Geometry {
    final Point3D center;
    final double radius;
    Box box = new Box();


    /**
     * creates a new Sphere
     *
     * @param center - the center point of the Sphere
     * @param radius - the radius of the Sphere
     */
    public Sphere(double radius, Point3D center) {
        this.radius = radius;
        this.center = center;
        box.setSphereBox(this);
    }

    /**
     * @return the center point of the Sphere
     */
    public Point3D getCenter() {
        return this.center;
    }

    /**
     * @return the radius of the Sphere
     */
    public double getRadius() {
        return this.radius;
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
        return p.subtract(this.center).normalize();
    }

    /**
     *
     * @return the bounding box of the sphere
     */
    public Box getBox() {
        return this.box;
    }

    /**
     * @param ray - the ray with we want to find intersections
     * @return - the sphere with the intersection points between this sphere to the ray
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        //if(!ray.intersect(box)) //check before if the ray intersect the bounding box
//            return null;

        Vector u;

        try {
            u = this.center.subtract(ray.getP0());
        } catch (IllegalArgumentException e) {
            return List.of(new GeoPoint(this, ray.getPoint(this.radius)));
        }

        Vector v = ray.getDir();
        double tm = alignZero(u.dotProduct(v));
        double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));

        if (d > this.radius)
            return null;

        double th = alignZero(Math.sqrt(radius * radius - d * d));

        if (isZero(th)) //P is on the surface of the sphere
            return null;

        double t1 = alignZero(tm + th);
        double t2 = alignZero(tm - th);

        if (t1 > 0 && t2 > 0) //two intersection points
            return List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));

        if (t1 > 0) //one intersection point
            return List.of(new GeoPoint(this, ray.getPoint(t1)));

        if (t2 > 0) //one intersection point
            return List.of(new GeoPoint(this, ray.getPoint(t2)));

        return null;
    }
}