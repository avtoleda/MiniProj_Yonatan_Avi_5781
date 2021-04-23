package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void getNormal() {
        Point3D p1=new Point3D(1,0,0);
        Point3D p2=new Point3D(2,0,2);
        Point3D p3=new Point3D(3,0,3);

        Plane p=new Plane(p1,p2,p3);
        int help=1;
        if(p.getNormal(p3) == new Vector(0,-1,0))
            help=-1;
        assertEquals(p.getNormal(),new Vector(0,help,0));
    }
    @Test
    void constructor1(){
        Point3D p1=new Point3D(1,0,0);
        Point3D p2=new Point3D(1,0,0);
        Point3D p3=new Point3D(3,0,3);
        assertThrows(IllegalArgumentException.class, ()->new Plane(p1,p2,p3),"wrong arguments");
    }
    @Test
    void constructor2(){
        Point3D p1=new Point3D(1,1,1);
        Point3D p2=new Point3D(2,2,2);
        Point3D p3=new Point3D(3,3,3);
        assertThrows(IllegalArgumentException.class, ()->new Plane(p1,p2,p3),"wrong arguments");
    }

    @Test
    void findIntersections() {
        Plane plane = new Plane(
                new Point3D(1.0, 0.0, 0.0),
                new Point3D(0.0, 1.0, 0.0),
                new Point3D(0.0, 0.0, 1.0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the plane (1 points)
        assertEquals(List.of(new Point3D(1, 0, 0)),
                plane.findIntersections(new Ray(new Point3D(-3, 0.0, 0), new Vector(6, 0, 0))),
                "Ray not intersected the plane, not as expected");

        // TC02: Ray doesn't intersect the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point3D(0.5, 0, 0), new Vector(-5, 0, 0))),
                "Ray intersect the Plane, not as expected!");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray is parallel to the plane
        // TC03: Ray is include in the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(1, -1, 0))),
                "Ray intersect the Plane, not as expected! ");

        // TC04: Ray doesn't include in the plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point3D(0.5, 0, -1), new Vector(1, -1, 0))),
                "Ray intersect the Plane, not as expected! ");

        // **** Group: Ray is orthogonal to the plane
        // TC05: P0 is before the Plane (1 points)
        assertEquals(List.of(new Point3D(0.3333333333333335, 0.3333333333333335, 0.3333333333333335)),
                plane.findIntersections(new Ray(new Point3D(-1, -1, -1),
                        new Vector(0.5773502691896258, 0.5773502691896258, 0.5773502691896258))),
                "Ray not intersected the plane as expected");
        // TC06: P0 is in the Plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point3D(0, 0, 1),
                        new Vector(0.5773502691896258, 0.5773502691896258, 0.5773502691896258))),
                "Ray intersect the Plane, not as expected! ");
        // TC07: P0 is after the Plane (0 points)
        assertNull(plane.findIntersections(new Ray(new Point3D(2, 2, 2),
                        new Vector(0.5773502691896258, 0.5773502691896258, 0.5773502691896258))),
                "Ray intersect the Plane, not as expected! ");

        // TC08: Ray is neither orthogonal nor parallel to the plane and begins at the plane
        //  P0 is in the plane, but not the ray (0 points)
        assertNull(plane.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(1, 0, 0))),
                "Ray intersect the Plane, not as expected! ");
        // TC09: Ray is neither orthogonal nor parallel to the plane and
        //  Ray begins in the same point which appears as reference point in the plane (Q)(0 points)
        assertNull(plane.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(1, 0, 0))),
                "Ray intersect the Plane, not as expected! ");
    }
}