package xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.MultiSelector;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class ColorsRepresentation extends MultiSelectableRepresentation {
    private static final int[] IMG_DIMENSIONS = {128, 320};
    protected List<Color> selectedColors;
    protected Color[] sortedColors;

    public ColorsRepresentation() {
        super();
        this.selectedColors = new ArrayList<>();
        setPreferredSize(TileHelper.createDimension(IMG_DIMENSIONS));
    }

    @Override
    protected void drawRepresentation(Graphics g) {
        for (int i = 0; i < sortedColors.length; i++) {
            int x = i % 8;
            int y = i / 8;
            g.setColor(sortedColors[i]);
            g.fillRect(x * BLOCK, y * BLOCK, BLOCK, BLOCK);
        }

        selectorInUse.drawComponent(g);
    }

    /*@Override
    protected void drawPaintFilter(Graphics g, Selector selector) {}*/

    @Override
    public void startSelection(int x, int y) {
        super.startSelection(x, y);
        addSelectedColor(x, y);
    }

    @Override
    public void dragSelection(int x, int y) {
        super.dragSelection(x, y);
        addSelectedColor(x, y);
    }

    @Override
    public void removeSelection(int x, int y) {
        super.removeSelection(x, y);
        removeSelectedColor(x, y);
    }


    private void addSelectedColor(int x, int y) {
        int index = TileHelper.calculateColorsIndex(x, y);
        if (index >= 0 && index<sortedColors.length){
            Color color = sortedColors[index];
            if (!selectedColors.contains(color)){
                this.selectedColors.add(color);
            }
        }
    }

    private void removeSelectedColor(int x, int y) {
        int index = TileHelper.calculateColorsIndex(x, y);
        if (index > 0 && index<sortedColors.length){
            this.selectedColors.remove(sortedColors[index]);
        }
    }

    public void sortMethodChanged() {
        if (selectorInUse instanceof MultiSelector multiSelector) {
            multiSelector.resetSelectionEntries();
            for (int i = 0; i < sortedColors.length; i++) {
                if (selectedColors.contains(sortedColors[i])){
                    System.out.println(i);
                    Point point = TileHelper.calculateXYCoordinates(i);
                    multiSelector.addSelectionEntry(point.x, point.y);
                    System.out.println(point.x+":"+point.y);
                }
            }
        }
    }

    public boolean hasRepresentation(){
        return sortedColors != null;
    }

    public void setSortedColors(Color[] sortedColors) {
        this.sortedColors = sortedColors;
        sortMethodChanged();
    }
}
