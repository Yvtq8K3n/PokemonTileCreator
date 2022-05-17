package xyz.yvtq8k3n.pokemon_tile_creator.view.selection;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;

import java.awt.*;
import java.awt.geom.Area;

public class AreaSelector extends SingleSelector {
    private int[] resizeLocation;

    public AreaSelector() {
        super();
    }

    public void resizeSelector(int x, int y){
        resizeLocation = new int[]{
                BLOCK * Math.floorDiv(x, BLOCK),
                BLOCK * Math.floorDiv(y, BLOCK)
        };
    }

    public int[] getStartingPoint(){
        return TileHelper.minCoordinates(initialLocation, resizeLocation);
    }

    public int[] getEndingPoint(){
        return TileHelper.maxCoordinates(initialLocation, resizeLocation);
    }

    @Override
    public void drawComponent(Graphics g) {
        if(isActive()) {
            int[] minCoordinates = getStartingPoint();
            int[] maxCoordinates = getEndingPoint();

            //Draw selector
            g.setColor(SELECTOR_COLOR);
            g.drawRect(minCoordinates[0], minCoordinates[1],
                    maxCoordinates[0] - minCoordinates[0] + BLOCK,
                    maxCoordinates[1] - minCoordinates[1] + BLOCK);
        }
    }

    @Override
    public Area getSelectionArea() {
        int[] minCoordinates = getStartingPoint();
        int[] maxCoordinates = getEndingPoint();

        Area area = new Area();
        Rectangle r = new Rectangle(minCoordinates[0], minCoordinates[1],
                maxCoordinates[0] - minCoordinates[0] + BLOCK,
                maxCoordinates[1] - minCoordinates[1] + BLOCK);
        area.add(new Area(r));
        return area;
    }

    @Override
    public void startSelection(int x, int y) {
        setInitialLocation(x, y);
    }

    @Override
    public void dragSelection(int x, int y) {
        resizeSelector(x, y);
        System.out.println(x+":"+y);
        state = Selector.ACTIVE;
    }
}
