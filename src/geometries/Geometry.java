package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * global interface for all geometries shapes
 */
public interface Geometry extends Intersectable {
    Vector getNormal(Point3D p);
}