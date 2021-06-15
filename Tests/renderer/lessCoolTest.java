package renderer;

import elements.*;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.*;
import scene.Scene;

public class lessCoolTest {
    private Camera _camera= new Camera(new Point3D(140,0,40),new Vector(-1,0,0), new Vector(0,0,1)).setViewPlaneSize(200, 200).setDistance(140);
    private Scene scene = new Scene("superbish").setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.2));
    @Test
    void t(){
        scene.geometries.add(
            new Sphere(10,new Point3D(-20,0,50))
                    .setEmission(new Color(52, 119, 120))
                    .setMaterial(new Material().setKd(0.5).setKr(0.7).setKs(0.5).setShininess(10)),
                new Polygon(new Point3D(50, 50, 30), new Point3D(-50, 50, 30), new Point3D(-50, -50, 30), new Point3D(50,-50,30))
                        .setEmission(new Color(168, 101, 45))
                        .setMaterial(new Material().setKr(0.6).setShininess(30).setKd(0.5).setKt(0.0)));
        scene.lights.add(
                new DirectionalLight(new Color(120, 166, 240),new Vector(0,3.6,-2.5)));
        scene.lights.add(
                new DirectionalLight(new Color(120, 166, 50),new Vector(0,6,2)));
        scene.lights.add(
                new SpotLight(new Color(70, 60, 100),new Point3D(100,100,100),new Vector(-90,-90,-90)).setKq(0.3).setKl(0.4).setKc(0.7));

        Render render = new Render() //
                .setImageWriter(new ImageWriter("hi to u too", 600, 600)) //
                .setCamera(_camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }

}
