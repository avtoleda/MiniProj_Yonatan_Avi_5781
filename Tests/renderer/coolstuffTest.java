package renderer;

import elements.*;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.*;
import scene.Scene;

    public class coolstuffTest {
        private Camera _camera= new Camera(new Point3D(140,0,0),new Vector(-1,0,0), new Vector(0,0,1)).setViewPlaneSize(200, 200).setDistance(140);
    private Scene scene = new Scene("Testyon").setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.2)); //
    ;
    @Test
    public void cool(){
        scene.geometries.add(
                new Sphere(10,new Point3D(0,0,30))
                        .setEmission(new Color(245, 66, 102))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(10)) ,
                new Sphere(10,new Point3D(9,-9,20))
                        .setEmission(new Color(11,111,64))
                        .setMaterial(new Material().setKd(0.6).setKs(0.7).setShininess(10)) ,
                new Sphere(10,new Point3D(-9,9,20))
                        .setEmission(new Color(207, 247, 59))
                        .setMaterial(new Material().setKd(0.6).setKs(0.7).setShininess(10)) ,
                new Sphere(10,new Point3D(9,9,20))
                        .setEmission(new Color(111,11,164))
                        .setMaterial(new Material().setKd(0.6).setKs(0.7).setShininess(10)) ,
                new Sphere(10,new Point3D(-9,-9,20))
                        .setEmission(new Color(200,111,64))
                        .setMaterial(new Material().setKd(0.6).setKs(0.7).setShininess(10)) ,
                new Triangle(new Point3D(0,0,-10),new Point3D(15,15,15),new Point3D(15,-15,15))
                        .setEmission(new Color(50,40,50)).setMaterial(new Material()
                        .setKd(0.3).setKs(0.6).setShininess(70)),
                new Triangle(new Point3D(0,0,-10),new Point3D(15,15,15),new Point3D(-15,15,15))
                        .setEmission(new Color(130, 101, 55)).setMaterial(new Material()
                        .setKd(0.3).setKs(0.6).setShininess(70)),
                new Triangle(new Point3D(0,0,-10),new Point3D(-15,-15,15),new Point3D(15,-15,15))
                        .setEmission(new Color(130, 101, 55)).setMaterial(new Material()
                        .setKd(0.3).setKs(0.6).setShininess(70)),
                new Triangle(new Point3D(0,0,-10),new Point3D(-15,-15,15),new Point3D(-15,15,15))
                        .setEmission(new Color(130, 101, 55)).setMaterial(new Material()
                        .setKd(0.3).setKs(0.6).setShininess(70)),
                new Sphere(20,new Point3D(-70,78,100))
                        .setEmission(new Color(200,100,100))
                        .setMaterial(new Material().setKd(0).setKt(0.5).setKr(0.0).setKs(0.0).setShininess(0)),
                new Plane(new Point3D(150, -150, -10), new Point3D(-150, 150, -10), new Point3D(-67, -67, -10))
                        .setEmission(new Color(168, 101, 45))
                        .setMaterial(new Material().setKr(0.2).setShininess(30).setKd(0.5).setKt(0.3))

        );
        scene.lights.add(
                new DirectionalLight(new Color(120, 166, 240),new Vector(0.4,3.6,-2.5)));
        scene.lights.add(
                new SpotLight(new Color(70, 60, 100),new Point3D(40,40,40),new Vector(-2,-2,-4)).setKq(0.3).setKl(0.4).setKc(0.7));
        scene.lights.add(
                new PointLight(new Color(200, 170, 245),new Point3D(-70,78,100)).setKl(0.4).setKq(0.005).setKc(0.5));
//        scene.lights.add(
//                new SpotLight(new Color(70, 60, 100),new Point3D(40,0,-4),new Vector(2,0,4)).setKq(0.6).setKl(0.5).setKc(0.7));
        Render render = new Render() //
                .setImageWriter(new ImageWriter("hi", 600, 600)) //
                .setCamera(_camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
}
