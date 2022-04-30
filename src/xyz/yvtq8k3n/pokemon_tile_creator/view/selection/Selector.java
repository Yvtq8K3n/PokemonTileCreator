package xyz.yvtq8k3n.pokemon_tile_creator.view.selection;

import java.awt.*;

public abstract class Selector {
    protected int state;

    protected final static int BLOCK = 16;
    public static final int INACTIVE = 0;
    public static final int ACTIVE = 1;

    public Selector() {
        state = Selector.INACTIVE;
    }

    public boolean isActive() {
        return state == ACTIVE;
    }

    public void setState(int state){
        this.state = state;
    }

    public abstract void drawComponent(Graphics g);
}
