package elements;

import primitives.*;
import primitives.Color;

import java.awt.*;

/**
 * class for the point light in the scene
 * position - position of the point light
 * Kc - factor for attenuation with distance
 * Kl - factor for attenuation with distance
 * Kq - factor for attenuation with distance
 */
public class PointLight extends Light implements LightSource {
    Point3D position;
    double Kc = 1;
    double Kl = 0;
    double Kq = 0;

    /**
     * constructor
     *
     * @param c        - intensity of the point light
     * @param position - position of the point light
     */
    public PointLight(Color c, Point3D position) {
        super(c);
        this.position = position;
    }

    /**
     * constructor
     *
     * @param pb - point light builder
     */
    public PointLight(PointLightBuilder pb) {
        super(pb.c);
        this.position = pb.position;
        this.Kc = pb.Kc;
        this.Kl = pb.Kl;
        this.Kq = pb.Kq;
    }

    /**
     * @param p the intersection point between the light and the geometry shape
     * @return intensity of the point light
     */
    @Override
    public Color getIntensity(Point3D p) {
        double d = p.distance(position);
        double ds = p.distanceSquared(position); //ds = d^2

        return super.getIntensity().reduce(Kc + Kl * d + Kq * ds);
    }

    /**
     * set position of the point light
     *
     * @param position - the position of the point light
     * @return the point light
     */
    public PointLight setPosition(Point3D position) {
        this.position = position;
        return this;
    }

    /**
     * set Kc
     *
     * @param kc - factor for attenuation with distance
     * @return - the point light
     */
    public PointLight setKc(double kc) {
        Kc = kc;
        return this;
    }

    /**
     * set Kl
     *
     * @param kl - factor for attenuation with distance
     * @return the point light
     */
    public PointLight setKl(double kl) {
        Kl = kl;
        return this;
    }

    /**
     * set Kq
     *
     * @param kq - factor for attenuation with distance
     * @return the point light
     */
    public PointLight setKq(double kq) {
        Kq = kq;
        return this;
    }

    /**
     * @param p - the intersection point between the light and the geometry shape
     * @return position p vector (the vector that begin at position and end at p)
     */
    @Override
    public Vector getL(Point3D p) {
        return getVecFromPos(p);
    }

    /**
     * @param point - the intersection point between the light and the geometry shape
     * @return the distance between position and p
     */
    @Override
    public double getDistance(Point3D point) {
        return position.distance(point);
    }

    /**
     * @param p - the intersection point between the light and the geometry shape
     * @return position p vector (the vector that begin at position and end at p)
     */
    public Vector getVecFromPos(Point3D p) {
        return p.subtract(position).normalized();
    }

    /**
     * point light builder
     * position - position of the point light
     * Kc - factor for attenuation with distance
     * Kl - factor for attenuation with distance
     * Kq - factor for attenuation with distance
     * c - the intensity of the spot light
     */
    public static class PointLightBuilder {
        Point3D position;
        double Kc = 1;
        double Kl = 0;
        double Kq = 0;
        Color c;

        /**
         * constructor
         *
         * @param p - the intersection point between the light and the geometry shape
         * @param c - the intensity of the spot light
         */
        public PointLightBuilder(Point3D p, Color c) {
            this.position = p;
            this.c = c;
        }

        /**
         * set Kc
         *
         * @param kc - factor for attenuation with distance
         * @return point light builder
         */
        public PointLightBuilder KC(double kc) {
            this.Kc = kc;
            return this;
        }

        /**
         * set Kl
         *
         * @param kl - factor for attenuation with distance
         * @return point light builder
         */
        public PointLightBuilder KL(double kl) {
            this.Kl = kl;
            return this;
        }

        /**
         * set Kq
         *
         * @param kq - factor for attenuation with distance
         * @return point light builder
         */
        public PointLightBuilder KQ(double kq) {
            this.Kq = kq;
            return this;
        }

        /**
         * @return the finally constructed User object
         */
        public PointLight build() {
            PointLight p = new PointLight(this);
            validateUserObject(p);
            return p;
        }

        /**
         * @param p - the intersection point between the light and the geometry shape
         */
        private void validateUserObject(PointLight p) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }
    }
}