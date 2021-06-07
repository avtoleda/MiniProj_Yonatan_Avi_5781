package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * abstract class for ray scanner
 * scene - a scene
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * constructor
     *
     * @param scene - the scene
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * @param ray - the ray that pass in the middle of the pixel
     * @return the color of the pixel
     */
    public abstract Color traceRay(Ray ray);
}