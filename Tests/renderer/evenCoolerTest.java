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
//        new Polygon(new Point3D(0,0,0),new Point3D(-50,0,0),new Point3D(-50,-50,0),new Point3D(0,-50,0))
//                .setEmission(new Color(123,32,87)).setMaterial(new Material().setShininess(69).setKs(0.7).setKd(0.5).setKr(0.6).setKt(1)),
////        new Polygon(new Point3D(0,0,0),new Point3D(0,0,50),new Point3D(0,-50,50),new Point3D(0,-50,0))
////                .setEmission(new Color(123,32,87)).setMaterial(new Material().setShininess(69).setKs(0.7).setKd(0.5).setKr(0.6).setKt(0)),
//          new Polygon(new Point3D(0,-50,0),new Point3D(0,-50,50),new Point3D(-50,-50,50),new Point3D(-50,-50,0))
//                .setEmission(new Color(123,32,87)).setMaterial(new Material().setShininess(69).setKs(0.7).setKd(0.5).setKr(0.6).setKt(1)),
//        new Polygon(new Point3D(-50,-50,0),new Point3D(-50,-50,50),new Point3D(-50,0,50),new Point3D(-50,0,0))
//                .setEmission(new Color(123,32,87)).setMaterial(new Material().setShininess(69).setKs(0.7).setKd(0.5).setKr(0.6).setKt(1)),
//        new Polygon(new Point3D(-50,0,0),new Point3D(-50,0,50),new Point3D(0,0,50),new Point3D(0,0,0))
//                .setEmission(new Color(123,32,87)).setMaterial(new Material().setShininess(69).setKs(0.7).setKd(0.5).setKr(0.6).setKt(1)),
//        new Polygon(new Point3D(0,0,50),new Point3D(0,-50,50),new Point3D(-50,-50,50),new Point3D(-50,0,50))
//                .setEmission(new Color(123,32,87)).setMaterial(new Material().setShininess(69).setKs(0.7).setKd(0.5).setKr(0.6).setKt(1)),
//                new Sphere(10,new Point3D(0,0,30))
//                        .setEmission(new Color(200,111,64))
//                        .setMaterial(new Material().setKd(0.6).setKs(0.7).setShininess(10))
////                new Polygon(new Point3D(40,40,0),new Point3D(40,-40,0),new Point3D(-40,-40,10),new Point3D(-40,40,10))
//                        .setEmission(new Color(80,111,64))
//                        .setMaterial(new Material().setKd(0.6).setKs(0.7).setShininess(70).setKr(0.5)),
//                new Polygon(new Point3D(40,40,40),new Point3D(40,-40,40),new Point3D(-40,-40,30),new Point3D(-40,40,30))
//                        .setEmission(new Color(80,111,64))
//                        .setMaterial(new Material().setKd(0.6).setKs(0.7).setShininess(70).setKr(0.3).setKt(0.3)),
//                new Polygon(new Point3D(0,30,0),new Point3D(0,0,0),new Point3D(0,0,-10),new Point3D(0,30,-10))
//                        .setEmission(new Color(194, 8, 33))

                //new Triangle(new Point3D(40,40,0),new Point3D(40,-40,0),new Point3D(-40,-40,10))
                //        .setEmission(new Color(80,111,64))
                //        .setMaterial(new Material().setKd(0.6).setKs(0.7).setShininess(70).setKr(0.5)),
                //new Triangle(new Point3D(40,40,0),new Point3D(-40,-40,10),new Point3D(-40,40,10))
                //        .setEmission(new Color(80,111,64))
                //        .setMaterial(new Material().setKd(0.6).setKs(0.7).setShininess(70).setKr(0.5))
               // );
//        scene.lights.add(
//               new SpotLight(new Color(70, 160, 100),new Point3D(50,50,50),new Vector(-50,-50,-20)).setKq(0.5).setKl(0.6).setKc(0.7));
//        scene.lights.add(
//        new SpotLight(new Color(100,100,200),new Point3D(0,0,50),new Vector(new Point3D(0,0,-2))));
//        scene.lights.add(new DirectionalLight(new Color(150,98,72),new Vector(0,-3,-6)));
//      scene.lights.add(
//                new PointLight(new Color(200, 170, 245),new Point3D(-70,78,40)).setKl(0.4).setKq(0.005).setKc(0.5));
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
                new Triangle(new Point3D(0-20,0,-10),new Point3D(15-20,15,15),new Point3D(15-20,-15,15))
                        .setEmission(new Color(50,40,50)).setMaterial(new Material()
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
                        .setEmission(new Color(200,200,200))
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
        scene.lights.add(
                new PointLight(new Color(200, 170, 245),new Point3D(-20,0,100)).setKq(1E-6));
        Render render = new Render() //
                .setImageWriter(new ImageWriter("hi to u", 600, 600)) //
                .setCamera(_camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
}
