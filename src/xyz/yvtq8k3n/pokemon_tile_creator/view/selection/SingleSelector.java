package xyz.yvtq8k3n.pokemon_tile_creator.view.selection;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.OperatorController;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.SingleSelectableBehaviour;

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
    public Area getSelectorArea() {
        Area area = new Area();
        Rectangle r = new Rectangle(initialLocation[0], initialLocation[1], BLOCK, BLOCK);
        area.add(new Area(r));
        return area;
    }

   /* @Override
    public void startSingleSelector(int x, int y){
        moveSingleSelector(x, y);
    }

    @Override
    public void moveSingleSelector(int x, int y){
        //if (!hasRepresentation()) return;
        //Set selector location
        Point bound = TileHelper.applyBoundsConstraint(x, y);
        startSelection(bound.x, bound.y);
    }*/

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
