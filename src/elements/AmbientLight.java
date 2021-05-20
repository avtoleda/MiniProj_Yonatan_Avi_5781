package elements;

import primitives.Color;

public class AmbientLight {
    private final Color intensity;

    public AmbientLight() {
        intensity = Color.BLACK;
    }

    public AmbientLight(Color iA, double kA) {
        this.intensity = iA.scale(kA);
    }

    public Color getIntensity() {
        return intensity;
    }
}
