package elements;

import primitives.Color;

/**
 * general class for light in the scene
 * intensity - intensity of the light
 */
class Light {
    private Color intensity;

    /**
     * constructor
     *
     * @param c - intensity of the light
     */
    protected Light(Color c) {
        intensity = c;
    }

    /**
     * @return intensity of the light
     */
    public Color getIntensity() {
        return intensity;
    }
}