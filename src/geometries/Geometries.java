package geometries;

import primitives.Box;
import primitives.Point3D;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * general class of geometries shapes
 * geometries - list of geometries shapes
 */
public class Geometries implements Intersectable {
    public List<Intersectable> geometries = null;
    public Box box = new Box(); //bounding box of geometries

    //default constructor - update geometries to a empty linked list
    public Geometries() {
        geometries = new LinkedList<>();
    }

    /**
     * constructor
     * enter to geometries a list of intersectable geometries shapes
     * and build the bounding box
     * @param geometries - list of intersectable geometries shapes
     */
    public Geometries(Intersectable... geometries) {
        this.geometries = new LinkedList<>();
        this.add(geometries);
        box.setGeometriesBox(this.geometries);
    }

    /**
     * add to geometries a list of intersectable geometries shapes
     * and build the new bounding box
     * @param geometries - list of intersectable geometries shapes
     */
    public void add(Intersectable... geometries) {
        for (Intersectable item : geometries) {
            this.geometries.add(item);
        }

        box.setGeometriesBox(this.geometries);
    }

    /**
     * find intersections between the ray and all the intersectable shapes in geometries
     *
     * @param ray - ray with we want to find intersections
     * @return a list with all the intersections points
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> result = null;

        //if(ray.intersect(this.box)) { //check before if the ray intersect the bounding box of geometries
            for (Intersectable item : this.geometries) {
                List<GeoPoint> itemIntersectionPoints = item.findGeoIntersections(ray);
                if (itemIntersectionPoints != null) {
                    if (result == null)
                        result = new LinkedList<>();

                    result.addAll(itemIntersectionPoints);
                }
            }
        //}

        return result;
    }
}