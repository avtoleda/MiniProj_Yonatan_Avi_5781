package primitives;

/**
 * Vector in the 3D space(begin from (0,0,0) to a Point in the 3D space)
 * head - vector's end point
 */
public class Vector {
    Point3D head;

    /**
     * creates a new vector
     *
     * @param x - x end point coordinate
     * @param y - y end point coordinate
     * @param z - z end point coordinate
     */
    public Vector(double x, double y, double z) {
        Point3D check = new Point3D(x, y, z);

        if (check.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Vector 0 is impossible!\n");

        this.head = check;
    }

    /**
     * creates a new vector
     *
     * @param p - vector end point
     */
    public Vector(Point3D p) {
        if (p.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Vector 0 is impossible!\n");

        this.head = p;
    }

    /**
     * @return vector end point
     */
    public Point3D getHead() {
        return this.head;
    }

    /**
     * subtracts two vectors
     *
     * @param v - the vector we subtract from the vector
     * @return new vector that begin in (0, 0, 0) and end at the subtract of the two heads of the vectors
     */
    public Vector subtract(Vector v) {
        double x = this.head.x.coord - v.head.x.coord;
        double y = this.head.y.coord - v.head.y.coord;
        double z = this.head.z.coord - v.head.z.coord;

        return new Vector(x, y, z);
    }

    /**
     * adds two vector
     *
     * @param v - the vector we add to the vector
     * @return new vector that begin in (0, 0, 0) and end at the add of the two heads of the vectors
     */
    public Vector add(Vector v) {
        double x = this.head.x.coord + v.head.x.coord;
        double y = this.head.y.coord + v.head.y.coord;
        double z = this.head.z.coord + v.head.z.coord;

        return new Vector(x, y, z);
    }

    /**
     * multiplies a vector with a number
     *
     * @param n - the number we multiply the vector by.
     * @return vector multiply result
     */
    public Vector scale(double n) {
        if (n == 0)
            throw new IllegalArgumentException("can not scale by zero!\n");

        double x = this.head.x.coord * n;
        double y = this.head.y.coord * n;
        double z = this.head.z.coord * n;

        return new Vector(x, y, z);
    }

    /**
     * returns the cross product between the vector and another one
     *
     * @param v - a vector with we multiply the vector
     * @return vector multiply result
     */
    public Vector crossProduct(Vector v) {
        double x = this.head.y.coord * v.head.z.coord - this.head.z.coord * v.head.y.coord;
        double y = this.head.z.coord * v.head.x.coord - this.head.x.coord * v.head.z.coord;
        double z = this.head.x.coord * v.head.y.coord - this.head.y.coord * v.head.x.coord;

        if (new Point3D(x, y, z).equals(Point3D.ZERO))
            throw new IllegalArgumentException("cross product resulting Zero point head");

        return new Vector(x, y, z);
    }

    /**
     * returns the dot product between the vector and another one
     *
     * @param v - a vector with we multiply the vector
     * @return vector multiply result
     */
    public double dotProduct(Vector v) {
        double x = this.head.x.coord * v.head.x.coord;
        double y = this.head.y.coord * v.head.y.coord;
        double z = this.head.z.coord * v.head.z.coord;

        return (x + y + z);
    }

    /**
     * @return the squared length of the vector
     */
    public double lengthSquared() {
        double x = this.head.x.coord;
        double y = this.head.y.coord;
        double z = this.head.z.coord;

        return (x * x + y * y + z * z);

    }

    /**
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * normalizes the vector
     *
     * @return the vector after he was normalized
     */
    public Vector normalize() {
        double length = this.length(), x = this.head.x.coord, y = this.head.y.coord, z = this.head.z.coord;

        if (length == 0)
            throw new ArithmeticException("can't not divide by zero!\n");

        this.head = new Point3D(x / length, y / length, z / length);

        return this;
    }

    /**
     * @return a new vector which is the normalized version of the vector
     */
    public Vector normalized() {
        Vector v = new Vector(this.head);

        return v.normalize();
    }

    @Override
    public String toString() {
        return this.head.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector vector = (Vector) o;

        return head.equals(vector.head);
    }

    /**
     * RotateByRadians
     * * @see <a href="https://en.wikipedia.org/wiki/Rodrigues%27_rotation_formula">https://en.wikipedia.org/wiki/Rodrigues%27_rotation_formula</a>
     *gets a vector axis that is perpendicular to this and an angle in radians
     * it spins the vector by the amount of radians counter clockwise
     * and returns a new vector
     * @param axis - the vector that we use to rotate around
     * @param angle - the angle we want to rotate
     * @return - a new vector after the rotation
     */
    public Vector RotateByRadians(Vector axis, double angle) {
        Vector v = new Vector(new Point3D(this.head.getX(), this.head.getY(), this.head.getZ()));
        Vector term1 = null, term2 = null;
        double cos=Math.cos(angle),sin=Math.sin(angle);
        if(cos != 0)
            term1 = v.scale(cos);
        if(sin != 0)
            term2 = axis.crossProduct(v).scale(sin);
        //if cosine of angle was zero, then sin has to be non-zero, therefore return the second term
        if(term1 == null)
            return term2;
        //if sine of angle was zero, then cosine has to be non-zero, therefore return the first term
        else if(term2 == null)
            return term1;
        //if both cosine and sine are none zero then return the addition of the terms
        return term1.add(term2);
        //complex calculation to ro tate vec by given angle in radians
    }
}