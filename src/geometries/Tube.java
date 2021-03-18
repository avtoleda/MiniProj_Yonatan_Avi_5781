package geometries;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry{
    Ray axisRay;
    double radius;

    /**
     * const. that puts the stuff and there are getters...
     * @param axisRay
     * @param radius
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**
     * left getnormal empty
     * @param p
     * @return
     */
    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}
