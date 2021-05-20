package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void writeToImage() {
        int nX = 800, nY = 500; //picture 500X800
        int gapX = nX / 16, gapY = nY / 10; //16X10 pixels

        ImageWriter imageWriter = new ImageWriter("blue screen", nX, nY);

        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % gapY == 0 || j % gapX == 0) //draw the black lines of the pixels
                    imageWriter.writePixel(j, i, Color.BLACK);

                else //color the pixels in blue
                    imageWriter.writePixel(j, i, new Color(0, 0, 255));
            }
        }

        imageWriter.writeToImage();
    }
}