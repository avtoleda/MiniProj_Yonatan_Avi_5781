package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * global interface for all Intersectable shapes
 */

public interface Intersectable {
    /**
     * @param ray - thee ray with we want to find intersections
     * @return - list of intersection points between the ray to the geometry
     */
    public default List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
    }

    /**
     * geometry - a geometry shape
     * point - point on the geometry (we will enter to this point the intersection point between the shape and a ray)
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * constructor
         *
         * @param geometry - a geometry shape
         * @param point    - point on the geometry
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }
    }

    /**
     * @param ray - the ray with we want to find intersections
     * @return list of geo points(geometry with the point of intersection with the ray)
     */
    public List<GeoPoint> findGeoIntersections(Ray ray);
}