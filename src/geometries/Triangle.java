package geometries;

import primitives.Point3D;

/**
 * class that represents a triangle that inherits from polygon
 */
public class Triangle extends Polygon {
    /**
     * we call the parent const with the points
     * @param a
     * @param b
     * @param c
     */
    Triangle(Point3D a,Point3D b,Point3D c) {
    super(a,b,c);
}
}
