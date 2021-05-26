package scene;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import geometries.Geometry;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

public class Scene {
    private final String name;
    public Color background = Color.BLACK;
    public AmbientLight  ambientLight = new AmbientLight(new Color(192, 192, 192), 1.d);
    public Geometries geometries = new Geometries();
    public List<LightSource> lights = new LinkedList<LightSource>();
    public Scene(sceneBuilder sb) {
        this.name = sb.name;
        this.background = sb.background;
        this.ambientLight = sb.ambientLight;
        this.geometries = sb.geometries;
        this.lights = sb.lights;
    }

    //chaining methods
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    public static class sceneBuilder
    {
        private final String name;
        public Color background = Color.BLACK;
        public AmbientLight  ambientLight = new AmbientLight(new Color(192, 192, 192), 1.d);
        public Geometries geometries = new Geometries();
        public List<LightSource> lights = new LinkedList<LightSource>();

        public sceneBuilder(String name) {
            this.name = name;

        }
        public sceneBuilder background(Color background) {
            this.background = background;
            return this;
        }
        public sceneBuilder ambientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }
        public sceneBuilder geometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }
        public sceneBuilder lights(List<LightSource> lights) {
            this.lights = lights;
            return this;
        }
        //Return the finally consrcuted User object
        public Scene build() {
            Scene s =  new Scene(this);
            validateUserObject(s);
            return s;
        }
        private void validateUserObject(Scene s) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }
    }
}
