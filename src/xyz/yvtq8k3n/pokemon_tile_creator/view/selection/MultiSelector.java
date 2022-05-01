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
            Point point = applyBoundsConstraint((int)selectionEntry.getX(), (int)selectionEntry.getY());
            selectionPoints.add(point);
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
        if (hasImage()){
            if (hasFilter()){
                g.setColor(Color.BLACK);

                for (Point point:selectionPoints) {
                    int x = (int) point.getX();
                    int y = (int) point.getY();
                    for (int i = x; i < x + BLOCK; i++) {
                        for (int j = y; j < y + BLOCK; j++) {
                            Color pixelColor = new Color(image.getRGB(i, j));
                            if (!pixelColor.equals(filter)){
                                g.fillRect(i, j, 1,1);
                            }
                        }
                    }
                }
            }

            //Draw grid
            g.setColor(Color.RED);
            drawGridComponent(g);

            g.setColor(Color.BLUE);
            for (Point point:selectionPoints) {
                g.drawRect((int)point.getX(), (int)point.getY(), BLOCK, BLOCK);
            }
        }
    }
}