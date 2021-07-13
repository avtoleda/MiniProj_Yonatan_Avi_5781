package primitives;

import geometries.Geometries;
import geometries.Intersectable;
import geometries.Polygon;
import geometries.Sphere;

import java.util.List;

/**
 * minX - min x coordinate of the box
 * maxX - max x coordinate of the box
 * minY - min y coordinate of the box
 * maxY - max y coordinate of the box
 * minZ - min z coordinate of the box
 * maxZ - max z coordinate of the box
 */
public class Box {
    double minX;
    double maxX;
    double minY;
    double maxY;
    double minZ;
    double maxZ;

    /**
     * constructor
     */
    public Box() {
        minX = Double.MAX_VALUE;
        maxX = Double.MIN_VALUE;
        minY = Double.MAX_VALUE;
        maxY = Double.MIN_VALUE;
        minZ = Double.MAX_VALUE;
        maxZ = Double.MIN_VALUE;
    }

    /**
     * contruct a box that bound the geometries in geo
     * @param geo - list of intersectables geometries
     */
    public void setGeometriesBox(List<Intersectable> geo) {
        minX = Double.MAX_VALUE;
        maxX = Double.MIN_VALUE;
        minY = Double.MAX_VALUE;
        maxY = Double.MIN_VALUE;
        minZ = Double.MAX_VALUE;
        maxZ = Double.MIN_VALUE;

        for (Intersectable geometry : geo) {
            if (geometry instanceof Sphere) {
                Sphere s = (Sphere) geometry;
                Box b = s.getBox();

                minX = b.minX < minX ? b.minX : minX;
                maxX = b.maxX > maxX ? b.maxX : maxX;
                minY = b.minY < minY ? b.minY : minY;
                maxY = b.maxY > maxY ? b.maxY : maxY;
                minZ = b.minZ < minZ ? b.minZ : minZ;
                maxZ = b.maxZ > maxZ ? b.maxZ : maxZ;
            }

            if (geometry instanceof Polygon) {
                Polygon p = (Polygon) geometry;
                Box b = p.getBox();

                minX = b.minX < minX ? b.minX : minX;
                maxX = b.maxX > maxX ? b.maxX : maxX;
                minY = b.minY < minY ? b.minY : minY;
                maxY = b.maxY > maxY ? b.maxY : maxY;
                minZ = b.minZ < minZ ? b.minZ : minZ;
                maxZ = b.maxZ > maxZ ? b.maxZ : maxZ;
            }
        }
    }

    /**
     * contruct a box that bound s
     * @param s - a sphere
     */
    public void setSphereBox(Sphere s) {
        double r = s.getRadius();
        Point3D c = s.getCenter();
        this.minX = c.x.coord - r;
        this.maxX = c.x.coord + r;
        this.minY = c.y.coord - r;
        this.maxY = c.y.coord + r;
        this.minZ = c.z.coord - r;
        this.maxZ = c.z.coord + r;
    }

    /**
     * contruct a box that bound p
     * @param p - a polygon
     */
    public void setPolygonBox(Polygon p) {
        List<Point3D> vertices = p.getVertices();
        double hMinX, hMaxX, hMinY, hMaxY, hMinZ, hMaxZ;

        minX = Double.MAX_VALUE;
        maxX = Double.MIN_VALUE;
        minY = Double.MAX_VALUE;
        maxY = Double.MIN_VALUE;
        minZ = Double.MAX_VALUE;
        maxZ = Double.MIN_VALUE;

        for (Point3D point : vertices) {
            hMinX = point.x.coord;
            hMaxX = point.x.coord;
            hMinY = point.y.coord;
            hMaxY = point.y.coord;
            hMinZ = point.z.coord;
            hMaxZ = point.z.coord;
            minX = hMinX < minX ? hMinX : minX;
            maxX = hMaxX > maxX ? hMaxX : maxX;
            minY = hMinY < minY ? hMinY : minY;
            maxY = hMaxY > maxY ? hMaxY : maxY;
            minZ = hMinZ < minZ ? hMinZ : minZ;
            maxZ = hMaxZ > maxZ ? hMaxZ : maxZ;
        }
    }
}