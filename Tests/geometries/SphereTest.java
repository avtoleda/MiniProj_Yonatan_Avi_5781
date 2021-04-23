package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal() {
        Sphere sp = new Sphere(1, new Point3D(0, 0, 0));
        Vector N = sp.getNormal(new Point3D(0, 1, 0));

        assertEquals(N, new Vector(0, 1, 0));
    }

    @Test
    void findIntsersections() {
        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(1, 1, 0))), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(3, 1, 0)));

        assertEquals(2, result.size(), "Wrong number of points");

        //if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));

        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        result = sphere.findIntersections(new Ray(new Point3D(1.5, 0, 0),
                new Vector(3, 1, 0)));

        assertEquals(1, result.size(), "Wrong number of points");

        // TC04: Ray starts after the sphere (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(3, 0, 0),
                new Vector(3, 1, 0)));

        assertNull(result, "Wrong number of points");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 0, -1),
                new Vector(1, 0, 1)));

        assertEquals(1, result.size(), "Wrong number of points");

        // TC12: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(2, 0, 0),
                new Vector(1, 0, 1)));

        assertNull(result, "Wrong number of points");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(4, 0, 0)));

        assertEquals(2, result.size(), "Wrong number of points");

        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(2, 0, 0),
                new Vector(-1, 0, 0)));

        assertEquals(1, result.size(), "Wrong number of points");

        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(0.5, 0, 0),
                new Vector(-1.5, 0, 0)));

        assertEquals(1, result.size(), "Wrong number of points");

        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 0, 0),
                new Vector(3, 1, 0)));

        assertEquals(1, result.size(), "Wrong number of points");

        // TC17: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(2, 0, 0),
                new Vector(1, 0, 0)));

        assertNull(result, "Wrong number of points");

        // TC18: Ray starts after sphere (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(-4, 0, 0)));

        assertNull(result, "Wrong number of points");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        result = sphere.findIntersections(new Ray(new Point3D(-3, 0, 0),
                new Vector(4, 0, 1)));

        assertNull(result, "Wrong number of points");

        // TC20: Ray starts at the tangent point
        result = sphere.findIntersections(new Ray(new Point3D(1, 0, 1),
                new Vector(1, 0, 1)));

        assertNull(result, "Wrong number of points");

        // TC21: Ray starts after the tangent point
        result = sphere.findIntersections(new Ray(new Point3D(2, 0, 2),
                new Vector(1, 0, 1)));

        assertNull(result, "Wrong number of points");

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        result = sphere.findIntersections(new Ray(new Point3D(-3, 0, 0),
                new Vector(0, 1, 2)));

        assertNull(result, "Wrong number of points");
    }
}