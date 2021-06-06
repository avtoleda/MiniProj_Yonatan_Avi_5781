package geometries;

import primitives.Point3D;
import primitives.Vector;
import primitives.*;

import java.util.List;

/**
 * global interface for all geometries shapes
 * emission - the color of the shape
 * Material - the material the geometry is made of
 */
public abstract class Geometry implements Intersectable {
    protected Color emission = Color.BLACK;
    private Material m = new Material();

    /**
     * @return the material the geometry is made of
     */
    public Material getMaterial() {
        return m;
    }

    /**
     * @param m - the material the geometry is made of
     * @return the object himself(geometry)
     */
    public Geometry setMaterial(Material m) {
        this.m = m;
        return this;
    }

    /**
     * @param p - point on the geometry from where we want calculate the normal
     * @return the normal vector to the geometry
     */
    public abstract Vector getNormal(Point3D p);

    /**
     * @return the color of the shape
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * @param emission - the color of the shape
     * @return the object himself(Geometry)
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * @param ray - the ray with we want to find intersections
     * @return list of geo points intersections
     */
    @Override
    public abstract List<GeoPoint> findGeoIntersections(Ray ray);
}