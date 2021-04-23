package geometries;

public abstract class RadialGeometry {
    final protected double radius;

    public RadialGeometry(double radius) {
        this.radius = radius;
    }

    /**
     * @return the radius of the Sphere
     */
    public double getRadius() {
        return this.radius;
    }
}
