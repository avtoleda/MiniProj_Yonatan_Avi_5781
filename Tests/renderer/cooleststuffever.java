package renderer;

import elements.*;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.*;
import scene.Scene;

public class cooleststuffever {
    private Camera _camera= new Camera(new Point3D(70,0,15),new Vector(-1,0,0), new Vector(0,0,1)).setViewPlaneSize(200, 200).setDistance(100);
    private Scene scene = new Scene("wonderful").setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.2));
    static double DELTA=0.1;
    @Test
    public void cooleststuffever(){
        scene.geometries.add(
            new Polygon(new Point3D(-60,-60,-5),new Point3D(240,-60,-5),new Point3D(240,60,-5),new Point3D(-60,60,-5))
                    .setMaterial(new Material().setKd(0.5).setKr(0).setKs(0.0009).setKt(0).setShininess(30))
                    .setEmission(new Color(61, 99, 48)),
            new Polygon(new Point3D(-60,-60,-5), new Point3D(-60,60,-5),new Point3D(-90,100,60), new Point3D(-90,-100,60)).setEmission(new Color(57, 85, 196)),
            new Polygon(new Point3D(-60,-60,-5), new Point3D(240,-60,-5), new Point3D(270,-100,60), new Point3D(-90,-100,60)).setEmission(new Color(191, 16, 10)),
            new Polygon(new Point3D(240,-60,-5), new Point3D(240,60,-5),new Point3D(270,100,60), new Point3D(270,-100,60)).setEmission(new Color(191, 16, 10)),
            new Polygon(new Point3D(-60,60,-5), new Point3D(240,60,-5), new Point3D(270,100,60), new Point3D(-90,100,60)).setEmission(new Color(191, 16, 10)),

            new Polygon(new Point3D(-20,-60,-5+DELTA),new Point3D(-22,-60,-5+DELTA),new Point3D(-22,60,-5+DELTA),new Point3D(-20,60,-5+DELTA))
                .setEmission(new Color(250, 251, 252)),
            new Polygon(new Point3D(-20,20,-5+DELTA),new Point3D(0,20,-5+DELTA),new Point3D(0,21,-5+DELTA),new Point3D(-20,21,-5+DELTA))
                .setEmission(new Color(250, 251, 252)),
            new Polygon(new Point3D(-20,-20,-5+DELTA),new Point3D(0,-20,-5+DELTA),new Point3D(0,-21,-5+DELTA),new Point3D(-20,-21,-5+DELTA))
                .setEmission(new Color(250, 251, 252)),
            new Polygon(new Point3D(0,-20,-5+DELTA),new Point3D(-2,-20,-5+DELTA),new Point3D(-2,20,-5+DELTA),new Point3D(0,20,-5+DELTA))
                .setEmission(new Color(250, 251, 252)),
            new Polygon(new Point3D(-20,30,-5+DELTA),new Point3D(10,30,-5+DELTA),new Point3D(10,31,-5+DELTA),new Point3D(-20,31,-5+DELTA))
                    .setEmission(new Color(250, 251, 252)),
            new Polygon(new Point3D(-20,-30,-5+DELTA),new Point3D(10,-30,-5+DELTA),new Point3D(10,-31,-5+DELTA),new Point3D(-20,-31,-5+DELTA))
                    .setEmission(new Color(250, 251, 252)),
            new Polygon(new Point3D(10,-30,-5+DELTA),new Point3D(8,-30,-5+DELTA),new Point3D(8,30,-5+DELTA),new Point3D(10,30,-5+DELTA))
                    .setEmission(new Color(250, 251, 252)),
            //the left post
            new Polygon(new Point3D(-20,-20,-5), new Point3D(-20,-21,-5), new Point3D(-20,-21,10), new Point3D(-20,-20,10))
                .setEmission(new Color(250, 251, 252)).setMaterial(new Material().setKr(0.1).setShininess(10).setKs(0.2).setKt(0.0)),
            new Polygon(new Point3D(-22,-20,-5), new Point3D(-22,-21,-5), new Point3D(-22,-21,10), new Point3D(-22,-20,10))
                    .setEmission(new Color(250, 251, 252)).setMaterial(new Material().setKr(0.1).setShininess(10).setKs(0.2).setKt(0.0)),
            new Polygon(new Point3D(-20,-20,-5),new Point3D(-22,-20,-5),new Point3D(-22,-20,10),new Point3D(-20,-20,10))
                    .setEmission(new Color(250, 251, 252)).setMaterial(new Material().setKr(0.1).setShininess(10).setKs(0.2).setKt(0.0)),
            new Polygon(new Point3D(-20,-21,-5),new Point3D(-22,-21,-5),new Point3D(-22,-21,10),new Point3D(-20,-21,10))
                    .setEmission(new Color(250, 251, 252)).setMaterial(new Material().setKr(0.1).setShininess(10).setKs(0.2).setKt(0.0)),
            //the cross bar
            new Polygon(new Point3D(-20,-21,10),new Point3D(-22,-21,10),new Point3D(-22,21,10),new Point3D(-20,21,10))
                    .setEmission(new Color(250, 251, 252)).setMaterial(new Material().setKr(0.1).setShininess(10).setKs(0.2).setKt(0.0)),
            new Polygon(new Point3D(-20,-20,8),new Point3D(-22,-20,8),new Point3D(-22,20,8), new Point3D(-20,20,8))
                    .setEmission(new Color(250, 251, 252)).setMaterial(new Material().setKr(0.1).setShininess(10).setKs(0.2).setKt(0.0)),
            new Polygon(new Point3D(-20,-20,8),new Point3D(-20,-20,10),new Point3D(-20,20,10),new Point3D(-20,20,8))
                    .setEmission(new Color(250, 251, 252)).setMaterial(new Material().setKr(0.1).setShininess(10).setKs(0.2).setKt(0.0)),
            new Polygon(new Point3D(-22,-20,8),new Point3D(-22,-20,10),new Point3D(-22,20,10),new Point3D(-22,20,8)).
                    setEmission(new Color(250, 251, 252)).setMaterial(new Material().setKr(0.1).setShininess(10).setKs(0.2).setKt(0.0)),
            //the right post
            new Polygon(new Point3D(-20,20,-5), new Point3D(-20,21,-5), new Point3D(-20,21,10), new Point3D(-20,20,10))
                    .setEmission(new Color(250, 251, 252)).setMaterial(new Material().setKr(0.1).setShininess(10).setKs(0.2).setKt(0.0)),
            new Polygon(new Point3D(-22,20,-5), new Point3D(-22,21,-5), new Point3D(-22,21,10), new Point3D(-22,20,10))
                    .setEmission(new Color(250, 251, 252)).setMaterial(new Material().setKr(0.1).setShininess(10).setKs(0.2).setKt(0.0)),
            new Polygon(new Point3D(-20,20,-5),new Point3D(-22,20,-5),new Point3D(-22,20,10),new Point3D(-20,20,10))
                    .setEmission(new Color(250, 251, 252)).setMaterial(new Material().setKr(0.1).setShininess(10).setKs(0.2).setKt(0.0)),
            new Polygon(new Point3D(-20,21,-5),new Point3D(-22,21,-5),new Point3D(-22,21,10),new Point3D(-20,21,10))
                    .setEmission(new Color(250, 251, 252)).setMaterial(new Material().setKr(0.1).setShininess(10).setKs(0.2).setKt(0.0)),
            //ball
            new Sphere(3,new Point3D(40,0,-2)).setEmission(new Color(97, 152, 207)).setMaterial(new Material().setKr(0.3).setShininess(50).setKs(0.01)),
            new Sphere(5,new Point3D(-60,-60,100)).setEmission(new Color(232, 250, 248)).setMaterial(new Material().setKt(1).setKr(0)),
            new Sphere(5,new Point3D(-60,60,100)).setEmission(new Color(232, 250, 248)).setMaterial(new Material().setKt(1).setKr(0)),
            new Sphere(5,new Point3D(240,-60,100)).setEmission(new Color(232, 250, 248)).setMaterial(new Material().setKt(1).setKr(0)),
            new Sphere(5,new Point3D(240,60,100)).setEmission(new Color(232, 250, 248)).setMaterial(new Material().setKt(1).setKr(0))
            );
//        scene.lights.add(
//                new DirectionalLight(new Color(120, 166, 240),new Vector(0.4,3.6,-2.5)));
        scene.lights.add(
                new SpotLight(new Color(232, 250, 248),new Point3D(-60,-60,100),new Vector(100,60,-100)).setKq(1E-4));//.setKq(0.3).setKl(0.4).setKc(0.7));
        scene.lights.add(
                new SpotLight(new Color(232, 250, 248), new Point3D(-60,60,100), new Vector(100,-60,100)).setKq(1E-4));//.setKq(0.00005).setKl(0.03).setKc(0.8));
        scene.lights.add(
                new SpotLight(new Color(232, 250, 248), new Point3D(240,-60,100), new Vector(-200,60,-100)).setKq(1E-4));
        scene.lights.add(
                new SpotLight(new Color(232, 250, 248),new Point3D(240,60,100),new Vector(-200,-60,-100)).setKq(1E-4));
        Render render = new Render() //
                .setImageWriter(new ImageWriter("football", 600, 600)) //
                .setCamera(_camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
}
