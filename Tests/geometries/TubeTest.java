package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void getNormal() {
        Ray ray = new Ray(new Point3D(0, 1, 0), new Vector(0,1,0));
        Tube tb = new Tube(ray, 2);

        assertEquals(tb.getNormal(new Point3D(0,2,2)), new Vector(0, 0, 1));

        //Ray ray = new Ray(new Point3D(0, 1, 0), new Vector(0,-1,0));
        //Tube tb = new Tube(ray, 2);

        //assertEquals(tb.getNormal(new Point3D(0,0,0)), new Vector(0, 1, 0));
    }
}