package xyz.yvtq8k3n.pokemon_tile_creator.view.panels;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaletteRepresentation extends Representation {
    Color[] palette;

    private boolean hasSelector;
    private int[] selectorLocation;

    public PaletteRepresentation() {
        super();

        palette = new Color[0];
        this.hasSelector = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int count = 0;

        if(palette.length > 0){
            for(int j = 0; j < BLOCK +2; j += BLOCK){
                 for(int i = 0; i < BLOCK *8; i += BLOCK){
                    g.setColor(palette[count]);
                    g.fillRect(i, j, BLOCK, BLOCK);
                    count++;
                }
            }
        }
        if(hasSelector){
            g.setColor(Color.BLUE);
            g.drawRect(selectorLocation[0], selectorLocation[1], BLOCK, BLOCK);
        }
    }

    public void setPalette( Color[] palette) {
        this.palette = palette;
    }

    public void startSelector(int x, int y){
        hasSelector = true;
        moveSelector(x, y);
    }

    public void moveSelector(int x, int y){
        selectorLocation = new int[]{
                x / BLOCK * BLOCK,
                y / BLOCK * BLOCK
        };
        //Given the x and y coordinates calculate the index of the wanted color
        int x1 = Math.min(Math.floorDiv(x, BLOCK), 7);
        x1 = Math.max(x1, 0);
        int y1 = Math.min(Math.floorDiv(y, BLOCK) * 8, 1);
        y1 = Math.max(y1, 0);

        Color selectedColor = palette[x1 + y1];
        MainController.setImageColorFilter(selectedColor);
        repaint();
    }

    public void releaseSelector(int x, int y) {
        MainController.setImageColorFilter(null);
        repaint();
    }


    public void reset(){
        hasSelector = false;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        startSelector(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        releaseSelector(e.getX(), e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        moveSelector(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
