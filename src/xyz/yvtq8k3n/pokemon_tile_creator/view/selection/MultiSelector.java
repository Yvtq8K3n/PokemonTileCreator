package xyz.yvtq8k3n.pokemon_tile_creator.view.selection;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;

import java.awt.*;
import java.util.ArrayList;

public class MultiSelector extends Selector{
    ArrayList<Point> selectionPoints;

    public MultiSelector() {
        super();
        selectionPoints = new ArrayList<>();
    }

    public void addSelectionEntry(int x, int y){
        state = Selector.ACTIVE;
        Point selectionEntry = TileHelper.blockAdjustment(x, y);
        if (!selectionPoints.contains(selectionEntry)){
            selectionPoints.add(selectionEntry);
        }
    }

    public void removeSelectionEntry(int x, int y){
        state = Selector.ACTIVE;
        Point selectionEntry = TileHelper.blockAdjustment(x, y);
        selectionPoints.remove(selectionEntry);
    }

    @Override
    public void setState(int state) {
        super.setState(state);
        selectionPoints.clear();
    }

    @Override
    public void drawComponent(Graphics g) {
        g.setColor(Color.BLUE);
        for (Point point:selectionPoints) {
            g.drawRect((int)point.getX(), (int)point.getY(), BLOCK, BLOCK);
        }
    }
}
