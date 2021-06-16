package scene;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import geometries.Geometry;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * this class represent a scene
 * name - the name of the scene
 * background - the background color in the scene
 * ambientLight - the ambient light in the scene
 * geometries - the geometries n the scene
 * lights - list of the others lights in the scene(point lights, directional lights, spot lights)
 */
public class Scene {
    private final String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight(Color.BLACK, 1.d);
    public Geometries geometries = new Geometries();
    public List<LightSource> lights = new LinkedList<LightSource>();
    public int RayAmount = 0;

    /**
     * constructor
     *
     * @param sb - scene builder
     */
    public Scene(sceneBuilder sb) {
        this.name = sb.name;
        this.background = sb.background;
        this.ambientLight = sb.ambientLight;
        this.geometries = sb.geometries;
        this.lights = sb.lights;
    }

    /**
     * constructor
     *
     * @param name - the name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    //chaining methods

    /**
     * set background
     *
     * @param background - the background color in the scene
     * @return scene
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     *
     * @param rayAmount the amount of rays to use in the scene
     * @return returns the scene
     */
    public Scene setRayAmount(int rayAmount) {
        RayAmount = rayAmount;
        return this;
    }

    /**
     * set ambient light
     *
     * @param ambientLight
     * @return scene
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * set geometries
     *
     * @param geometries
     * @return scene
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * scene builder
     * name - the name of the scene
     * background - the background color in the scene
     * ambientLight - the ambient light in the scene
     * geometries - the geometries n the scene
     * lights - list of the others lights in the scene(point lights, directional lights, spot lights)
     */
    public static class sceneBuilder {
        private final String name;
        public Color background = Color.BLACK;
        public AmbientLight ambientLight = new AmbientLight(Color.BLACK, 1.d);
        public Geometries geometries = new Geometries();
        public List<LightSource> lights = new LinkedList<>();

        /**
         * constructor
         *
         * @param name - name of the scene
         */
        public sceneBuilder(String name) {
            this.name = name;
        }

        //chaining methods

        /**
         * set background
         *
         * @param background - the background color in the scene
         * @return scene builder
         */
        public sceneBuilder background(Color background) {
            this.background = background;
            return this;
        }

        /**
         * set ambient light
         *
         * @param ambientLight
         * @return scene builder
         */
        public sceneBuilder ambientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        /**
         * set geometries
         *
         * @param geometries
         * @return scene builder
         */
        public sceneBuilder geometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        /**
         * set lights
         *
         * @param lights - list of the others lights in the scene(point lights, directional lights, spot lights)
         * @return scene builder
         */
        public sceneBuilder lights(List<LightSource> lights) {
            this.lights = lights;
            return this;
        }

        //Return the finally constructed User object
        public Scene build() {
            Scene s = new Scene(this);
            validateUserObject(s);
            return s;
        }

        private void validateUserObject(Scene s) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }
    }
}