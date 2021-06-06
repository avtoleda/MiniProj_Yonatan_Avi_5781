package elements;

import primitives.*;

/**
 * class foe directional light in the scene
 * direction - light vector direction
 */
public class DirectionalLight extends Light implements LightSource {
    private Vector direction;

    /**
     * constructor
     *
     * @param c         - intensity of the light
     * @param direction - direction of the light
     */
    public DirectionalLight(Color c, Vector direction) {
        super(c);
        this.direction = direction.normalized();
    }

    /**
     * @param p - the intersection point between the light and the geometry shape
     * @return the intensity of the directional light
     */
    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity();
    }

    /**
     * @param p - the intersection point between the light and the geometry shape
     * @return the direction of the light
     */
    @Override
    public Vector getL(Point3D p) {
        return direction.normalized();
    }

    /**
     * @param point - the intersection point between the light and the geometry shape
     * @return the distance between the point and the directional light source
     * (doesn't really matter because the distance have no effect in directional light)
     */
    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY;
    }
}