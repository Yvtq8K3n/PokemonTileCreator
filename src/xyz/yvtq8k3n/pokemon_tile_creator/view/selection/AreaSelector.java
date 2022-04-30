package xyz.yvtq8k3n.pokemon_tile_creator.view.selection;

import xyz.yvtq8k3n.pokemon_tile_creator.HelperCreator;
import java.awt.*;

public class AreaSelector extends SingleSelector{
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
        return HelperCreator.minCoordinates(initialLocation, resizeLocation);
    }

    public int[] getEndingPoint(){
        return HelperCreator.maxCoordinates(initialLocation, resizeLocation);
    }

    @Override
    public void drawComponent(Graphics g) {
        g.setColor(Color.BLUE);
        int[] minCoordinates = getStartingPoint();
        int[] maxCoordinates = getEndingPoint();
        g.drawRect(minCoordinates[0], minCoordinates[1],
                maxCoordinates[0] - minCoordinates[0] + BLOCK,
                maxCoordinates[1] - minCoordinates[1] + BLOCK);
    }
}
