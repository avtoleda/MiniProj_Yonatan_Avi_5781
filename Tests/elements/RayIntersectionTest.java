package elements;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import elements.Camera;
import primitives.*;

public class RayIntersectionTest {
    /***
     * each case is a case from the slide show i changed the numbers a little so that it will be easier for me to work with them
     */
    @Test
    void SphereTest(){
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 1,0 ), new Vector(0, 0, -1)).setDistance(1);//up then to
        camera.setViewPlaneSize(3,3);
        //case 1
        Sphere s1=new Sphere(1, new Point3D(0,0,-3));
        assertEquals(rayShooter(s1,camera),2);
        // List<Point3D> l=s1.findIntersections()
        //case 2
        Sphere s2=new Sphere(2.5, new Point3D(0,0,-3));
        assertEquals(rayShooter(s2,camera),18);
        //case 3
        Sphere s3=new Sphere(2, new Point3D(0,0,-2.5));
        assertEquals(rayShooter(s3,camera),10);
        //case 4
        Sphere s4=new Sphere(4, new Point3D(0,0,-1));
        assertEquals(rayShooter(s4,camera),9);
        //case 5
        Sphere s5=new Sphere(0.5, new Point3D(0,0,1));
        assertEquals(rayShooter(s5,camera),0);
    }
    @Test
    void planeTest(){
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1)).setDistance(1);//up then to
        camera.setViewPlaneSize(3,3);
        //case 1
        Plane p1=new Plane(new Point3D(0,2,-3),new Vector(0,0,1));
        assertEquals(rayShooter(p1,camera),9);
        //case 2
        Plane p2=new Plane(new Point3D(0,2,-3),new Vector(0,1,1.5));
        assertEquals(rayShooter(p2,camera),9);
        //case 3
        Plane p3=new Plane(new Point3D(0,2,-3),new Vector(0,1,0.7));
        assertEquals(rayShooter(p3,camera),6);


    }
    @Test
    void TriangleTest(){
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 1, 0), new Vector(0, 0, -1)).setDistance(1);//up then to
        camera.setViewPlaneSize(3,3);
        //case 1
        Triangle t1=new Triangle(new Point3D(0,1,-2), new Point3D(1,-1,-2), new Point3D(-1,-1,-2));
        assertEquals(rayShooter(t1,camera),1);
        //case 2
        Triangle t2=new Triangle(new Point3D(0,20,-2), new Point3D(1,-1,-2), new Point3D(-1,-1,-2));
        assertEquals(rayShooter(t2,camera),2);
    }

    /***
     * we use a 3 by 3 so i just made a loop that runs on each pixel adds to a counter
     * the amount of intersections there are and then sends back the number of intersections in the whole vp
     * @param inters so that for every object that derives from Intersectable it will work
     * @param c
     * @return
     */
    int rayShooter(Intersectable inters, Camera c){
        int count=0;
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
                if (inters.findIntersections(c.constructRayThroughPixel(3,3,j,i))!=null)
                    count+=inters.findIntersections(c.constructRayThroughPixel(3,3,j,i)).size();
            // assertNotNull(s1.findIntersections(camera.constructRayThroughPixel(i,j,i,j)).isEmpty());

        }
        return count;
    }
}
