package geometries;

import primitives.Point3D;
import primitives.Vector;
import primitives.*;
/**
 * global interface for all geometries shapes
 */
public abstract class Geometry implements Intersectable {
    protected Color emission=Color.BLACK;
    public abstract Vector getNormal(Point3D p);

    public Color getEmission() {
        return emission;
    }

    public void setEmission(Color emission) {
        this.emission = emission;
    }

}