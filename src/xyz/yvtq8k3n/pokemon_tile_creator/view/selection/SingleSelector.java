package xyz.yvtq8k3n.pokemon_tile_creator.view.selection;

import java.awt.*;
import java.awt.geom.Area;

public class SingleSelector extends Selector{
    protected int[] initialLocation;

    public SingleSelector() {
        super();
    }

    public void setInitialLocation(int x, int y){
        state = Selector.ACTIVE;
        initialLocation = new int[]{
                BLOCK * Math.floorDiv(x, BLOCK),
                BLOCK * Math.floorDiv(y, BLOCK)
        };
    }

    public int[] getInitialLocation() {
        return initialLocation;
    }

    @Override
     public void drawComponent(Graphics g) {
        //Draw selector
        g.setColor(SELECTOR_COLOR);
        g.drawRect(initialLocation[0], initialLocation[1], BLOCK, BLOCK);
    }

    @Override
    public Area getSelectorArea() {
        Area area = new Area();
        Rectangle r = new Rectangle(initialLocation[0], initialLocation[1], BLOCK, BLOCK);
        area.add(new Area(r));
        return area;
    }
}
