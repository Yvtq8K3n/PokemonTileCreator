package xyz.yvtq8k3n.pokemon_tile_creator.view.selection;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;

public class MultiSelector extends Selector {
    ArrayList<Point> selectionPoints;

    public MultiSelector() {
        super();
        selectionPoints = new ArrayList<>();
    }

    public void addSelectionEntry(int x, int y){
        Point selectionEntry = TileHelper.blockAdjustment(x, y);
        if (!selectionPoints.contains(selectionEntry)){
            selectionPoints.add(selectionEntry);
        }
        setState(ACTIVE);
    }

    public void removeSelectionEntry(int x, int y){
        Point selectionEntry = TileHelper.blockAdjustment(x, y);
        selectionPoints.remove(selectionEntry);
    }

    @Override
    public Area getSelectionArea() {
        Area area = null;
        try{
            for (Point point:selectionPoints) {
                Rectangle r = new Rectangle((int)point.getX(), (int)point.getY(), BLOCK, BLOCK);
                if (area == null) area = new Area(r);
                else area.add(new Area(r));
            }
        }catch (Exception ex){
            throw new IllegalArgumentException("Unable to generate a valid area for selector");
        }
        return area;
    }



    public void resetSelectionEntries(){
        selectionPoints.clear();
    }

    @Override
    public void drawComponent(Graphics g) {
        g.setColor(SELECTOR_COLOR);
        for (Point point:selectionPoints) {
            g.drawRect(point.x, point.y, BLOCK, BLOCK);
        }
    }

    @Override
    public void startSelection(int x, int y) {
        addSelectionEntry(x, y);
    }

    @Override
    public void dragSelection(int x, int y) {
        addSelectionEntry(x, y);
    }

    @Override
    public void removeSelection(int x, int y) {
        removeSelectionEntry(x, y);
    }

    @Override
    public void resetSelection() {
        resetSelectionEntries();
        state = Selector.INACTIVE;
    }
}
