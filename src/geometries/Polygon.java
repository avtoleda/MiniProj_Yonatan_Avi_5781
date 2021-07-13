package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.*;

import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 *
 * @author Dan
 */
public class Polygon extends Geometry {
    /**
     * List of polygon's vertices
     */
    protected List<Point3D> vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected Plane plane;
    protected Box box = new Box(); // the box that bound the polygon

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex)</li>
     *                                  </ul>
     */
    public Polygon(Point3D... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (vertices.length == 3) {
            box.setPolygonBox(this);
            return; // no need for more tests for a Triangle
        }
        Vector n = plane.getNormal();

        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (int i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }

        box.setPolygonBox(this);
    }

    @Override
    public Vector getNormal(Point3D point) {
        return plane.getNormal();
    }

    public List<Point3D> getVertices() {
        return this.vertices;
    }

    /**
     *
     * @return the bounding box of the polygon
     */
    public Box getBox() {
        return this.box;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        //if(!ray.intersect(box)) //check before if the ray intersect the bounding box of the polygon
          //  return null;

        List<GeoPoint> intersections = this.plane.findGeoIntersections(ray);

        if (intersections == null)
            return null;

        Point3D p0 = ray.getP0();
        Vector v = ray.getDir();

        intersections.get(0).geometry = this;
        Vector v1;
        Vector v2;
        Vector n;
        double s = 1;
        double sign;
        v1 = this.vertices.get(0).subtract(p0);
        v2 = this.vertices.get((1) % vertices.size()).subtract(p0);
        n = (v1.crossProduct(v2)).normalize();
        s = alignZero(v.dotProduct(n));
        sign = Math.signum(s);
        if (sign == 0)
            return null;
        for (int i = 1; i < vertices.size(); i++) {
            v1 = this.vertices.get(i).subtract(p0);
            v2 = this.vertices.get((i + 1) % vertices.size()).subtract(p0);
            n = (v1.crossProduct(v2)).normalize();
            s = alignZero(v.dotProduct(n));
            if (sign * s <= 0)
                return null;
        }

        return intersections;
    }
}