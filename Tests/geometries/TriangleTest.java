package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void findIntersections() {
        Triangle triangle = new Triangle(
                new Point3D(1.0, 1.0, 0.0),
                new Point3D(0.0, 1.0, 0.0),
                new Point3D(0.0, 1.0, 1.0));
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects inside the triangle (1 points)
        assertEquals(List.of(new Point3D(0.1, 1.0, 0.1)),
                triangle.findIntersections(new Ray(new Point3D(0.1, -3, 0.1), new Vector(0.0, 1.0, 0.0))),
                "Ray not intersected the triangle as expected");

        // TC02: Ray intersects outside against edge of the triangle (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(-0.5, 1, -0.5))),
                "Ray intersect the triangle, not as expected! ");

        // TC03: Ray intersects outside against vertex of the triangle (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(1, 1, -0.1))),
                "Ray intersect the triangle, not as expected! ");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray intersects the bound of the triangle

        // TC04: Ray intersects an edge of the triangle (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point3D(1, 0, 1), new Vector(-0.5, 1, -0.5))),
                "Ray intersect the triangle, not as expected! ");

        // TC05: Ray intersects a vertex of the triangle (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(-1, 1, 0))),
                "Ray intersect the triangle, not as expected! ");

        // TC06: Ray intersects an  edge's continuation of the triangle (0 points)
        assertNull(triangle.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(1, 1, 0))),
                "Ray intersect the triangle, not as expected! ");
    }
}