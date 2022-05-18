package xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.PaletteController;
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
        int index = TileHelper.calculateColorsIndex(x, y);
        if (index >= 0 && index<sortedColors.length) {
            Color color = sortedColors[index];
            PaletteController.addSelectedColor(color);
        }
    }

    @Override
    public void dragSelection(int x, int y) {
        super.dragSelection(x, y);
        int index = TileHelper.calculateColorsIndex(x, y);
        if (index >= 0 && index<sortedColors.length) {
            Color color = sortedColors[index];
            PaletteController.addSelectedColor(color);
        }
    }

    @Override
    public void removeSelection(int x, int y) {
        super.removeSelection(x, y);
        int index = TileHelper.calculateColorsIndex(x, y);
        if (index >= 0 && index<sortedColors.length) {
            Color color = sortedColors[index];
            PaletteController.removeSelectedColor(color);
        }
    }

    public void addSelectedColor(Color color) {
        if (!selectedColors.contains(color)) this.selectedColors.add(color);
        System.out.println(selectedColors.size());
    }

    public void removeSelectedColor(Color color) {
        this.selectedColors.remove(color);
        System.out.println(selectedColors.size());
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
