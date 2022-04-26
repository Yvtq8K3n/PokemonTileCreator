package xyz.yvtq8k3n.pokemon_tile_creator.view;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;
import xyz.yvtq8k3n.pokemon_tile_creator.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.operators.SelectorOperator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import static xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController.MAIN_CONTROLLER;

public class PanelTileRepresentation extends JPanel implements CustomBehaviour, MouseListener, MouseMotionListener {
    private static final int[] GRID_DIMENSIONS = {8,8};
    private static final int[] GRID_SIZE = {2,1,0};

    public BufferedImage image;
    int gridIndex;

    private boolean hasSelector;
    private int[] selectorLocation;

    public PanelTileRepresentation() {
        this.gridIndex = GRID_SIZE.length-1;
        this.hasSelector = false;

        //Add event listeners
        addMouseListener(this);
        addMouseMotionListener(this);
        MAIN_CONTROLLER.addCustomBehaviourComponents(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
        if (image != null && GRID_SIZE[gridIndex] > 0){
            for (int i = 0; i < image.getWidth(); i+= GRID_DIMENSIONS[0] * GRID_SIZE[gridIndex]) {
                for (int j = 0; j < image.getHeight(); j+= GRID_DIMENSIONS[1] * GRID_SIZE[gridIndex]) {
                    g.setColor(Color.RED);
                    g.drawRect(i, j, GRID_DIMENSIONS[0]*GRID_SIZE[gridIndex],
                            GRID_DIMENSIONS[1]*GRID_SIZE[gridIndex]);
                }
            }
        }
        if(hasSelector){
            g.setColor(Color.BLUE);
            g.drawRect(selectorLocation[0], selectorLocation[1],
                    GRID_SIZE[0] * GRID_DIMENSIONS[0],
                    GRID_SIZE[0] * GRID_DIMENSIONS[1]
            );
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void changeGridIndex() {
        this.gridIndex++;
        if(gridIndex>=GRID_SIZE.length) this.gridIndex = 0;
        this.repaint();
    }

    @Override
    public void customAction(int x, int y){
        hasSelector = true;
        selectorLocation = new int[]{
                x / (GRID_DIMENSIONS[0] * 2) * GRID_DIMENSIONS[0] *2,
                y / (GRID_DIMENSIONS[1] * 2) * GRID_DIMENSIONS[1] *2
        };
        MainController.setDisplayBlock(image, selectorLocation[0], selectorLocation[1]);
        repaint();
    }

    @Override
    public void reset(){
        hasSelector = false;
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
