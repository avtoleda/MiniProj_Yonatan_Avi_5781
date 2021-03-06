package renderer;

import elements.*;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.*;
import scene.Scene;

public class evenCoolerTest {
    private Camera _camera= new Camera(new Point3D(140,0,15),new Vector(-1,0,0), new Vector(0,0,1)).setViewPlaneSize(200, 200).setDistance(140);
    private Scene scene = new Scene("superb").setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.2));
    @Test
    public void EvenCoolerTest(){
        scene.geometries.add(
        new Sphere(10,new Point3D(0-20,0,30))
                .setEmission(new Color(245, 66, 102))
                .setMaterial(new Material().setKd(0.5).setKr(0.4).setKs(0.5).setShininess(10)) ,
                new Sphere(10,new Point3D(9-20,-9,20))
                        .setEmission(new Color(11,111,64))
                        .setMaterial(new Material().setKd(0.6).setKs(0.7).setShininess(10)) ,
                new Sphere(10,new Point3D(-9-20,9,20))
                        .setEmission(new Color(207, 247, 59))
                        .setMaterial(new Material().setKd(0.6).setKs(0.7).setShininess(10)) ,
                new Sphere(10,new Point3D(9-20,9,20))
                        .setEmission(new Color(111,11,164))
                        .setMaterial(new Material().setKd(0.6).setKs(0.7).setShininess(10)) ,
                new Sphere(10,new Point3D(-9-20,-9,20))
                        .setEmission(new Color(200,111,64))
                        .setMaterial(new Material().setKd(0.6).setKs(0.7).setShininess(10)) ,
                //cone
                new Triangle(new Point3D(0-20,0,-10),new Point3D(15-20,15,15),new Point3D(15-20,-15,15))
                        .setEmission(new Color(130, 101, 55)).setMaterial(new Material()
                        .setKd(0.3).setKs(0.6).setShininess(50)),
                new Triangle(new Point3D(0-20,0,-10),new Point3D(15-20,15,15),new Point3D(-15-20,15,15))
                        .setEmission(new Color(130, 101, 55)).setMaterial(new Material()
                        .setKd(0.3).setKs(0.6).setShininess(50)),
                new Triangle(new Point3D(0-20,0,-10),new Point3D(-15-20,-15,15),new Point3D(15-20,-15,15))
                        .setEmission(new Color(130, 101, 55)).setMaterial(new Material()
                        .setKd(0.3).setKs(0.6).setShininess(50)),
                new Triangle(new Point3D(0-20,0,-10),new Point3D(-15-20,-15,15),new Point3D(-15-20,15,15))
                        .setEmission(new Color(130, 101, 55)).setMaterial(new Material()
                        .setKd(0.3).setKs(0.6).setShininess(50)),
                //light
                new Sphere(20,new Point3D(-20,0,100))
                        .setEmission(new Color(200,200,100))
                        .setMaterial(new Material().setKd(0.3).setKt(0.7).setKr(0.0).setKs(0.1).setShininess(30)),
                //table
                //top and bottom
                new Polygon(new Point3D(50, 50, -10), new Point3D(-50, 50, -10), new Point3D(-50, -50, -10), new Point3D(50,-50,-10))
                        .setEmission(new Color(168, 101, 45))
                        .setMaterial(new Material().setKr(0.8).setShininess(30).setKd(0.5).setKt(0.0)),
                new Polygon(new Point3D(50, 50, -10.5), new Point3D(-50, 50, -10.5), new Point3D(-50, -50, -10.5), new Point3D(50,-50,-10.5))
                        .setEmission(new Color(168, 101, 45))
                        .setMaterial(new Material().setKr(0.8).setShininess(30).setKd(0.5).setKt(0.0)),
                //front and back
                new Polygon(new Point3D(50, 50, -10.5), new Point3D(50, 50, -10), new Point3D(50, -50, -10), new Point3D(50,-50,-10.5))
                        .setEmission(new Color(168, 101, 45))
                        .setMaterial(new Material().setKr(0.8).setShininess(30).setKd(0.5).setKt(0.0)),
                new Polygon(new Point3D(-50, 50, -10.5), new Point3D(-50, 50, -10), new Point3D(-50, -50, -10), new Point3D(-50,-50,-10.5))
                        .setEmission(new Color(168, 101, 45))
                        .setMaterial(new Material().setKr(0.8).setShininess(30).setKd(0.5).setKt(0.0)),
                //sides
                new Polygon(new Point3D(50, 50, -10.5), new Point3D(-50, 50, -10.5), new Point3D(-50, 50, -10), new Point3D(50,50,-10))
                        .setEmission(new Color(168, 101, 45))
                        .setMaterial(new Material().setKr(0.8).setShininess(30).setKd(0.5).setKt(0.0)),
                new Polygon(new Point3D(50, -50, -10.5), new Point3D(-50, -50, -10.5), new Point3D(-50, -50, -10), new Point3D(50,-50,-10))
                        .setEmission(new Color(168, 101, 45))
                        .setMaterial(new Material().setKr(0.8).setShininess(30).setKd(0.5).setKt(0.0)),
                //legs
                //front right
                new Polygon(new Point3D(50, 50, -10.5), new Point3D(50, 49.5, -10.5), new Point3D(50, 49.5, -40), new Point3D(50,50,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(50, 49.5, -10.5), new Point3D(49.5, 49.5, -10.5), new Point3D(49.5, 49.5, -40), new Point3D(50,49.5,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(49.5, 49.5, -10.5), new Point3D(49.5, 50, -10.5), new Point3D(49.5, 50, -40), new Point3D(49.5,49.5,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(49.5, 50, -10.5), new Point3D(50, 50, -10.5), new Point3D(50, 50, -40), new Point3D(49.5,50,-40))
                        .setEmission(new Color(168, 101, 45)),
                //front left
                new Polygon(new Point3D(50, -50, -10.5), new Point3D(50, -49.5, -10.5), new Point3D(50, -49.5, -40), new Point3D(50,-50,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(50, -49.5, -10.5), new Point3D(49.5, -49.5, -10.5), new Point3D(49.5, -49.5, -40), new Point3D(50,-49.5,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(49.5, -49.5, -10.5), new Point3D(49.5,- 50, -10.5), new Point3D(49.5, -50, -40), new Point3D(49.5,-49.5,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(49.5, -50, -10.5), new Point3D(50, -50, -10.5), new Point3D(50,- 50, -40), new Point3D(49.5,-50,-40))
                        .setEmission(new Color(168, 101, 45)),
                //back right
                new Polygon(new Point3D(-50, 50, -10.5), new Point3D(-50, 49.5, -10.5), new Point3D(-50, 49.5, -40), new Point3D(-50,50,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(-50, 49.5, -10.5), new Point3D(-49.5, 49.5, -10.5), new Point3D(-49.5, 49.5, -40), new Point3D(-50,49.5,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(-49.5, 49.5, -10.5), new Point3D(-49.5, 50, -10.5), new Point3D(-49.5, 50, -40), new Point3D(-49.5,49.5,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(-49.5, 50, -10.5), new Point3D(-50, 50, -10.5), new Point3D(-50, 50, -40), new Point3D(-49.5,50,-40))
                        .setEmission(new Color(168, 101, 45)),
                //back left
                new Polygon(new Point3D(-50, -50, -10.5), new Point3D(-50, -49.5, -10.5), new Point3D(-50, -49.5, -40), new Point3D(-50,-50,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(-50, -49.5, -10.5), new Point3D(-49.5, -49.5, -10.5), new Point3D(-49.5, -49.5, -40), new Point3D(-50,-49.5,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(-49.5, -49.5, -10.5), new Point3D(-49.5, -50, -10.5), new Point3D(-49.5, -50, -40), new Point3D(-49.5,-49.5,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(-49.5, -50, -10.5), new Point3D(-50, -50, -10.5), new Point3D(-50, -50, -40), new Point3D(-49.5,-50,-40))
                        .setEmission(new Color(168, 101, 45)),
                //walls
                new Polygon(new Point3D(-100,100,100),new Point3D(-100, -100, 100), new Point3D(-100, -100, -41), new Point3D(-100, 100, -41))
                .setEmission(new Color(129, 171, 219)),
                new Polygon(new Point3D(100,-100,100),new Point3D(-100, -100, 100), new Point3D(-100, -100, -41), new Point3D(100, -100, -41))
                        .setEmission(new Color(129, 171, 219)),
                new Polygon(new Point3D(100,100,100),new Point3D(-100, 100, 100), new Point3D(-100, 100, -41), new Point3D(100, 100, -41))
                        .setEmission(new Color(129, 171, 219)),
                 new Polygon(new Point3D(-100, -100, -41), new Point3D(-100, 100, -41), new Point3D(100,100,-41), new Point3D(100,-100,-41))
                    .setEmission(new Color(230, 222, 193)).setMaterial(new Material().setKr(0.7).setKd(0.4)),
                //carpet
                //top and bottom
                new Polygon(new Point3D(52, 52, -40), new Point3D(-52, 52, -40), new Point3D(-52, -52, -40), new Point3D(52,-52,-40))
                        .setEmission(new Color(179, 4, 4))
                        .setMaterial(new Material().setKr(0.0).setShininess(30).setKd(0.5).setKt(0.0)),
//                new Polygon(new Point3D(50, 50, -10.5), new Point3D(-50, 50, -10.5), new Point3D(-50, -50, -10.5), new Point3D(50,-50,-10.5))
//                        .setEmission(new Color(168, 101, 45))
//                        .setMaterial(new Material().setKr(0.8).setShininess(30).setKd(0.5).setKt(0.0)),
                //front and back
                new Polygon(new Point3D(52, 52, -41), new Point3D(52, 52, -40), new Point3D(52, -52, -40), new Point3D(52,-52,-41))
                        .setEmission(new Color(179, 4, 4))
                        .setMaterial(new Material().setKr(0.0).setShininess(30).setKd(0.5).setKt(0.0)),
                new Polygon(new Point3D(-52, 52, -41), new Point3D(-52, 52, -40), new Point3D(-52, -52, -40), new Point3D(-52,-52,-41))
                        .setEmission(new Color(179, 4, 4))
                        .setMaterial(new Material().setKr(0.0).setShininess(30).setKd(0.5).setKt(0.0)),
                //sides
                new Polygon(new Point3D(52, 52, -41), new Point3D(-52, 52, -41), new Point3D(-52, 52, -40), new Point3D(52,52,-40))
                        .setEmission(new Color(179, 4, 4))
                        .setMaterial(new Material().setKr(0.0).setShininess(30).setKd(0.5).setKt(0.0)),
                new Polygon(new Point3D(52, -52, -41), new Point3D(-52, -52, -41), new Point3D(-52, -52, -40), new Point3D(52,-52,-40))
                        .setEmission(new Color(179, 4, 4))
                        .setMaterial(new Material().setKr(0.0).setShininess(30).setKd(0.5).setKt(0.0)),
                //test spheres
                new Sphere(10, new Point3D(0,40,5))
                        .setEmission(new Color(150,98,200)).setMaterial(new Material().setKr(0.1).setShininess(10).setKs(0.2).setKt(0.0)),
                new Sphere(10, new Point3D(0,-40,5))
                        .setEmission(new Color(150,98,200)).setMaterial(new Material().setKr(0.1).setShininess(10).setKs(0.2).setKt(0.0)),
                new Polygon(new Point3D(50,-50,-10), new Point3D(50,-10,-10), new Point3D(50,-10,30), new Point3D(50,-50,30))
                        .setMaterial(new Material().setKt(1).setKr(0.0).setKd(0.3).setKs(0.2))

                );
        scene.lights.add(
                new DirectionalLight(new Color(120, 166, 240),new Vector(0.4,3.6,-2.5)));
        scene.lights.add(
                new SpotLight(new Color(70, 60, 100),new Point3D(100,100,100),new Vector(-90,-90,-90)).setKq(0.3).setKl(0.4).setKc(0.7));
        scene.lights.add(
                new SpotLight(new Color(232, 250, 248), new Point3D(60,40,2), new Vector(-1,0,0)).setKq(0.00005).setKl(0.03).setKc(0.8));
        scene.lights.add(
                new SpotLight(new Color(232, 250, 248), new Point3D(60,-40,2), new Vector(-1,0,0)).setKq(0.00005).setKl(0.03).setKc(0.8));
        //scene.lights.add(
        //        new PointLight(new Color(200, 170, 245),new Point3D(-20,0,100)).setKq(1E-10));
        Render render = new Render() //
                .setImageWriter(new ImageWriter("hi to u 8", 600, 600)) //
                .setCamera(_camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void checkTest() {
        scene.geometries.add(
                new Sphere(10,new Point3D(0-20,0,30))
                        .setEmission(new Color(245, 66, 102))
                        .setMaterial(new Material().setKd(0.5).setKr(0.4).setKs(0.5).setShininess(10)) ,
                new Sphere(10,new Point3D(9-20,-9,20))
                        .setEmission(new Color(11,111,64))
                        .setMaterial(new Material().setKd(0.6).setKs(0.7).setShininess(10)) ,
                new Sphere(10,new Point3D(-9-20,9,20))
                        .setEmission(new Color(207, 247, 59))
                        .setMaterial(new Material().setKd(0.6).setKs(0.7).setShininess(10)) ,
                new Sphere(10,new Point3D(9-20,9,20))
                        .setEmission(new Color(111,11,164))
                        .setMaterial(new Material().setKd(0.6).setKs(0.7).setShininess(10)) ,
                new Sphere(10,new Point3D(-9-20,-9,20))
                        .setEmission(new Color(200,111,64))
                        .setMaterial(new Material().setKd(0.6).setKs(0.7).setShininess(10)) ,
                //cone
                new Triangle(new Point3D(0-20,0,-10),new Point3D(15-20,15,15),new Point3D(15-20,-15,15))
                        .setEmission(new Color(130, 101, 55)).setMaterial(new Material()
                        .setKd(0.3).setKs(0.6).setShininess(50)),
                new Triangle(new Point3D(0-20,0,-10),new Point3D(15-20,15,15),new Point3D(-15-20,15,15))
                        .setEmission(new Color(130, 101, 55)).setMaterial(new Material()
                        .setKd(0.3).setKs(0.6).setShininess(50)),
                new Triangle(new Point3D(0-20,0,-10),new Point3D(-15-20,-15,15),new Point3D(15-20,-15,15))
                        .setEmission(new Color(130, 101, 55)).setMaterial(new Material()
                        .setKd(0.3).setKs(0.6).setShininess(50)),
                new Triangle(new Point3D(0-20,0,-10),new Point3D(-15-20,-15,15),new Point3D(-15-20,15,15))
                        .setEmission(new Color(130, 101, 55)).setMaterial(new Material()
                        .setKd(0.3).setKs(0.6).setShininess(50)),
                //light
                new Sphere(20,new Point3D(-20,0,100))
                        .setEmission(new Color(200,200,100))
                        .setMaterial(new Material().setKd(0.3).setKt(0.7).setKr(0.0).setKs(0.1).setShininess(30)),
                //table
                //top and bottom
                new Polygon(new Point3D(50, 50, -10), new Point3D(-50, 50, -10), new Point3D(-50, -50, -10), new Point3D(50,-50,-10))
                        .setEmission(new Color(168, 101, 45))
                        .setMaterial(new Material().setKr(0.8).setShininess(30).setKd(0.5).setKt(0.0)),
                new Polygon(new Point3D(50, 50, -10.5), new Point3D(-50, 50, -10.5), new Point3D(-50, -50, -10.5), new Point3D(50,-50,-10.5))
                        .setEmission(new Color(168, 101, 45))
                        .setMaterial(new Material().setKr(0.8).setShininess(30).setKd(0.5).setKt(0.0)),
                //front and back
                new Polygon(new Point3D(50, 50, -10.5), new Point3D(50, 50, -10), new Point3D(50, -50, -10), new Point3D(50,-50,-10.5))
                        .setEmission(new Color(168, 101, 45))
                        .setMaterial(new Material().setKr(0.8).setShininess(30).setKd(0.5).setKt(0.0)),
                new Polygon(new Point3D(-50, 50, -10.5), new Point3D(-50, 50, -10), new Point3D(-50, -50, -10), new Point3D(-50,-50,-10.5))
                        .setEmission(new Color(168, 101, 45))
                        .setMaterial(new Material().setKr(0.8).setShininess(30).setKd(0.5).setKt(0.0)),
                //sides
                new Polygon(new Point3D(50, 50, -10.5), new Point3D(-50, 50, -10.5), new Point3D(-50, 50, -10), new Point3D(50,50,-10))
                        .setEmission(new Color(168, 101, 45))
                        .setMaterial(new Material().setKr(0.8).setShininess(30).setKd(0.5).setKt(0.0)),
                new Polygon(new Point3D(50, -50, -10.5), new Point3D(-50, -50, -10.5), new Point3D(-50, -50, -10), new Point3D(50,-50,-10))
                        .setEmission(new Color(168, 101, 45))
                        .setMaterial(new Material().setKr(0.8).setShininess(30).setKd(0.5).setKt(0.0)),
                //legs
                //front right
                new Polygon(new Point3D(50, 50, -10.5), new Point3D(50, 49.5, -10.5), new Point3D(50, 49.5, -40), new Point3D(50,50,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(50, 49.5, -10.5), new Point3D(49.5, 49.5, -10.5), new Point3D(49.5, 49.5, -40), new Point3D(50,49.5,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(49.5, 49.5, -10.5), new Point3D(49.5, 50, -10.5), new Point3D(49.5, 50, -40), new Point3D(49.5,49.5,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(49.5, 50, -10.5), new Point3D(50, 50, -10.5), new Point3D(50, 50, -40), new Point3D(49.5,50,-40))
                        .setEmission(new Color(168, 101, 45)),
                //front left
                new Polygon(new Point3D(50, -50, -10.5), new Point3D(50, -49.5, -10.5), new Point3D(50, -49.5, -40), new Point3D(50,-50,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(50, -49.5, -10.5), new Point3D(49.5, -49.5, -10.5), new Point3D(49.5, -49.5, -40), new Point3D(50,-49.5,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(49.5, -49.5, -10.5), new Point3D(49.5,- 50, -10.5), new Point3D(49.5, -50, -40), new Point3D(49.5,-49.5,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(49.5, -50, -10.5), new Point3D(50, -50, -10.5), new Point3D(50,- 50, -40), new Point3D(49.5,-50,-40))
                        .setEmission(new Color(168, 101, 45)),
                //back right
                new Polygon(new Point3D(-50, 50, -10.5), new Point3D(-50, 49.5, -10.5), new Point3D(-50, 49.5, -40), new Point3D(-50,50,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(-50, 49.5, -10.5), new Point3D(-49.5, 49.5, -10.5), new Point3D(-49.5, 49.5, -40), new Point3D(-50,49.5,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(-49.5, 49.5, -10.5), new Point3D(-49.5, 50, -10.5), new Point3D(-49.5, 50, -40), new Point3D(-49.5,49.5,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(-49.5, 50, -10.5), new Point3D(-50, 50, -10.5), new Point3D(-50, 50, -40), new Point3D(-49.5,50,-40))
                        .setEmission(new Color(168, 101, 45)),
                //back left
                new Polygon(new Point3D(-50, -50, -10.5), new Point3D(-50, -49.5, -10.5), new Point3D(-50, -49.5, -40), new Point3D(-50,-50,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(-50, -49.5, -10.5), new Point3D(-49.5, -49.5, -10.5), new Point3D(-49.5, -49.5, -40), new Point3D(-50,-49.5,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(-49.5, -49.5, -10.5), new Point3D(-49.5, -50, -10.5), new Point3D(-49.5, -50, -40), new Point3D(-49.5,-49.5,-40))
                        .setEmission(new Color(168, 101, 45)),
                new Polygon(new Point3D(-49.5, -50, -10.5), new Point3D(-50, -50, -10.5), new Point3D(-50, -50, -40), new Point3D(-49.5,-50,-40))
                        .setEmission(new Color(168, 101, 45)),
                //walls
                new Polygon(new Point3D(-100,100,100),new Point3D(-100, -100, 100), new Point3D(-100, -100, -41), new Point3D(-100, 100, -41))
                        .setEmission(new Color(129, 171, 219)),
                new Polygon(new Point3D(100,-100,100),new Point3D(-100, -100, 100), new Point3D(-100, -100, -41), new Point3D(100, -100, -41))
                        .setEmission(new Color(129, 171, 219)),
                new Polygon(new Point3D(100,100,100),new Point3D(-100, 100, 100), new Point3D(-100, 100, -41), new Point3D(100, 100, -41))
                        .setEmission(new Color(129, 171, 219)),
                new Polygon(new Point3D(-100, -100, -41), new Point3D(-100, 100, -41), new Point3D(100,100,-41), new Point3D(100,-100,-41))
                        .setEmission(new Color(230, 222, 193)).setMaterial(new Material().setKr(0.7).setKd(0.4)),
                //carpet
                //top and bottom
                new Polygon(new Point3D(52, 52, -40), new Point3D(-52, 52, -40), new Point3D(-52, -52, -40), new Point3D(52,-52,-40))
                        .setEmission(new Color(179, 4, 4))
                        .setMaterial(new Material().setKr(0.0).setShininess(30).setKd(0.5).setKt(0.0)),
//                new Polygon(new Point3D(50, 50, -10.5), new Point3D(-50, 50, -10.5), new Point3D(-50, -50, -10.5), new Point3D(50,-50,-10.5))
//                        .setEmission(new Color(168, 101, 45))
//                        .setMaterial(new Material().setKr(0.8).setShininess(30).setKd(0.5).setKt(0.0)),
                //front and back
                new Polygon(new Point3D(52, 52, -41), new Point3D(52, 52, -40), new Point3D(52, -52, -40), new Point3D(52,-52,-41))
                        .setEmission(new Color(179, 4, 4))
                        .setMaterial(new Material().setKr(0.0).setShininess(30).setKd(0.5).setKt(0.0)),
                new Polygon(new Point3D(-52, 52, -41), new Point3D(-52, 52, -40), new Point3D(-52, -52, -40), new Point3D(-52,-52,-41))
                        .setEmission(new Color(179, 4, 4))
                        .setMaterial(new Material().setKr(0.0).setShininess(30).setKd(0.5).setKt(0.0)),
                //sides
                new Polygon(new Point3D(52, 52, -41), new Point3D(-52, 52, -41), new Point3D(-52, 52, -40), new Point3D(52,52,-40))
                        .setEmission(new Color(179, 4, 4))
                        .setMaterial(new Material().setKr(0.0).setShininess(30).setKd(0.5).setKt(0.0)),
                new Polygon(new Point3D(52, -52, -41), new Point3D(-52, -52, -41), new Point3D(-52, -52, -40), new Point3D(52,-52,-40))
                        .setEmission(new Color(179, 4, 4))
                        .setMaterial(new Material().setKr(0.0).setShininess(30).setKd(0.5).setKt(0.0)),
                //test spheres
                new Sphere(10, new Point3D(0,40,5))
                        .setEmission(new Color(150,98,200)).setMaterial(new Material().setKr(0.1).setShininess(10).setKs(0.2).setKt(0.0)),
                new Sphere(10, new Point3D(0,-40,5))
                        .setEmission(new Color(150,98,200)).setMaterial(new Material().setKr(0.1).setShininess(10).setKs(0.2).setKt(0.0)),
                new Polygon(new Point3D(50,-50,-10), new Point3D(50,-10,-10), new Point3D(50,-10,30), new Point3D(50,-50,30))
                        .setMaterial(new Material().setKt(1).setKr(0.0).setKd(0.3).setKs(0.2))

        );
        scene.lights.add(
                new DirectionalLight(new Color(120, 166, 240),new Vector(0.4,3.6,-2.5)));
        scene.lights.add(
                new SpotLight(new Color(70, 60, 100),new Point3D(100,100,100),new Vector(-90,-90,-90)).setKq(0.3).setKl(0.4).setKc(0.7));
        scene.lights.add(
                new SpotLight(new Color(232, 250, 248), new Point3D(60,40,2), new Vector(-1,0,0)).setKq(0.00005).setKl(0.03).setKc(0.8));
        scene.lights.add(
                new SpotLight(new Color(232, 250, 248), new Point3D(60,-40,2), new Vector(-1,0,0)).setKq(0.00005).setKl(0.03).setKc(0.8));
        //scene.lights.add(
        //        new PointLight(new Color(200, 170, 245),new Point3D(-20,0,100)).setKq(1E-10));
        Render render = new Render() //
                .setImageWriter(new ImageWriter("hi to u after", 600, 600)) //
                .setCamera(_camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
}
