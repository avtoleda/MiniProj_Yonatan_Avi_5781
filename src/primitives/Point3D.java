package primitives;

public class Point3D {
    final Coordinate x;
    final Coordinate y;
    final Coordinate z;
    public final static Point3D ZERO = new Point3D(0,0,0);

    /**
     * creates a coordinate
     * @param x
     * @param y
     * @param z
     */
    public Point3D(double x, double y, double z) {
        this.x = new Coordinate(x);
        this.y = new Coordinate(y);
        this.z = new Coordinate(z);
    }

    /* later in the next level of the project
    public Coordinate getX() {
        return x;
    }

    public Coordinate getY() {
        return y;
    }

    public Coordinate getZ() {
        return z;
    }

    public static Point3D getZERO() {
        return ZERO;
    }
    */

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

    public Vector subtract(Point3D p) {
        double x = this.x.coord - p.x.coord;
        double y = this.y.coord - p.y.coord;
        double z = this.z.coord - p.z.coord;

        return new Vector(x, y, z);
    }

    public Point3D add(Vector v) {
        double x = this.x.coord + v.head.x.coord;
        double y = this.y.coord + v.head.y.coord;
        double z = this.z.coord + v.head.z.coord;

        return new Point3D(x, y, z);
    }

    public double distanceSquared(Point3D p) {
        double x = p.x.coord - this.x.coord;
        double y = p.y.coord - this.y.coord;
        double z = p.z.coord - this.z.coord;

        return (x * x + y * y + z * z);
    }

    public double distance(Point3D p) {
        return Math.sqrt(this.distanceSquared(p));
    }
}