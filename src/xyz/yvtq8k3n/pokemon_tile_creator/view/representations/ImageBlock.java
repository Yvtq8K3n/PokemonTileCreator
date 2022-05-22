package xyz.yvtq8k3n.pokemon_tile_creator.view.representations;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

//Maybe I should change this to BlockDisplay and Display abstract class
public class ImageBlock extends BlockRepresentation {
    protected static Color FILTER_BACKGROUND = Color.BLACK;
    private BufferedImage image;
    private boolean enableGrid;
    private Color colorFilter;

    public ImageBlock() {
        super();
        enableGrid = false;
    }

    @Override
    protected void drawRepresentation(Graphics g) {
        g.drawImage(image, 0, 0, this);

        if (colorFilter != null){
            g.setColor(FILTER_BACKGROUND);
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    Color pixelColor = new Color(image.getRGB(i, j));
                    if (!pixelColor.equals(colorFilter)){
                        g.fillRect(i, j, 1,1);
                    }
                }
            }
        }

        if (enableGrid){
            g.setColor(Color.RED);
            for (int i = 0; i < DISPLAY_DIMENSION; i+=DISPLAY_DIMENSION/2) {
                for (int j = 0; j < DISPLAY_DIMENSION; j+=DISPLAY_DIMENSION/2) {
                    g.drawRect(i, j,DISPLAY_DIMENSION/2,DISPLAY_DIMENSION/2);
                }
            }
        }
    }

    public void setImage(BufferedImage image, int x, int y) {
        //Crop from image wanted box
        Point adjusted = TileHelper.blockAdjustment(x, y);
        BufferedImage subImage = image.getSubimage(adjusted.x, adjusted.y, BLOCK, BLOCK);

        //Scale image
        this.image = generateScaledImage(subImage);
        repaint();
    }

    private BufferedImage generateScaledImage(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        // Create a new image of the proper size
        int w2 = w * 8;
        int h2 = h * 8;

        BufferedImage destinationBufferedImage = new BufferedImage(w2, h2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = destinationBufferedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2.drawImage(image, 0, 0, w2, h2, null);
        g2.dispose();
        return destinationBufferedImage;
    }

    public void setColorFilter(Color color) {
        this.colorFilter = color;
        repaint();
    }


    @Override
    public boolean hasRepresentation() {
        return image != null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        enableGrid = !enableGrid;
        repaint();
    }
}