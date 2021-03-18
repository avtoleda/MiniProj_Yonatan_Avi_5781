package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry{
    final Point3D q0;
    final Vector normal;

    public Plane(Point3D q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalized();
    }

    /**
     * creates a plane...
     * @param p1
     * @param p2
     * @param p3
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

    public Point3D getQ0() {
        return this.q0;
    }

    public Vector getNormal() {
        return this.normal;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return getNormal();
    }
}