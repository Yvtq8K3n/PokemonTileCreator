package xyz.yvtq8k3n.pokemon_tile_creator.view.selection;

import java.awt.*;
import java.awt.geom.Area;

public abstract class Selector extends StateComponent {
    protected static Color SELECTOR_COLOR = Color.BLUE;
    protected final static int BLOCK = 16;

    public Selector() {
        super(StateComponent.INACTIVE);
    }

    public abstract void drawComponent(Graphics g);

    public abstract Area getSelectorArea();
}
