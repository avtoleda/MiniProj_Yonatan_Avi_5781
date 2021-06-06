package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Triangle in the 3D space
 */
public class Triangle extends Polygon {
    /**
     * creates a new Triangle
     *
     * @param p1 first point of the Triangle
     * @param p2 second point of the Triangle
     * @param p3 third pint of the Triangle
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * @param ray - the ray with we want to find intersections
     * @return - the plane with the intersection points between this plane to the ray
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> intersections = this.plane.findGeoIntersections(ray);

        if (intersections == null)
            return null;

        Point3D p0 = ray.getP0();
        Vector v = ray.getDir();

        intersections.get(0).geometry = this;

        Vector v1 = this.vertices.get(0).subtract(p0);
        Vector v2 = this.vertices.get(1).subtract(p0);
        Vector v3 = this.vertices.get(2).subtract(p0);

        Vector n1 = (v1.crossProduct(v2)).normalize();
        double s1 = alignZero(v.dotProduct(n1));

        Vector n2 = (v2.crossProduct(v3)).normalize();
        double s2 = alignZero(v.dotProduct(n2));

        Vector n3 = (v3.crossProduct(v1)).normalize();
        double s3 = alignZero(v.dotProduct(n3));

        return ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) ? intersections : null;
    }
}