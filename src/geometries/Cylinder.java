package geometries;

import primitives.Ray;

public class Cylinder extends Tube{
    double height;

    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    /**
     * called the father tostring in this one
     * @return
     */
    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                super.toString()+
                '}';
    }
}
