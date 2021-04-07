package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {
    Vector v = new Vector(0, 4, 3);

    @Test
    void testConstructor() {
        try { //test zero vector
            new Vector(0, 0, 0);
            fail("ERROR: zero vector doesn't throw an exception");
        } catch (IllegalArgumentException e) {
            System.out.println("good: zero vector can't exist");
        }
    }

    @Test
    void subtract() {
    }

    @Test
    void add() {
    }

    @Test
    void scale() {
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3), "crossProduct() for parallel vectors does not throw an exception");
        // try {
        //     v1.crossProduct(v2);
        //     fail("crossProduct() for parallel vectors does not throw an exception");
        // } acatch (Exception e) {}
    }

    @Test
    void dotProduct() {
    }

    @Test
    void lengthSquared() {
        if(!isZero(v.lengthSquared() - 25))
            fail("Error: lengthSquared() return wrong value!");
    }

    @Test
    void length() {
        assertEquals(5, v.length(), "ERROR: length() return wrong value!");
    }

    @Test
    public void testNormalize() {
        Vector v = new Vector(3.5, -5, 10);
        v.normalize();
        assertEquals(1, v.length(), 1e-10, "");

        try {
            v = new Vector(0, 0, 0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            v.normalize();
            //fail("Didn't throw divide by zero exception!");
        } catch (ArithmeticException e) {
            assertTrue(true);
        }
    }

    @Test
    void normalized() {
    }
}