package primitives;

import java.util.List;

import geometries.Intersectable.GeoPoint;

/**
 * Ray in the 3D space
 * p0 - beginning point of ray
 * dir - ray vector direction
 */
public class Ray {
    private static final double DELTA = 0.1;
    final Point3D p0;
    final Vector dir;

    /**
     * constructor
     * creates a new ray
     *
     * @param p0  - beginning point of ray
     * @param dir - ray vector direction
     */
    public Ray(Point3D p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalized();
    }

    /**
     * constructor
     *
     * @param point          - the beginning point of the ray
     * @param lightDirection - vector in the light direction
     * @param n              - the normal vector
     */
    public Ray(Point3D point, Vector lightDirection, Vector n) {
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        this.p0 = point.add(delta);
        this.dir = lightDirection.normalized();
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

    /**
     * @param points - list of points
     * @return the closest point to P0
     */
    public Point3D findClosestPoint(List<Point3D> points) {
        Point3D minPoint = null;

        if (points != null) {
            double distance = Double.POSITIVE_INFINITY;

            for (Point3D p : points) {
                double h = p.distance(p0);

                if (h < distance) {
                    distance = h;
                    minPoint = p;
                }
            }
        }

        return minPoint;
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

    /**
     * @param t - number with we want to scale v(the direction vector of the ray)
     * @return a point that is equal to p0 + tv
     */
    public Point3D getPoint(double t) {
        return this.p0.add(this.dir.scale(t));
    }

    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {
        GeoPoint minPoint = null;

        if (points != null) {
            double distance = Double.POSITIVE_INFINITY;

            for (GeoPoint p : points) {
                double h = p.point.distance(p0);

                if (h < distance) {
                    distance = h;
                    minPoint = p;
                }
            }
        }

        return minPoint;
    }

    /**
     *
     * @param b - a box
     * @return either is an intersection between the ray and the box or not
     *
     * authors - Amy Williams, Steve Barrus, R. Keith Morley, Peter Shirley
     * thank you! (and thank you to Yehoshua Gronspect that exlain me the algorithm )
     */
    public boolean intersect(Box b) {
        Point3D min = new Point3D(b.minX, b.minY, b.minZ);
        Point3D max = new Point3D(b.maxX, b.maxY, b.maxZ);

        double tmin, tmax, tymin, tymax, tzmin, tzmax;

        if (this.dir.head.x.coord >= 0) {
            tmin = (min.x.coord - this.p0.x.coord) / this.dir.head.x.coord;
            tmax = (max.x.coord - this.p0.x.coord) / this.dir.head.x.coord;
        } else {
            tmin = (max.x.coord - this.p0.x.coord) / this.dir.head.x.coord;
            tmax = (min.x.coord - this.p0.x.coord) / this.dir.head.x.coord;
        }

        if (this.dir.head.y.coord >= 0) {
            tymin = (min.y.coord - this.p0.y.coord) / this.dir.head.y.coord;
            tymax = (max.y.coord - this.p0.y.coord) / this.dir.head.y.coord;
        } else {
            tymin = (max.y.coord - this.p0.y.coord) / this.dir.head.y.coord;
            tymax = (min.y.coord - this.p0.y.coord) / this.dir.head.y.coord;
        }

        if ((tmin > tymax) || (tymin > tmax))
            return false;

        if (tymin > tmin)
            tmin = tymin;

        if (tymax < tmax)
            tmax = tymax;

        if (this.dir.head.z.coord >= 0) {
            tzmin = (min.z.coord - this.p0.z.coord) / this.dir.head.z.coord;
            tzmax = (max.z.coord - this.p0.z.coord) / this.dir.head.z.coord;
        } else {
            tzmin = (max.z.coord - this.p0.z.coord) / this.dir.head.z.coord;
            tzmax = (min.z.coord - this.p0.z.coord) / this.dir.head.z.coord;
        }

        if ((tmin > tzmax) || (tzmin > tmax))
            return false;

        if (tzmin > tmin)
            tmin = tzmin;

        if (tzmax < tmax)
            tmax = tzmax;

        return true;
    }
}