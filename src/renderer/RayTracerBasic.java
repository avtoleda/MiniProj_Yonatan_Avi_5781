package renderer;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase{
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<Point3D> intersections = this.scene.geometries.findIntersections(ray);

        if(intersections != null) {
            Point3D closestPoint = ray.findClosestPoint(intersections);
            return calcColor(closestPoint);
        }

        //ray didn't intersect any geometrical object
        return this.scene.background;
    }

    private Color calcColor(Point3D p) {
        return this.scene.ambientLight.getIntensity();
    }
}
