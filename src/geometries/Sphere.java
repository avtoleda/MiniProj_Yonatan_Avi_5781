package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Sphere in the 3D space
 * field1 center - the center point of the Sphere
 * field2 radius - the radius of the Sphere
 */
public class Sphere implements Geometry {
    Point3D center;
    double radius;

    /**
     * creates a new Sphere
     * @param center - the center point of the Sphere
     * @param radius - the radius of the Sphere
     */
    public Sphere(Point3D center, double radius) {
        this.center = center;
        this.radius = radius;
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
        return null;
    }
}