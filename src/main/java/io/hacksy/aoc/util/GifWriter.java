package io.hacksy.aoc.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GifWriter {
    private BufferedImage bufferedImage;
    private int multiplier;

    public GifWriter(int width, int height, int multiplier) {
        this.bufferedImage = new BufferedImage(width * multiplier, height * multiplier, BufferedImage.TYPE_BYTE_BINARY);
        this.multiplier = multiplier;
        bufferedImage.createGraphics();
    }

    public GifWriter addPixel(int x, int y, boolean pixel) {
        var graphics = bufferedImage.getGraphics();
        if (pixel) {
            graphics.fillRect(x * multiplier, y * multiplier, multiplier, multiplier);
        } else {
            graphics.clearRect(x * multiplier, y * multiplier, multiplier, multiplier);
        }
        return this;
    }

    public void write(String fileName) {
        try {
            ImageIO.write(bufferedImage, "gif", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
