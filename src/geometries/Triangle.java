package geometries;

import primitives.Point3D;

/**
 * Triangle in the 3D space
 */
public class Triangle extends Polygon {
    /**
     * creates a new Triangle
     * @param p1 first point of the Triangle
     * @param p2 second point of the Triangle
     * @param p3 third pint of the Triangle
     */
    Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}