package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class CylinderTest {
    @Test
    void getNormal(){

        Ray r= new Ray(new Point3D(0,0,0),new Vector(1,0,0));
        Cylinder c=new Cylinder(r,2,2);
        Point3D p1=new Point3D(1,0,2);
        assertEquals(c.getNormal(p1),new Vector(0,0,1));
    }
    @Test
    void getNormalb1f(){
        Ray r= new Ray(new Point3D(0,0,0),new Vector(1,0,0));
        Cylinder c=new Cylinder(r,2,2);
        Point3D p1=new Point3D(0,0,1);
        assertEquals(c.getNormal(p1),new Vector(1,0,0));
    }
    @Test
    void getNormalb1s(){
        Ray r= new Ray(new Point3D(0,0,0),new Vector(1,0,0));
        Cylinder c=new Cylinder(r,2,2);
        Point3D p1=new Point3D(0,0,0);
        assertEquals(c.getNormal(p1),new Vector(1,0,0));
    }
    @Test
    void getNormalb2f(){
        Ray r= new Ray(new Point3D(0,0,0),new Vector(1,0,0));
        Cylinder c=new Cylinder(r,2,2);
        Point3D p1=new Point3D(2,0,1);
        assertEquals(c.getNormal(p1),new Vector(1,0,0));
    }
    @Test
    void getNormalb2s(){
        Ray r= new Ray(new Point3D(0,0,0),new Vector(1,0,0));
        Cylinder c=new Cylinder(r,2,2);
        Point3D p1=new Point3D(2,0,0);
        assertEquals(c.getNormal(p1),new Vector(1,0,0));
    }
}
