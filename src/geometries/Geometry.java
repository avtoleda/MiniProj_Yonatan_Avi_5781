package geometries;

import primitives.Point3D;
import primitives.Vector;
import primitives.*;

import java.util.List;

/**
 * global interface for all geometries shapes
 */
public abstract class Geometry implements Intersectable {
    protected Color emission=Color.BLACK;
    private Material m = new Material();

    /**
     *
     * @return
     */
    public Material getM() {
        return m;
    }

    /**
     *
     * @param m
     * @return
     */
    public Geometry setMaterial(Material m) {
        this.m = m;
        return this;
    }

    public abstract Vector getNormal(Point3D p);

    public Color getEmission() {
        return emission;
    }

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return  this;
    }

   // @Override
    //public abstract List<Point3D> findIntersections(Ray ray);
    @Override
    public abstract List<GeoPoint> findGeoIntersections(Ray ray);
}