package primitives;

import java.util.Objects;

public class Ray {
    Point3D p0;
    Vector dir;

    public Ray(Point3D p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalized();
    }

    public Point3D getP0() {
        return this.p0;
    }

    public Vector getDir() {
        return this.dir;
    }

    @Override
    public String toString() {
        return "begining point: " + this.p0.toString() + "\nvector: " + this.dir.toString();
    }

    /**
     * checks if the rays are equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }
}