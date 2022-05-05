package xyz.yvtq8k3n.pokemon_tile_creator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static xyz.yvtq8k3n.pokemon_tile_creator.ColorHelper.COLOR_STEP;

public abstract class TileHelper {
    protected final static int BLOCK = 16;
    protected final static int STEP = 16;

    public static Dimension createDimension(int[] dimension){
        return new Dimension(dimension[0], dimension[1]);
    }

    public static BufferedImage readImage(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                image.setRGB(x, y, ColorHelper.round(pixelColor));
            }
        }
        return image;
    }

    public static BufferedImage copyImage(BufferedImage source){
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics2D g2 = b.createGraphics();
        g2.drawImage(source, 0, 0, null);
        g2.dispose();
        return b;
    }


    /** Calculates the min x and y coordinates between 2 points
     * @return the smallest point
     */
    public static int [] minCoordinates(int[] pointA, int[] pointB){
        int minX = Math.min(pointA[0], pointB[0]);
        int minY = Math.min(pointA[1], pointB[1]);
        return new int[]{minX, minY};
    }

    /** Calculates the max x and y coordinates between 2 points
     * @return the highest point
     */
    public static int [] maxCoordinates(int[] pointA, int[] pointB){
        int minX = Math.max(pointA[0], pointB[0]);
        int minY = Math.max(pointA[1], pointB[1]);
        return new int[]{minX, minY};
    }


    /** Given an
     * @param x coordinate
     * @param y coordinate
     * @return a Point that its multiple of BLOCK
     */
    public static Point blockAdjustment(int x, int y){
        return new Point(BLOCK * Math.floorDiv(x, BLOCK),BLOCK * Math.floorDiv(y, BLOCK));
    }

    /** Maps the
     * @param x coordinates
     * @param y coordinates
     * @return the correspondent 1D index value
     */
    public static int calculateColorsIndex(int x, int y){
        Point startCoordinate = TileHelper.blockAdjustment(x, y);
        int scaledX = startCoordinate.x / BLOCK;
        int scaledY = startCoordinate.y / BLOCK;
        int index = scaledX + scaledY * COLOR_STEP;
        return index;
    }


    /** Given an
     * @param index value
     * @return the corresponding X,Y Coordinates
     */
    public static Point calculateXYCoordinates(int index){
        int upScaledX = index % 8 * BLOCK;
        int upScaledY = index / 8 * BLOCK;
        return new Point(upScaledX, upScaledY);
    }
}

