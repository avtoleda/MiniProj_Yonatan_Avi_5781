package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;

import java.util.MissingResourceException;

public class Render {
    private ImageWriter imageWriter;
    private Camera camera;
    private RayTracerBase rayTracerBase;


    public Render setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    public Render setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }

    public Render setRayTracer(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }

    public void renderImage() {
        try {
            if (this.imageWriter == null)
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");

            if (this.camera == null)
                throw new MissingResourceException("missing resource", Camera.class.getName(), "");

            if (this.rayTracerBase == null)
                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");

            //rendering the image
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();

            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    Ray ray = this.camera.constructRayThroughPixel(nX, nY, j, i);
                    Color pixelColor = this.rayTracerBase.traceRay(ray);
                    this.imageWriter.writePixel(j, i, pixelColor);
                }
            }
        }

        catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
    }

    public void printGrid(int interval, Color color) {
        int nX = this.imageWriter.getNx();
        int nY = this.imageWriter.getNy();

        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if(i % interval == 0 || j % interval == 0)
                    this.imageWriter.writePixel(j, i, color);
            }
        }
    }

    public void writeToImage() {
        if (this.imageWriter == null)
            throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");

        this.imageWriter.writeToImage();
    }
}