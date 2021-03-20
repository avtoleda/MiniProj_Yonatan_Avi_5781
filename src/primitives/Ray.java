package primitives;

/**
 * Ray in the 3D space
 * field1 p0 - beginning point of ray
 * field2 dir - ray vector direction
 */
public class Ray {
    Point3D p0;
    Vector dir;

    /**
     * creates a new ray
     * @param p0 - beginning point of ray
     * @param dir - ray vector direction
     */
    public Ray(Point3D p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalized();
    }

    /**
     * @return beginning point of ray
     */
    public Point3D getP0() {
        return this.p0;
    }

    /**
     * @return ray vector direction
     */
    public Vector getDir() {
        return this.dir;
    }

    @Override
    public String toString() {
        return "beginning point: " + this.p0.toString() + "\nvector: " + this.dir.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }
}