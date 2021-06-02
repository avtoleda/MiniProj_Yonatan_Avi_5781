package elements;

import primitives.*;
import primitives.Color;

import java.awt.*;

public class PointLight extends Light implements LightSource{
    Point3D position;
    double Kc=1,Kl=0,Kq=0;

    public PointLight(Color c, Point3D position) {
        super(c);
        this.position=position;
    }
    public PointLight(PointLightBuilder pb){
        super(pb.c);
        this.position = pb.position;
        this.Kc = pb.Kc;
        this.Kl = pb.Kl;
        this.Kq = pb.Kq;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double d= p.distance(position);
        double ds= p.distanceSquared(position);

        return super.getIntensity().reduce(Kc+Kl*d+Kq*ds);
    }

    public PointLight setPosition(Point3D position) {
        this.position = position;
        return this;
    }

    public PointLight setKc(double kc) {
        Kc = kc;
        return this;
    }

    public PointLight setKl(double kl) {
        Kl = kl;
        return this;
    }

    public PointLight setKq(double kq) {
        Kq = kq;
        return this;
    }

    @Override
    public Vector getL(Point3D p) {
        return getVecFromPos(p);
    }

    @Override
    public double getDistance(Point3D point) {
        return position.distance(point);
    }

    public Vector getVecFromPos(Point3D p) {
        return p.subtract(position).normalized();
    }

    public static class PointLightBuilder
    {
        Point3D position;
        double Kc=1,Kl=0,Kq=0;
        Color c;
        public PointLightBuilder(Point3D p,Color c) {
            this.position = p;
            this.c=c;
        }
        public PointLightBuilder KC(double kc) {
            this.Kc = kc;
            return this;
        }
        public PointLightBuilder KL(double kl) {
            this.Kl = kl;
            return this;
        }
        public PointLightBuilder KQ(double kq) {
            this.Kq = kq;
            return this;
        }
        //Return the finally consrcuted User object
        public PointLight build() {
            PointLight p =  new PointLight(this);
            validateUserObject(p);
            return p;
        }
        private void validateUserObject(PointLight p) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }
    }
}
