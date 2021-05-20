package primitives;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void findClosestPoint() {
        Ray ray = new Ray(Point3D.ZERO, new Vector(0, 0, 1));

        //EP
        List<Point3D> points3D = new LinkedList<>();

        points3D.add(new Point3D(-1000, 90, 100));
        points3D.add(new Point3D(50, 48, 1000));
        points3D.add(new Point3D(0, .5, 1));
        points3D.add(new Point3D(-20, 60, 50));
        points3D.add(new Point3D(0, 0, -90));

        assertEquals(points3D.get(2), ray.findClosestPoint(points3D), "supposed to be " + points3D.get(2).toString());

        //BVA1
        List<Point3D> points3D1 = null;
        assertNull(ray.findClosestPoint(points3D1), "supposed to be null");

        //BVA2
        List<Point3D> points3D2 = new LinkedList<>();

        points3D2.add(new Point3D(0, 0, 1));
        points3D2.add(new Point3D(50, 48, 1000));
        points3D2.add(new Point3D(0, .5, 1));

        assertEquals(points3D2.get(0), ray.findClosestPoint(points3D2), "supposed to be " + points3D2.get(0).toString());

        //BVA3
        List<Point3D> points3D3 = new LinkedList<>();

        points3D3.add(new Point3D(0, .5, 1));
        points3D3.add(new Point3D(50, 48, 1000));
        points3D3.add(new Point3D(0, 0, 1));

        assertEquals(points3D3.get(2), ray.findClosestPoint(points3D3), "supposed to be " + points3D3.get(2).toString());
    }
}