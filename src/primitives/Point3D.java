package primitives;

/**
 * Point in the 3D space
 * x - x point coordinate
 * y - y point coordinate
 * z - z point coordinate
 * ZERO - zero point
 */

public class Point3D {
    final Coordinate x;
    final Coordinate y;
    final Coordinate z;
    public final static Point3D ZERO = new Point3D(0, 0, 0);

    /**
     * creates a new point
     *
     * @param x - x point coordinate
     * @param y - y point coordinate
     * @param z - z point coordinate
     */
    public Point3D(double x, double y, double z) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
        this.z = new Coordinate(z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return this.x.equals(point3D.x) && this.y.equals(point3D.y) && this.z.equals(point3D.z);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    /**
     * create a vector from p to the point
     *
     * @param p - a point from where the vector begin
     * @return Vector from p to the point
     */
    public Vector subtract(Point3D p) {
        double x = this.x.coord - p.x.coord;
        double y = this.y.coord - p.y.coord;
        double z = this.z.coord - p.z.coord;

        return new Vector(x, y, z);
    }

    /**
     * create a point that is the end of the point when we add him the vector
     *
     * @param v - the vector we want add to the point
     * @return the point we reach after we add to the point the vector
     */
    public Point3D add(Vector v) {
        double x = this.x.coord + v.head.x.coord;
        double y = this.y.coord + v.head.y.coord;
        double z = this.z.coord + v.head.z.coord;

        return new Point3D(x, y, z);
    }

    /**
     * calculate the distance^2 between p and the point
     *
     * @param p the point we want know the distance^2 between him to the point
     * @return the distance^2 between the two points
     */
    public double distanceSquared(Point3D p) {
        double x = p.x.coord - this.x.coord;
        double y = p.y.coord - this.y.coord;
        double z = p.z.coord - this.z.coord;

        return (x * x + y * y + z * z);
    }

    /**
     * calculate the distance between p and the point
     *
     * @param p the point we want know the distance between him to the point
     * @return the distance between the two points
     */
    public double distance(Point3D p) {
        return Math.sqrt(this.distanceSquared(p));
    }
}