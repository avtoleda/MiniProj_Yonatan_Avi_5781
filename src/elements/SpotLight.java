package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import static java.lang.Double.max;

public class SpotLight extends PointLight{
    Vector dir;
    public SpotLight(Color c, Point3D position,Vector dir){
        super(c,position);
        this.dir = dir.normalize();
    }


    @Override
    public Color getIntensity(Point3D p) {

        return super.getIntensity(p).scale(max(0,dir.dotProduct(getVecFromPos(p)))); //mult. by the dot prod or 0
    }

}
