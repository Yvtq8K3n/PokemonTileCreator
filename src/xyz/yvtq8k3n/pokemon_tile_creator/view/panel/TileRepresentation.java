package xyz.yvtq8k3n.pokemon_tile_creator.view.panel;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.AreaSelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.MultiSelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.SelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.AreaSelector;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.MultiSelector;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.Selector;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.SingleSelector;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class TileRepresentation extends Representation implements SelectableBehaviour, AreaSelectableBehaviour, MultiSelectableBehaviour {
    private static final int GRID_BASE = 8;
    private static final int[] GRID_SIZE = {2,1,0};
    int gridIndex;

    public BufferedImage image;
    private SingleSelector singleSelector;
    private AreaSelector areaSelector;
    private MultiSelector multiSelector;

    public TileRepresentation() {
        super();
        this.gridIndex = GRID_SIZE.length-1;
        this.singleSelector = new SingleSelector();
        this.areaSelector = new AreaSelector();
        this.multiSelector = new MultiSelector();

        //Add event listeners
        MainController.addSelectableBehaviour(this);
        MainController.addAreaSelectableBehaviour(this);
        MainController.addMultiSelectableBehaviour(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);

            if(singleSelector.isActive()){
                singleSelector.drawComponent(g);
            }

            if(areaSelector.isActive()){
                areaSelector.drawComponent(g);
            }

            if (multiSelector.isActive()){
                multiSelector.drawComponent(g);
            }
        }
    }

    public void setColorFilter(Color color) {
        areaSelector.setFilter(color);
        multiSelector.setFilter(color);
        repaint();
    }

    public void changeGridIndex() {
        this.gridIndex++;
        if(gridIndex>=GRID_SIZE.length) this.gridIndex = 0;
        singleSelector.setGridSize(GRID_SIZE[gridIndex] * 8);
        areaSelector.setGridSize(GRID_SIZE[gridIndex] * 8);
        multiSelector.setGridSize(GRID_SIZE[gridIndex] * 8);
        this.repaint();
    }

    @Override
    public void startSelector(int x, int y){
        moveSelector(x, y);
    }

    @Override
    public void moveSelector(int x, int y){
        if (image == null) return;

        //Set selector location
        singleSelector.setInitialLocation(x, y);

        MainController.setDisplayBlock(image, singleSelector.getInitialLocation());
        repaint();
    }

    @Override
    public void startAreaSelector(int x, int y) {
        areaSelector.setImage(image);
        areaSelector.setInitialLocation(x, y);
        resizeAreaSelector(x, y);
    }

    @Override
    public void resizeAreaSelector(int x, int y) {
        multiSelector.setImage(image);
        areaSelector.resizeSelector(x, y);
        repaint();
    }

    @Override
    public void addMultiSelectorPoint(int x, int y) {
        multiSelector.setImage(image);
        multiSelector.addSelectionEntry(x, y);
        repaint();
    }

    @Override
    public void removeMultiSelectorPoint(int x, int y) {
        multiSelector.removeSelectionEntry(x, y);
        repaint();
    }


    @Override
    public void reset(){
        singleSelector.setState(Selector.INACTIVE);
        areaSelector.setState(Selector.INACTIVE);
        multiSelector.setState(Selector.INACTIVE);
        repaint();
    }

    public void setImage(BufferedImage image) {
        this.image = image;
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
    public void mouseDragged(MouseEvent e) {
        MainController.selectedOperator.mouseDragged(e);
    }
}
