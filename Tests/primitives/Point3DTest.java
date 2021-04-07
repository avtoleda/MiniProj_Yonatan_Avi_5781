package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class Point3DTest {
    Point3D p1 = new Point3D(1.0d, 2.0d, 3.0d);
    Point3D p2 = new Point3D(1.0000000000000000000001d, 2.0d, 3.0d);

    @Test
    void testEquals() {
        assertEquals(p1, p2);
    }

    @Test
    void distanceSquared() {
        assertTrue(isZero(p1.distanceSquared(p2)), "ERROR: distanceSquared() return a wrong result!");
    }

    @Test
    void substract() {
    }

    @Test
    void add() {

    }
}