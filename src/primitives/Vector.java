package primitives;

public class Vector {
    Point3D head;

    public Vector(double x, double y, double z) {
        this.head = new Point3D(x, y, z);

        if(this.head.equals(0))
            throw new IllegalArgumentException("Vector 0 is impossible!\n");
    }

    public Vector(Point3D p) {
        this.head = p;

        if(this.head.equals(0))
            throw new IllegalArgumentException("Vector 0 is impossible!\n");
    }

    public Point3D getHead() {
        return this.head;
    }

    public Vector subtract(Vector v) {
        double x = this.head.x.coord - v.head.x.coord;
        double y = this.head.y.coord - v.head.y.coord;
        double z = this.head.z.coord - v.head.z.coord;

        return new Vector(x, y, z);
    }

    public Vector add(Vector v) {
        double x = this.head.x.coord + v.head.x.coord;
        double y = this.head.y.coord + v.head.y.coord;
        double z = this.head.z.coord + v.head.z.coord;

        return new Vector(x, y, z);
    }

    public Vector scale(double n) {
        double x = this.head.x.coord * n;
        double y = this.head.y.coord * n;
        double z = this.head.z.coord * n;

        return new Vector(x, y, z);
    }

    public Vector crossProduct(Vector v) {
        double x = this.head.y.coord * v.head.z.coord - this.head.z.coord * v.head.y.coord;
        double y = this.head.z.coord * v.head.x.coord - this.head.x.coord * v.head.z.coord;
        double z = this.head.x.coord * v.head.y.coord - this.head.y.coord * v.head.x.coord;

        return new Vector(x, y, z);
    }

    public double dotProduct(Vector v) {
        double x = this.head.x.coord * v.head.x.coord;
        double y = this.head.y.coord * v.head.y.coord;
        double z = this.head.z.coord * v.head.z.coord;

        return (x + y + z);
    }

    public double lengthSquared() {
        double x = this.head.x.coord;
        double y = this.head.y.coord;
        double z = this.head.z.coord;

        return (x * x + y * y + z * z);

    }

    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    public Vector normalize() {
        double length = this.length(), x = this.head.x.coord, y = this.head.y.coord, z = this.head.z.coord;
        this.head = new Point3D(x / length, y / length, z / length);

        return this;
    }

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
}