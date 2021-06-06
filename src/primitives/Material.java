package primitives;

import java.lang.reflect.MalformedParametersException;

/**
 * class that represent the material of the geometries
 * kD - diffuse factor
 * kS - specular factor
 * nShininess - the object shininess
 * kT -
 * kR -
 */
public class Material {
    public double kD = 0;
    public double kS = 0;
    public int nShininess = 0;
    public double kT = 0;
    public double kR = 0;

    /**
     *
     * @param kD
     */
    public Material setKd(double kD) {
        this.kD = kD;
        return this;
    }

    /**
     *
     * @param kS
     */
    public Material setKs(double kS) {
        this.kS = kS;
        return this;
    }

    /**
     *
     * @param nShininess
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     *
     * @param kT
     * @return
     */
    public Material setKt(double kT) {
        this.kT = kT;
        return this;
    }

    /**
     *
     * @param kR
     * @return
     */
    public Material setKr(double kR) {
        this.kR = kR;
        return this;
    }
}
