package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {
    private Point3D point;

    private Vector Vup;
    private Vector Vto;
    private Vector Vright;

    double viewplaneW;
    double viewplaneH;
    double distance;

    public Camera(Point3D p, Vector vto, Vector vup) {
         if(isZero(vto.dotProduct(vup))){
            point = p;
            Vup = vup.normalized();
            Vto = vto.normalized();
            Vright = vto.crossProduct(Vup).normalized();
            viewplaneW = 0;
            viewplaneH = 0;
            distance = 0;
        }
    }

    public Point3D getPoint() {
        return this.point;
    }

    public Vector getVup() {
        return this.Vup;
    }

    public Vector getVto() {
        return this.Vto;
    }

    public Vector getVright() {
        return this.Vright;
    }

    public Camera setViewPlaneSize(double width, double height) {
        this.viewplaneW = width;
        this.viewplaneH = height;
        return this;
    }

    public Camera setDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /***
     *we take the pixels and the coordinates and we use the long formula but because we cant scale by 0 e have to separate in to cases
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
//        Point3D pc = point.add(Vto.scale(distance));
//        double rx, ry;
//
//        rx = (double) viewplaneH / nY;
//        ry = (double) viewplaneW / nX;
//
//        Point3D Pij;
//
//        if (j - (double) (nX - 1) / 2 != 0 && i - (double) (nY - 1) / 2 != 0)
//            Pij = pc.add((Vright.scale(rx * (j - (double) (nX - 1) / 2))).subtract((Vup.scale(ry * (i - (double) (nY - 1) / 2)))));
//
//        else if (j - (double) (nX - 1) / 2 == 0 && i - (double) (nY - 1) / 2 == 0)
//            Pij = pc;
//
//        else if (j - (double) (nX - 1) / 2 == 0)
//            Pij = pc.add((Vup.scale(ry * (i - (double) (nY - 1) / 2))).scale(-1));
//
//        else
//            Pij = pc.add((Vright.scale(rx * (j - (double) (nX - 1) / 2))));
//
//        Ray ray = new Ray(point, Pij.subtract(point));
//
//        return ray;

        Point3D pc = point.add(Vto.scale(distance));

        double Ry = viewplaneH / nY;
        double Rx = viewplaneW / nX;

        double Yi = -(i - (nY - 1) / 2d) * Ry;
        double Xj = (j - (nX - 1) / 2d) * Rx;

        Point3D Pij = pc;

        if(isZero(Yi) && isZero(Xj)) {
            return new Ray(point, pc.subtract(point));
        }

        if(isZero(Xj)) {
            Pij = pc.add(Vup.scale(Yi));
            return new Ray(point, Pij.subtract(point));
        }

        if(isZero(Yi)) {
            Pij = pc.add(Vright.scale(Xj));
            return new Ray(point, Pij.subtract(point));
        }

        Pij = pc.add(Vright.scale(Xj).add(Vup.scale(Yi)));

        return new Ray(point, Pij.subtract(point));
    }
}
