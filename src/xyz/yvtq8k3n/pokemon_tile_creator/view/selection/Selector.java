package xyz.yvtq8k3n.pokemon_tile_creator.view.selection;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Selector {
    protected final static int BLOCK = 16;
    public static final int INACTIVE = 0;
    public static final int ACTIVE = 1;
    protected static Color SELECTOR_COLOR = Color.BLUE;
    protected static Color GRID_COLOR = Color.RED;

    protected int state;

    protected BufferedImage image;
    protected Color filter;
    protected int gridSize;

    public Selector() {
        this.state = Selector.INACTIVE;
    }

    protected Point applyBoundsConstraint(int x, int y){
        //Replace x(0, max) if it's out of viewport
        x = Math.min(x, image.getWidth() - BLOCK);
        x = Math.max(x, 0);

        //Replace y(0, max) if it's out of viewport
        y = Math.min(y, image.getHeight() - BLOCK);
        y = Math.max(y, 0);
        return new Point(x, y);
    }

    public boolean hasFilter() {return filter!=null;}

    public void setFilter(Color filter) {
        this.filter = filter;
    }

    public void setGridSize(int gridSize){
        this.gridSize = gridSize;
    }

    public boolean hasGridSize(){
        return this.gridSize > 0;
    }

    public boolean isActive() {
        return state == ACTIVE;
    }

    public void setState(int state){
        this.state = state;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean hasImage() {return image != null; }

    public abstract void drawComponent(Graphics g);

    protected void drawGridComponent(Graphics g){
        if (hasImage()
                && hasGridSize()) {
            for (int i = 0; i < image.getWidth(); i += this.gridSize) {
                for (int j = 0; j < image.getHeight(); j += this.gridSize) {
                    g.drawRect(i, j, this.gridSize,
                            this.gridSize);
                }
            }
        }
    }
}
