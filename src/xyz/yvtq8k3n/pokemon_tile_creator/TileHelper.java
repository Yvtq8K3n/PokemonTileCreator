package xyz.yvtq8k3n.pokemon_tile_creator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static xyz.yvtq8k3n.pokemon_tile_creator.ColorHelper.COLOR_STEP;
import static xyz.yvtq8k3n.pokemon_tile_creator.model.Tileset.PALETTE_LIMIT;

public abstract class TileHelper {
    protected final static int BLOCK = 16;
    protected final static int STEP = 16;

    public static Dimension createDimension(int[] dimension){
        return new Dimension(dimension[0], dimension[1]);
    }

    public static BufferedImage readImage(File file) {
        BufferedImage image = null;
        try{
            BufferedImage imageFile = ImageIO.read(file);

            for (int x = 0; x < imageFile.getWidth(); x++) {
                for (int y = 0; y < imageFile.getHeight(); y++) {
                    Color pixelColor = new Color(imageFile.getRGB(x, y));
                    imageFile.setRGB(x, y, ColorHelper.round(pixelColor));
                }
            }

            //Create a new Buffer image without Alpha
            image = new BufferedImage(imageFile.getWidth(), imageFile.getHeight(), BufferedImage.TYPE_INT_RGB);
            image.createGraphics().drawImage(imageFile, 0, 0, Color.WHITE, null);
        } catch (IOException e) {
            e.printStackTrace();
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

    public static Color[] readPalette(byte[] palette) {
        Color[] colors = new Color[PALETTE_LIMIT];

        int counter = 0;
        for (int i=0;i<palette.length;i+=4) {
            byte blue = palette[i];
            byte green = palette[i+1];
            byte red = palette[i+2];
            colors [counter] = new Color(red & 0xFF, green & 0xFF, blue & 0xFF);
            counter++;
        }
       return colors;
    }

    //Converts the palette to a writable byte[]
    public static byte[] getWritablePalette(Color[] palette) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (Color color: palette) {
            outputStream.write(color.getBlue());
            outputStream.write(color.getGreen());
            outputStream.write(color.getRed());
            outputStream.write(color.getAlpha());
        }
        return outputStream.toByteArray();
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
        return scaledX + scaledY * COLOR_STEP;
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

