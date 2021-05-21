package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Tube in the 3D space
 * field1 axisRay - the ray around which we built the tube
 * field2 radius - the radius of the tube
 */
public class Tube extends RadialGeometry {
    final Ray axisRay;

    /**
     * create a new tube
     * @param axisRay - the ray around which we built the tube
     * @param radius - the radius of the tube
     */
    public Tube(Ray axisRay, double radius) {
        super(radius);
        this.axisRay = axisRay;
    }

    /**
     * @return the ray around which we built the tube
     */
    public Ray getAxisRay() {
        return this.axisRay;
    }

    @Override
    public String toString() {
        return "axisRay= " + this.axisRay.toString() + ", radius= " + radius;
    }

    /**
     * calculate the normal of the tube in a point
     * @param p - the point from where we want calculate the normal of the tube
     * @return the normal to the tube in p
     */
    @Override
    public Vector getNormal(Point3D p) {
        Vector P_P0 = p.subtract(this.axisRay.getP0());
        double t = this.axisRay.getDir().dotProduct(P_P0);

        Point3D O = this.axisRay.getP0().add(axisRay.getDir().scale(t));
        Vector N = p.subtract(O);

        return N.normalize();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}