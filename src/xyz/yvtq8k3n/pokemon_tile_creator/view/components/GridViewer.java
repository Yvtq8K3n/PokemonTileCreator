package xyz.yvtq8k3n.pokemon_tile_creator.view.components;

import java.awt.*;

public class GridViewer {
    protected static Color GRID_COLOR = Color.RED;
    public final static int BLOCK = 16;
    public final static int UNIT = 8;
    public final static int NONE = 0;

    private int width;
    private int height;

    private int[] gridSize;
    protected int gridIndex;

    public GridViewer() {
        gridSize = new int[] {
                BLOCK,
                UNIT,
                NONE
        };
        gridIndex = gridSize.length - 1;
    }

    public GridViewer(Dimension dimension) {
        this();
        this.width = dimension.width;
        this.height = dimension.height;
    }

    public void drawGrid(Graphics g){
        if (hasGrid()) {
            g.setColor(GRID_COLOR);
            int size = getCurrentGridSize();
            for (int i = 0; i < width; i += size) {
                for (int j = 0; j < height; j += size) {
                    g.drawRect(i, j, size, size);
                }
            }

        }
    }

    public void changeGridIndex() {
        this.gridIndex++;
        if(gridIndex>=gridSize.length) this.gridIndex = 0;
    }

    private int getCurrentGridSize(){
        return gridSize[gridIndex];
    }

    private boolean hasGrid(){
        return gridSize[gridIndex] > 0;
    }
}
