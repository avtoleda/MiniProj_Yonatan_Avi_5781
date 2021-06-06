package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Cylinder in the 3D space
 * height - height of the tube
 */
public class Cylinder extends Tube {
    final double height;

    /**
     * create a new Cylinder
     *
     * @param axisRay -the ray around which we built the cylinder
     * @param radius  - the radius of the cylinder
     * @param height  - the height of the cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * @return the height of the cylinder
     */
    public double getHeight() {
        return this.height;
    }

    @Override
    public String toString() {
        return super.toString() + "height= " + height;
    }

    /**
     * calculate the normal vector to the cylinder
     *
     * @param p - the point from where we want calculate the normal of the cylinder
     * @return the normal vector to the cylinder
     */
    @Override
    public Vector getNormal(Point3D p) {
        Point3D b2 = getAxisRay().getDir().scale(height).getHead();
        Vector vec1 = new Vector(1, 0, 0);//stam to make him happy
        Vector vec2 = new Vector(1, 0, 0);//stam to make him happy

        if (p != getAxisRay().getP0() && p != b2) {
            vec1 = b2.subtract(p);
            vec2 = getAxisRay().getP0().subtract(p);
        }

        if (p == getAxisRay().getP0() || p == b2 || vec1.dotProduct(getAxisRay().getDir()) == 0 || vec2.dotProduct(getAxisRay().getDir()) == 0)
            return getAxisRay().getDir();

        else
            return super.getNormal(p);
    }
}