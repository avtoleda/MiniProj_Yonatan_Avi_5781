package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Plane in the 3D space
 * field1 q0 - point in the plane
 * field2 normal - the normal vector of the plane
 */
public class Plane implements Geometry{
    final Point3D q0;
    final Vector normal;

    /**
     * creates a new plane
     * @param q0 - point in the plane
     * @param normal - the normal vector of the plane
     */
    public Plane(Point3D q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalized();
    }

    /**
     * creates a new plane
     * @param p1 - point in the plane
     * @param p2 - point in the plane
     * @param p3 - point in the plane
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3){
        this.q0 = p2;

        /* real calculate, but for the next level of the project for now normal = null
        Vector v1 = p1.subtract(p2);
        Vector v2 = p3.subtract(p2);

        this.normal = v1.crossProduct(v2);
        */

        this.normal = null; // to be changed in next level of the project
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
     * @param p - point in the plane
     * @return the normal vector to the plane
     */
    @Override
    public Vector getNormal(Point3D p) {
        return getNormal();
    }
}