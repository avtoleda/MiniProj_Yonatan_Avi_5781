package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Camera {
    private Vector Vup;
    private Point3D point;
    private Vector Vto;
    private Vector Vright;
    double viewplaneW;
    double viewplaneH;
    double distance;

    public Camera setDistance(double distance){
        this.distance=distance;
        return this;
    }
    public Camera setViewPlaneSize(double viewplanew, double viewplaneh) {
        viewplaneW = viewplanew;
        viewplaneH = viewplaneh;
        return this;
    }

    public Point3D getPoint() {
        return point;
    }

    public Vector getVup() {
        return Vup;
    }

    public Vector getVto() {
        return Vto;
    }

    public Vector getVright() {
        return Vright;
    }

    public Camera(Point3D p, Vector vup, Vector vto) {
        if (vup.dotProduct(vto)==0){
            point=p;
            Vup=vup.normalized();
            Vto=vto.normalized();
            Vright=vto.crossProduct(Vup);
            viewplaneW=0;
            viewplaneH=0;
            distance=0;

        }
    }

    public Ray constructRayThroughPixel(int nX, int nY, int j, int i){
        Point3D pc=point.add(Vto.scale(distance));
        double rx,ry;
        rx=viewplaneH/nY;
        ry=viewplaneW/nX;

        Point3D Pij= pc.add(Vright.scale(rx).scale(j-(nX-1)/2).subtract(Vup.scale(ry).scale(i-(nY-1)/2)));
        Ray ray=new Ray(point,Pij.subtract(point));
        return ray;
    }
}
