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
    /***
     *we take the pixels and the coordinates and we use the long formula but because we cant scale by 0 e have to separate in to cases
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i){
        Point3D pc=point.add(Vto.scale(distance));
        double rx,ry;
        rx=(double) viewplaneH/nY;
        ry=(double)viewplaneW/nX;
        Point3D Pij;
        if(j-(double)(nX-1)/2!=0 && i-(double)(nY-1)/2 != 0)
            Pij= pc.add((Vright.scale(rx*(j-(double)(nX-1)/2))).subtract((Vup.scale(ry*(i-(double)(nY-1)/2)))));
        else if(j-(double)(nX-1)/2==0 && i-(double)(nY-1)/2 == 0)
            Pij=pc;
        else
            if(j-(double)(nX-1)/2==0)
                Pij=pc.add((Vup.scale(ry*(i-(double)(nY-1)/2))).scale(-1));
            else
                Pij=pc.add((Vright.scale(rx*(j-(double)(nX-1)/2))));
        Ray ray=new Ray(point,Pij.subtract(point));
        return ray;
    }
}
