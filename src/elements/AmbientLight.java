package elements;

import primitives.Color;

/**
 * class for the ambient light in the scene
 */
public class AmbientLight extends Light {

    /**
     * default constructor
     * call the constructor of light class with default intensity - BLACK color
     */
    public AmbientLight() {
        super(Color.BLACK);
    }

    /**
     * constructor
     *
     * @param iA - intensity of the light
     * @param kA - Attenuation factor
     */
    public AmbientLight(Color iA, double kA) {
        super(iA.scale(kA));
    }
}
