package xyz.yvtq8k3n.pokemon_tile_creator.view.panels;

import xyz.yvtq8k3n.pokemon_tile_creator.HelperCreator;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.MultiSelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.SelectableBehaviour;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class TileRepresentation extends Representation implements SelectableBehaviour, MultiSelectableBehaviour {
    private static final int GRID_BASE = 8;
    private static final int[] GRID_SIZE = {2,1,0};
    int gridIndex;

    public BufferedImage image;

    private boolean hasSelector;
    private boolean hasMultiSelector;
    private int[] initialLocation;
    private int[] selectorLocation;
    private Color filter;

    public TileRepresentation() {
        super();
        this.gridIndex = GRID_SIZE.length-1;
        this.hasSelector = false;
        this.hasMultiSelector = false;

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

            if(hasSelector && selectorLocation!=null){
                g.setColor(Color.BLUE);
                g.drawRect(selectorLocation[0], selectorLocation[1], BLOCK, BLOCK);
            }

            if(hasMultiSelector){
                g.setColor(Color.BLUE);
                int[] minCoordinates = HelperCreator.minCoordinates(initialLocation, selectorLocation);
                int[] maxCoordinates = HelperCreator.maxCoordinates(initialLocation, selectorLocation);
                if (filter != null){
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
                g.setColor(Color.BLUE);
                g.drawRect(minCoordinates[0], minCoordinates[1],
                        maxCoordinates[0] - minCoordinates[0] + BLOCK,
                        maxCoordinates[1] - minCoordinates[1] + BLOCK);
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
        moveSelector(x, y);
        hasSelector = true;
    }

    @Override
    public void moveSelector(int x, int y){
        if (image == null) return;
        //Replace x(0, max) if it's out of viewport
        x = Math.min(x, image.getWidth() - BLOCK);
        x = Math.max(x, 0);

        //Replace y(0, max) if it's out of viewport
        y = Math.min(y, image.getHeight() - BLOCK);
        y = Math.max(y, 0);

        selectorLocation = new int[]{
                BLOCK * Math.floorDiv(x, BLOCK),
                BLOCK * Math.floorDiv(y, BLOCK)
        };
        MainController.setDisplayBlock(image, selectorLocation[0], selectorLocation[1]);
        repaint();
    }
    @Override
    public void releaseSelector(int x, int y) {

    }

    @Override
    public void startMultiSelector(int x, int y) {
        hasMultiSelector = true;

        initialLocation = new int[]{
                BLOCK * Math.floorDiv(x, BLOCK),
                BLOCK * Math.floorDiv(y, BLOCK)
        };
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

        int[] blockSize = new int[]{GRID_SIZE[0] * GRID_BASE, GRID_SIZE[0] * GRID_BASE};
        selectorLocation = new int[]{
                blockSize[0] * Math.floorDiv(x, blockSize[0]),
                blockSize[1] * Math.floorDiv(y, blockSize[1])
        };
        repaint();
    }

    @Override
    public void exitSelectedAction(int x, int y) {

    }

    @Override
    public void reset(){
        hasSelector = false;
        hasMultiSelector = false;
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