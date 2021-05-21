package geometries;

public abstract class RadialGeometry extends Geometry{
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
