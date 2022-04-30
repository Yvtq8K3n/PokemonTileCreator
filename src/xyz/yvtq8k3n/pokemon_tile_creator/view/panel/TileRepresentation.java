package xyz.yvtq8k3n.pokemon_tile_creator.view.panel;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.MultiSelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.SelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.MultiSelector;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.Selector;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.SingleSelector;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class TileRepresentation extends Representation implements SelectableBehaviour, MultiSelectableBehaviour {
    private static final int GRID_BASE = 8;
    private static final int[] GRID_SIZE = {2,1,0};
    int gridIndex;

    public BufferedImage image;
    private SingleSelector singleSelector;
    private MultiSelector multiSelector;
    private Color filter;

    public TileRepresentation() {
        super();
        this.gridIndex = GRID_SIZE.length-1;
        this.singleSelector = new SingleSelector();
        this.multiSelector = new MultiSelector();

        //Add event listeners
        MainController.addSelectableBehaviour(this);
        MainController.addMultiSelectableBehaviour(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);

            if (GRID_SIZE[gridIndex] > 0) {
                for (int i = 0; i < image.getWidth(); i += GRID_BASE * GRID_SIZE[gridIndex]) {
                    for (int j = 0; j < image.getHeight(); j += GRID_BASE * GRID_SIZE[gridIndex]) {
                        g.setColor(Color.RED);
                        g.drawRect(i, j, GRID_BASE * GRID_SIZE[gridIndex],
                                GRID_BASE * GRID_SIZE[gridIndex]);
                    }
                }
            }

            if(singleSelector.isActive()){
                singleSelector.drawComponent(g);
            }

            if(multiSelector.isActive()){
                if (filter != null){
                    int[] minCoordinates = multiSelector.getStartingPoint();
                    int[] maxCoordinates = multiSelector.getEndingPoint();

                    g.setColor(Color.BLACK);
                    for (int i = minCoordinates[0]; i < maxCoordinates[0] + BLOCK; i++) {
                        for (int j = minCoordinates[1]; j < maxCoordinates[1] + BLOCK; j++) {
                            Color pixelColor = new Color(image.getRGB(i, j));
                            if (!pixelColor.equals(filter)){
                                g.fillRect(i, j, 1,1);
                            }
                        }
                    }
                }

                if (GRID_SIZE[gridIndex] > 0) {
                    for (int i = 0; i < image.getWidth(); i += GRID_BASE * GRID_SIZE[gridIndex]) {
                        for (int j = 0; j < image.getHeight(); j += GRID_BASE * GRID_SIZE[gridIndex]) {
                            g.setColor(Color.RED);
                            g.drawRect(i, j, GRID_BASE * GRID_SIZE[gridIndex],
                                    GRID_BASE * GRID_SIZE[gridIndex]);
                        }
                    }
                }
                multiSelector.drawComponent(g);
            }
        }
    }

    public void setColorFilter(Color color) {
        this.filter = color;
        repaint();
    }


    public void changeGridIndex() {
        this.gridIndex++;
        if(gridIndex>=GRID_SIZE.length) this.gridIndex = 0;
        this.repaint();
    }

    @Override
    public void startSelector(int x, int y){
        singleSelector.setState(Selector.ACTIVE);
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
    public void releaseSelector(int x, int y) {

    }

    @Override
    public void startMultiSelector(int x, int y) {
        multiSelector.setInitialLocation(x, y);
        resizeMultiSelector(x, y);
    }

    @Override
    public void resizeMultiSelector(int x, int y) {
        //Replace x(0, max) if it's out of viewport
        x = Math.min(x, image.getWidth() - BLOCK);
        x = Math.max(x, 0);

        //Replace y(0, max) if it's out of viewport
        y = Math.min(y, image.getHeight() - BLOCK);
        y = Math.max(y, 0);

        multiSelector.resizeSelector(x, y);
        repaint();
    }

    @Override
    public void exitSelectedAction(int x, int y) {

    }

    @Override
    public void reset(){
        singleSelector.setState(Selector.INACTIVE);
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
