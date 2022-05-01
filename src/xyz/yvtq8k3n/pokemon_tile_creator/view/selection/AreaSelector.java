package xyz.yvtq8k3n.pokemon_tile_creator.view.selection;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import java.awt.*;

public class AreaSelector extends SingleSelector{
    private int[] resizeLocation;

    public AreaSelector() {
        super();
    }

    public void resizeSelector(int x, int y){
        Point point = applyBoundsConstraint(x, y);
        resizeLocation = new int[]{
                BLOCK * Math.floorDiv((int) point.getX(), BLOCK),
                BLOCK * Math.floorDiv((int) point.getY(), BLOCK)
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
        int[] minCoordinates = getStartingPoint();
        int[] maxCoordinates = getEndingPoint();

        if (hasImage()){
            if (hasFilter()){
                g.setColor(Color.BLACK);
                for (int i = minCoordinates[0]; i < maxCoordinates[0] + BLOCK; i++) {
                    for (int j = minCoordinates[1]; j < maxCoordinates[1] + BLOCK; j++) {
                        Color pixelColor = new Color(image.getRGB(i, j));
                        if (!pixelColor.equals(filter)){
                            g.fillRect(i, j, 1,1);
                        }
                    }
                }
            }
            //Draw grid
            g.setColor(GRID_COLOR);
            drawGridComponent(g);

            //Draw selector
            g.setColor(SELECTOR_COLOR);
            g.drawRect(minCoordinates[0], minCoordinates[1],
            maxCoordinates[0] - minCoordinates[0] + BLOCK,
            maxCoordinates[1] - minCoordinates[1] + BLOCK);
        }
    }
}
