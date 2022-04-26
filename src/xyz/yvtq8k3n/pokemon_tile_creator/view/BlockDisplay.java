package xyz.yvtq8k3n.pokemon_tile_creator.view;

import xyz.yvtq8k3n.pokemon_tile_creator.HelperCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class BlockDisplay extends JPanel {
    private static final int[] DISPLAY_DIMENSIONS = {128, 128};
    private static final int[] BLOCK = {16, 16};
    private BufferedImage image;
    private int[] coordinates;


    public BlockDisplay() {
       setPreferredSize(HelperCreator.createDimension(DISPLAY_DIMENSIONS));
       setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(image!=null){
            g.drawImage(image, 0, 0, this);
        }

    }

    public void setImage(BufferedImage image, int x, int y) {
        //Crop from image wanted box
        BufferedImage subImage = image.getSubimage(x, y, BLOCK[0], BLOCK[1]);

        //Scale image
        BufferedImage scaledImage = generateScaledImage(subImage);
        this.image = scaledImage;
        this.coordinates = new int[] {x, y};
        repaint();
    }

    private BufferedImage generateScaledImage(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        // Create a new image of the proper size
        int w2 = w * 5;
        int h2 = h * 5;
        BufferedImage after = new BufferedImage(w2, h2, image.getType());
        AffineTransform scaleInstance = AffineTransform.getScaleInstance(5, 5);
        AffineTransformOp scaleOp
                = new AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_BILINEAR);

        Graphics2D g2 = (Graphics2D) after.getGraphics();
        // Here, you may draw anything you want into the new image, but we're just drawing
        // a scaled version of the original image. This is slower than
        // calling scaleOp.filter().
        g2.drawImage(image, scaleOp, 0, 0);
        g2.dispose();
        return after;
    }
}
