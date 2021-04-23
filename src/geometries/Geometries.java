package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    List<Intersectable> geometries = null;

    public Geometries() {
        geometries = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries) {
        this.geometries = new LinkedList<>();
        this.add(geometries);
    }

    public void add(Intersectable... geometries) {
        for (Intersectable item : geometries) {
            this.geometries.add(item);
        }
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> result = null;

        for (Intersectable item: this.geometries) {
            List<Point3D> itemIntersectionPoints = item.findIntersections(ray);

            if(itemIntersectionPoints != null) {
                if(result == null)
                    result = new LinkedList<>();

                result.addAll(itemIntersectionPoints);
            }
        }

        return result;
    }
}
