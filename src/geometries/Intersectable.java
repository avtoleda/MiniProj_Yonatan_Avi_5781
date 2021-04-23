package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

/**
 * global interface for all Intersectable shapes
 */

public interface Intersectable {
    List<Point3D> findIntersections(Ray ray);
}