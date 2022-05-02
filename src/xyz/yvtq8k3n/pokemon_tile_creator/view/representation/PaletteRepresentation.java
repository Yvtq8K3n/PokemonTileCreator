package xyz.yvtq8k3n.pokemon_tile_creator.view.representation;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.Selector;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.SingleSelector;

import java.awt.*;
import java.awt.event.*;

public class PaletteRepresentation extends Representation {
    private static final int[] PALETTE_BOX = {128, 32};
    Color[] palette;
    private SingleSelector selector;

    public PaletteRepresentation() {
        super();
        palette = new Color[0];
        this.selector = new SingleSelector();
        setPreferredSize(TileHelper.createDimension(PALETTE_BOX));
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
        if(selector.isActive()){
            selector.drawComponent(g);
        }
    }

    public void startSelector(int x, int y){
        selector.setState(Selector.ACTIVE);
        moveSelector(x, y);
    }

    public void moveSelector(int x, int y){
        //Set selector location
        selector.setInitialLocation(x, y);

        //Calculate the index of the wanted color by converting the x and y coordinates
        int x1 = Math.min(Math.floorDiv(x, BLOCK), 7);
        x1 = Math.max(x1, 0);
        int y1 = Math.min(Math.floorDiv(y, BLOCK) * 8, 1);
        y1 = Math.max(y1, 0);
        Color selectedColor = palette[x1 + y1];

        //Apply filter
        MainController.setImageColorFilter(selectedColor);
        repaint();
    }

    public void releaseSelector(int x, int y) {
        MainController.setImageColorFilter(null);
        repaint();
    }

    public void reset(){
        selector.setState(Selector.INACTIVE);
        repaint();
    }

    public void setPalette(Color[] palette) {
        this.palette = palette;
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
    public void mouseDragged(MouseEvent e) {
        moveSelector(e.getX(), e.getY());
    }

}
