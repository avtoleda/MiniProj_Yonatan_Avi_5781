package elements;

import geometries.*;
import primitives.*;

import scene.Scene;

import java.util.List;

/**
 * interface for directional light and point light
 */
public interface LightSource {
    public Color getIntensity(Point3D p); //return the intensity of the light

    public Vector getL(Point3D p); // return the direction of the light

    public double getDistance(Point3D point); //return the distance between the point and the light source
}