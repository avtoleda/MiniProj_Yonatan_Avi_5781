package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Plane in the 3D space
 * q0 - point in the plane
 * normal - the normal vector of the plane
 */
public class Plane extends Geometry {
    final Point3D q0;
    final Vector normal;

    /**
     * creates a new plane
     *
     * @param q0     - point in the plane
     * @param normal - the normal vector of the plane
     */
    public Plane(Point3D q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalized();
    }

    /**
     * creates a new plane
     *
     * @param p1 - point in the plane
     * @param p2 - point in the plane
     * @param p3 - point in the plane
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        this.q0 = p2;

        //calculate of the normal
        Vector u = p2.subtract(p1);
        Vector v = p3.subtract(p1);
        Vector n = u.crossProduct(v);

        this.normal = n.normalize();
    }

    /**
     * @return point in the plane
     */
    public Point3D getQ0() {
        return this.q0;
    }

    /**
     * @return the normal vector of the plane (without receiving point in the plane)
     */
    public Vector getNormal() {
        return this.normal;
    }

    /**
     * @param p - point in the plane from where we want calculate the normal
     * @return the normal vector to the plane
     */
    @Override
    public Vector getNormal(Point3D p) {
        return this.getNormal();
    }

    /**
     * find intersections between the ray and the plane
     *
     * @param ray - the ray with we want find intersections
     * @return list of geopoints(the shape and the point of intersection with the ray)
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> l;
        Point3D P0 = ray.getP0();
        Vector v = ray.getDir();

        if (q0.equals(P0)) {
            l = List.of(new GeoPoint(this, q0));
            return l;
        }

        double nv = this.normal.dotProduct(v);

        //The ray is laying on the plane
        if (isZero(nv))
            return null;

        double t = alignZero(this.normal.dotProduct(this.q0.subtract(P0)) / nv);

        if (t <= 0)
            return null;

        Point3D p = ray.getPoint(t);
        l = List.of(new GeoPoint(this, p));

        return l;
    }
}