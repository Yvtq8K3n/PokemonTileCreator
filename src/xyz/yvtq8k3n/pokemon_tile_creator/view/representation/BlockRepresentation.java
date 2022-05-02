package xyz.yvtq8k3n.pokemon_tile_creator.view.representation;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class BlockRepresentation extends Representation {
    private static final int[] DISPLAY_DIMENSIONS = {128, 128};
    private static final int BLOCK = 16;
    private BufferedImage image;
    private int[] initialLocation;
    private Color filter;
    private boolean enableGrid;

    public BlockRepresentation() {
        enableGrid = false;
        filter = null;
        setPreferredSize(TileHelper.createDimension(DISPLAY_DIMENSIONS));
        setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            g.drawImage(image, 0, 0, this);

            if (filter != null){
                g.setColor(Color.BLACK);
                for (int i = 0; i < image.getWidth(); i++) {
                    for (int j = 0; j < image.getHeight(); j++) {
                        Color pixelColor = new Color(image.getRGB(i, j));
                        if (!pixelColor.equals(filter)){
                            g.fillRect(i, j, 1,1);
                        }
                    }
                }
            }

            if (enableGrid){
                g.setColor(Color.RED);
                for (int i = 0; i < DISPLAY_DIMENSIONS[0]; i+=DISPLAY_DIMENSIONS[0]/2) {
                    for (int j = 0; j < DISPLAY_DIMENSIONS[1]; j+=DISPLAY_DIMENSIONS[1]/2) {
                        g.drawRect(i, j,DISPLAY_DIMENSIONS[0]/2,DISPLAY_DIMENSIONS[1]/2);
                    }
                }
            }
        }
    }

    public void setImage(BufferedImage image, int x, int y) {
        //Replace x(0, max) if it's out of viewport
        x = Math.min(x, image.getWidth() - BLOCK);
        x = Math.max(x, 0);

        //Replace y(0, max) if it's out of viewport
        y = Math.min(y, image.getHeight() - BLOCK);
        y = Math.max(y, 0);

        //Crop from image wanted box
        this.initialLocation = new int[]{x, y};
        BufferedImage subImage = image.getSubimage(x, y, BLOCK, BLOCK);

        //Scale image
        this.image = generateScaledImage(subImage);
        repaint();
    }

    public void setImage(BufferedImage image) {
        if (initialLocation!=null){
            setImage(image, initialLocation[0], initialLocation[1]);
        }
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
        this.filter = color;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        enableGrid = !enableGrid;
        repaint();
    }
}