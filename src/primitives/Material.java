package primitives;

import java.lang.reflect.MalformedParametersException;

public class Material {
    public double kD,kS;
    public int nShininess;

    /**
     *
     * @param kD
     */
    public void setkD(double kD) {
        this.kD = kD;
    }

    /**
     *
     * @param kS
     */
    public void setkS(double kS) {
        this.kS = kS;
    }

    /**
     *
     * @param nShininess
     */
    public void setnShininess(int nShininess) {
        this.nShininess = nShininess;
    }

    public double getkD() {
        return kD;
    }

    public double getkS() {
        return kS;
    }

    public int getnShininess() {
        return nShininess;
    }

    /**
     *
     * @param mb
     */
    public Material(MaterialBuilder mb){
        this.kD=mb.kD;
        this.kS=mb.kS;
        this.nShininess=mb.nShininess;
    }

    /**
     * builder class
     */
    public static class MaterialBuilder
    {
        public double kD,kS;
        public int nShininess;

        public MaterialBuilder() {
        }
        public MaterialBuilder kD(double kD) {
            this.kD = kD;
            return this;
        }
        public MaterialBuilder kS(double kS) {
            this.kS = kS;
            return this;
        }
        public MaterialBuilder nShininess(int nShininess) {
            this.nShininess = nShininess;
            return this;
        }
        //Return the finally consrcuted User object
        public Material build() {
            Material m =  new Material(this);
            validateMObject(m);
            return m;
        }
        private void validateMObject(Material m) {
            //Do some basic validations to check
            //if m object does not break any assumption of system
        }
    }
}
