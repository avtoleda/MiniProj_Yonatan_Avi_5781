package elements;

import primitives.*;

public class DirectionalLight extends Light implements LightSource{
    private Vector direction;

    public DirectionalLight(Color c, Vector direction) {
        super(c);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity();
    }

    @Override
    public Vector getL(Point3D p) {
        return direction.scale(-1).normalized();
    }
}
