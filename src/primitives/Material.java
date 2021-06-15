package primitives;

import java.lang.reflect.MalformedParametersException;

/**
 * class that represent the material of the geometries
 * kD - diffuse factor
 * kS - specular factor
 * nShininess - the object shininess
 * kT - transparency factor
 * kR - reflection factor
 */
public class Material {
    public double kD = 0;
    public double kS = 0;
    public int nShininess = 0;
    public double kT = 0;
    public double kR = 0;

    /**
     * set kD
     *
     * @param kD - diffuse factor
     * @return the material
     */
    public Material setKd(double kD) {
        this.kD = kD;
        return this;
    }

    /**
     * set kS
     *
     * @param kS - specular factor
     * @return the material
     */
    public Material setKs(double kS) {
        this.kS = kS;
        return this;
    }

    /**
     * set shininess
     *
     * @param nShininess - the object shininess
     * @return the material
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * set kT
     *
     * @param kT - transparency factor
     * @return the material
     */
    public Material setKt(double kT) {
        this.kT = kT;
        return this;
    }

    /**
     * set kR
     *
     * @param kR - reflection factor
     * @return the material
     */
    public Material setKr(double kR) {
        this.kR = kR;
        return this;
    }
}