package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * The Camera of the scene
 * point - the beginning point of the camera
 * Vup - vector in the upward direction from the camera
 * Vto - vector in a straight line from the camera
 * Vright - vector in the right direction from the camera
 * viewplaneW - the width of the view plane
 * viewplaneH - the height of the view plane
 * distance - the distance between the camera and the view plane
 */
public class Camera {
    private Point3D point;
    private Vector Vup;
    private Vector Vto;
    private Vector Vright;
    double viewplaneW;
    double viewplaneH;
    double distance;

    /**
     * constructor
     *
     * @param p   - the beginning point of the camera
     * @param vto - vector in a straight line from the camera
     * @param vup - vector in the upward direction from the camera
     */
    public Camera(Point3D p, Vector vto, Vector vup) {
        if (isZero(vto.dotProduct(vup))) {
            point = p;
            Vup = vup.normalized();
            Vto = vto.normalized();
            Vright = vto.crossProduct(Vup).normalized();
            viewplaneW = 0;
            viewplaneH = 0;
            distance = 0;
        }
    }

    /**
     * @return the beginning point of the camera
     */
    public Point3D getPoint() {
        return this.point;
    }

    /**
     * @return vector in the upward direction from the camera
     */
    public Vector getVup() {
        return this.Vup;
    }

    /**
     * @return vector in a straight line from the camera
     */
    public Vector getVto() {
        return this.Vto;
    }

    /**
     * @return vector in the right direction from the camera
     */
    public Vector getVright() {
        return this.Vright;
    }

    /**
     * set the view plane size
     *
     * @param width  - the width of the view plane
     * @param height - the height of the view plane
     * @return the camera
     */
    public Camera setViewPlaneSize(double width, double height) {
        this.viewplaneW = width;
        this.viewplaneH = height;
        return this;
    }

    /**
     * set the distance between the camera and the view plane
     *
     * @param distance - the distance between the camera and the view plane
     * @return the camera
     */
    public Camera setDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * construct a ray from the camera through the middle of (i, j) pixel
     *
     * @param nX - the number of pixels in one row (= the number of pixel columns)
     * @param nY - the number of pixels in one column (= the number of pixel rows)
     * @param j  - the column of the pixel
     * @param i  - the row of the pixel
     * @return the ray throughout the pixel(i, j)
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point3D pc = point.add(Vto.scale(distance)); //pc = point + Vto * distance

        double Ry = viewplaneH / nY; //Ry = height / nY
        double Rx = viewplaneW / nX; //Rx = width / nX

        double Yi = -(i - (nY - 1) / 2d) * Ry;
        double Xj = (j - (nX - 1) / 2d) * Rx;

        Point3D Pij = pc;

        if (isZero(Yi) && isZero(Xj)) {
            return new Ray(point, pc.subtract(point));
        }

        if (isZero(Xj)) { //we don't need to add Xj * Vright to pij because it's equal zero
            Pij = pc.add(Vup.scale(Yi));
            return new Ray(point, Pij.subtract(point));
        }

        if (isZero(Yi)) { //we don't need to add Yi * Vup to pij because it's equal zero
            Pij = pc.add(Vright.scale(Xj));
            return new Ray(point, Pij.subtract(point));
        }

        Pij = pc.add(Vright.scale(Xj).add(Vup.scale(Yi)));

        return new Ray(point, Pij.subtract(point));
    }
}