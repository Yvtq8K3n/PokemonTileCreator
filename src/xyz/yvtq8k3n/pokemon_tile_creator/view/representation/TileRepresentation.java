package xyz.yvtq8k3n.pokemon_tile_creator.view.representation;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileRepresentation extends SelectableRepresentation {
    private static final int[] IMG_DIMENSIONS = {128, 320};
    protected static Color GRID_COLOR = Color.RED;
    private static final int[] GRID_SIZE = {16,8,0};

    public BufferedImage image;
    protected int gridSize;

    public TileRepresentation() {
        super();
        setPreferredSize(TileHelper.createDimension(IMG_DIMENSIONS));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (hasRepresentation()) {
            g.drawImage(image, 0, 0, this);

            //Draw Grid
            drawGridComponent(g);

            //Draw Selectors
            paintSelectors(g);
        }
    }

    protected void drawGridComponent(Graphics g){
        if (hasGridSize()) {
            g.setColor(GRID_COLOR);
            for (int i = 0; i < image.getWidth(); i += this.gridSize) {
                for (int j = 0; j < image.getHeight(); j += this.gridSize) {
                    g.drawRect(i, j, this.gridSize, this.gridSize);
                }
            }
        }
    }

    @Override
    public void moveSelector(int x, int y){
        super.moveSelector(x, y);
        MainController.setDisplayBlock(image, singleSelector.getInitialLocation());
    }

    public boolean hasRepresentation(){
        return image != null;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean hasGridSize(){
        return this.gridSize > 0;
    }

    public void changeGridIndex() {
        this.gridIndex++;
        if(gridIndex>=GRID_SIZE.length) this.gridIndex = 0;
        gridSize = GRID_SIZE[gridIndex];
        repaint();
    }
}
