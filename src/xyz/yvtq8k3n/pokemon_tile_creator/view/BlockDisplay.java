package xyz.yvtq8k3n.pokemon_tile_creator.view;

import xyz.yvtq8k3n.pokemon_tile_creator.HelperCreator;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.SelectableBehaviour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import static xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController.MAIN_CONTROLLER;

public class BlockDisplay extends JPanel implements SelectableBehaviour, MouseListener, MouseMotionListener {
    private static final int[] DISPLAY_DIMENSIONS = {128, 128};
    private static final int[] BLOCK = {16, 16};
    private BufferedImage image;
    private boolean enableGrid;

    public BlockDisplay() {
        enableGrid = false;
        setPreferredSize(HelperCreator.createDimension(DISPLAY_DIMENSIONS));
        setBorder(BorderFactory.createLineBorder(Color.RED));

        //Add event listeners
        addMouseListener(this);
        addMouseMotionListener(this);
        MainController.addCustomBehaviourComponents(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
            if (enableGrid){
                g.setColor(Color.RED);
                for (int i = 0; i < DISPLAY_DIMENSIONS[0]; i+=DISPLAY_DIMENSIONS[0]/2) {
                    for (int j = 0; j < DISPLAY_DIMENSIONS[1]; j+=DISPLAY_DIMENSIONS[1]/2) {
                        g.drawRect(i, j,DISPLAY_DIMENSIONS[0]/2,DISPLAY_DIMENSIONS[1]/2
                        );
                    }
                }
            }
        }

    }

    public void setImage(BufferedImage image, int x, int y) {
        //Crop from image wanted box
        BufferedImage subImage = image.getSubimage(x, y, BLOCK[0], BLOCK[1]);

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

    @Override
    public void mousePressedSelectedAction(int x, int y) {
        enableGrid = !enableGrid;
        repaint();
    }

    @Override
    public void mouseDraggedSelectedAction(int x, int y) {

    }

    @Override
    public void mouseExitSelectedAction(int x, int y) {

    }

    @Override
    public void reset(){
        enableGrid = false;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MainController.selectedOperator.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MainController.selectedOperator.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MainController.selectedOperator.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        MainController.selectedOperator.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        MainController.selectedOperator.mouseExited(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MainController.selectedOperator.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MainController.selectedOperator.mouseMoved(e);
    }
}