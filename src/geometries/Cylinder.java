package geometries;

import primitives.Ray;

/**
 * Cylinder in the 3D space
 * field1 height - height of the tube
 */
public class Cylinder extends Tube {
    double height;

    /**
     * create a new Cylinder
     * @param axisRay -the ray around which we built the cylinder
     * @param radius - the radius of the cylinder
     * @param height - the height of the cylinder
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
}