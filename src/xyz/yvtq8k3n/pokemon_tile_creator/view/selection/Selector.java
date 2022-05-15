package xyz.yvtq8k3n.pokemon_tile_creator.view.selection;

import java.awt.*;
import java.awt.geom.Area;

public abstract class Selector {
    protected static Color SELECTOR_COLOR = Color.BLUE;
    protected final static int BLOCK = 16;
    public static final int INACTIVE = 0;
    public static final int ACTIVE = 1;
    protected int state;

    public Selector() {
        this.state = INACTIVE;
    }

    public abstract void startSelection(int x, int y);
    public abstract void dragSelection(int x, int y);
    public abstract void removeSelection(int x, int y);
    public abstract void resetSelection();

    public abstract void drawComponent(Graphics g);

    public abstract Area getSelectorArea();

    public boolean isActive() {
        return state == ACTIVE;
    }

    public void setState(int state){
        this.state = state;
    }
}
