package elements;

import primitives.Color;

class Light {
    private Color intensity;

    public Color getIntensity() {
        return intensity;
    }

    protected Light(Color c){
        intensity=c;
    }
}
