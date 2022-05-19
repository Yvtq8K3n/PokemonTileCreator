package xyz.yvtq8k3n.pokemon_tile_creator.view.selection;

import java.awt.*;
import java.awt.geom.Area;

public class SingleSelector extends Selector{
    protected int[] initialLocation;

    public SingleSelector() {
        super();
    }

    public void setInitialLocation(int x, int y){
        initialLocation = new int[]{
                BLOCK * Math.floorDiv(x, BLOCK),
                BLOCK * Math.floorDiv(y, BLOCK)
        };
    }

    @Override
    public void drawComponent(Graphics g) {
        if(isActive()){
            //Draw selector
            g.setColor(SELECTOR_COLOR);
            g.drawRect(initialLocation[0], initialLocation[1], BLOCK, BLOCK);
        }
    }

    @Override
    public Area getSelectionArea() {
        Area area = null;
        try{
            Rectangle r = new Rectangle(initialLocation[0], initialLocation[1], BLOCK, BLOCK);
            area = new Area(r);
        }catch (Exception ex){
            throw new IllegalArgumentException("Unable to generate a valid area for selector");
        }
        return area;
    }

    public int[] getInitialLocation() {
        return initialLocation;
    }

    @Override
    public void startSelection(int x, int y) {
        setInitialLocation(x, y);
        state = Selector.ACTIVE;
    }

    @Override
    public void dragSelection(int x, int y) {
        setInitialLocation(x, y);
    }

    @Override
    public void removeSelection(int x, int y) {
        state = Selector.INACTIVE;
    }

    @Override
    public void resetSelection() {
        initialLocation = null;
        state = Selector.INACTIVE;
    }
}
