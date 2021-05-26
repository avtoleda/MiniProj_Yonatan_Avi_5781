package elements;
import geometries.*;
import primitives.*;

import scene.Scene;

import java.util.List;

public interface LightSource {
    public Color getIntensity(Point3D p);
    public Vector getL(Point3D p);

}