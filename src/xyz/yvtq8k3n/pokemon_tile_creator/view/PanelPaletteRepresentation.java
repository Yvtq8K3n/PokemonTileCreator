package xyz.yvtq8k3n.pokemon_tile_creator.view;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController.MAIN_CONTROLLER;

public class PanelPaletteRepresentation extends JPanel implements SelectableBehaviour, MouseListener, MouseMotionListener {
    private final static int[] BOX_DIMENSIONS = {16, 16};
    Color[] palette;

    private boolean hasSelector;
    private int[] selectorLocation;

    public PanelPaletteRepresentation() {
        palette = new Color[0];
        this.hasSelector = false;

        //Add event listeners
        addMouseListener(this);
        addMouseMotionListener(this);
        MAIN_CONTROLLER.addCustomBehaviourComponents(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int count = 0;

        if(palette.length > 0){
            for(int j = 0; j < BOX_DIMENSIONS[1]+2; j += BOX_DIMENSIONS[1]){
                 for(int i = 0; i < BOX_DIMENSIONS[0]*8; i += BOX_DIMENSIONS[0]){
                    g.setColor(palette[count]);
                    g.fillRect(i, j, BOX_DIMENSIONS[0], BOX_DIMENSIONS[1]);
                    count++;
                }
            }
        }
        if(hasSelector){
            g.setColor(Color.BLUE);
            g.drawRect(selectorLocation[0], selectorLocation[1],BOX_DIMENSIONS[0],BOX_DIMENSIONS[1]);
        }
    }

    public void setPalette( Color[] palette) {
        this.palette = palette;
    }

    @Override
    public void mousePressedSelectedAction(int x, int y){
        mouseDraggedSelectedAction(x, y);
    }

    @Override
    public void mouseDraggedSelectedAction(int x, int y){
        hasSelector = true;
        selectorLocation = new int[]{
                x / BOX_DIMENSIONS[0] * BOX_DIMENSIONS[0],
                y / BOX_DIMENSIONS[1] * BOX_DIMENSIONS[1]
        };
        //MainController.setBlock()
        repaint();
    }

    @Override
    public void mouseExitSelectedAction(int x, int y) {
        reset();
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
