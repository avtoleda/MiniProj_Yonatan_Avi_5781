package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import static java.lang.Double.max;

/**
 * class for the spot light in the scene
 * dir - the direction vector of the spot light
 */
public class SpotLight extends PointLight {
    Vector dir;

    public SpotLight(Color c, Point3D position, Vector dir) {
        super(c, position);
        this.dir = dir.normalize();
    }


    /**
     * @param p - position p vector (the vector that begin at position and end at p)
     * @return the intensity of the spot light
     */
    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity(p).scale(max(0, dir.dotProduct(getVecFromPos(p)))); //mult. by the dot prod or 0
    }
}