//package renderer;
//
//import elements.*;
//import geometries.*;
//import org.junit.jupiter.api.Test;
//import primitives.*;
//import renderer.*;
//import scene.Scene;
//
//public class cooleststuffever {
//    private Camera _camera= new Camera(new Point3D(140,0,15),new Vector(-1,0,0), new Vector(0,0,1)).setViewPlaneSize(200, 200).setDistance(140);
//    private Scene scene = new Scene("wonderful").setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.2));
//    @Test
//    public void cooleststuffever(){
//        scene.geometries.add(
//            new Polygon(new Point3D(-60,-60,-5),new Point3D(60,-60,-5),new Point3D(60,60,-5),new Point3D(-60,60,-5))
//                    .setMaterial(new Material().setKd(0.5).setKr(0.3).setKs(0.1).setKt(0.6).setShininess(30))
//                    .setEmission(new Color(56, 107, 43)),
//            //the left post
//            new Polygon(new Point3D(0,-10,-5), new Point3D(0,-11,-5), new Point3D(0,-11,10), new Point3D(0,-10,10)),
//            new Polygon(new Point3D(-2,-10,-5), new Point3D(-2,-11,-5), new Point3D(-2,-11,10), new Point3D(-2,-10,10)),
//            new Polygon(new Point3D(0,-10,-5),new Point3D(-2,-10,-5),new Point3D(-2,-10,10),new Point3D(0,-10,10)),
//            new Polygon(new Point3D(0,-11,-5),new Point3D(-2,-11,-5),new Point3D(-2,-11,10),new Point3D(0,-11,10)),
//            //the cross bar
//            new Polygon(new Point3D(0,-11,10),new Point3D(-2,-11,10),new Point3D(-2,11,10),new Point3D(0,11,10)),
//            new Polygon(new Point3D(0,-10,8),new Point3D(-2,-10,8),new Point3D(-2,10,8), new Point3D(0,10,8)),
//            new Polygon(new Point3D(0,-10,8),new Point3D(0,-10,10),new Point3D(0,10,10),new Point3D(0,10,8)),
//            new Polygon(new Point3D(-2,-10,8),new Point3D(-2,-10,10),new Point3D(-2,10,10),new Point3D(-2,10,8)),
//            //the right post
//                new Polygon(new Point3D(0,10,-5), new Point3D(0,11,-5), new Point3D(0,11,10), new Point3D(0,10,10)),
//                new Polygon(new Point3D(-2,-10,-5), new Point3D(-2,11,-5), new Point3D(-2,11,10), new Point3D(-2,10,10)),
//                new Polygon(new Point3D(0,10,-5),new Point3D(-2,10,-5),new Point3D(-2,10,10),new Point3D(0,10,10)),
//                new Polygon(new Point3D(0,11,-5),new Point3D(-2,11,-5),new Point3D(-2,11,10),new Point3D(0,11,10)),
//                );
//    }
//}
