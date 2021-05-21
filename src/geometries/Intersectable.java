package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

/**
 * global interface for all Intersectable shapes
 */

public interface Intersectable {
    List<Point3D> findIntersections(Ray ray);

    /**
     *
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * a constructor
         * @param geometry
         * @param point
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

    public List<GeoPoint> findGeoIntersections(Ray ray);

}