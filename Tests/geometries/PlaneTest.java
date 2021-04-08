package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void getNormal() {
        Point3D p1=new Point3D(1,0,0);
        Point3D p2=new Point3D(2,0,2);
        Point3D p3=new Point3D(3,0,3);

        Plane p=new Plane(p1,p2,p3);
        int help=1;
        if(p.getNormal()==new Vector(0,-1,0))
            help=-1;
        assertEquals(p.getNormal(),new Vector(0,help*1,0));
    }

}