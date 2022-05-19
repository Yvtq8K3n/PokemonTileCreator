package xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable.multiple;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.PaletteController;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.MultiSelector;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.Selector;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class ColorsRepresentation extends GridRepresentation {
    private static final int[] IMG_DIMENSIONS = {128, 320};
    protected List<Color> selectedColors; //needs to go to model
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

        //Draw grid
        if (gridViewer != null) gridViewer.drawGrid(g);

        //Draw selector
        if (selectorInUse != null) selectorInUse.drawComponent(g);
    }

    public void updateMultiSelector() {
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

    public void setSortedColors(Color[] sortedColors) {
        this.sortedColors = sortedColors;
        updateMultiSelector();
    }

    public boolean isIndexValid(int index){
        return index >= 0 && index<sortedColors.length;
    }

    @Override
    public void startSelection(int x, int y) {
        int index = TileHelper.calculateColorsIndex(x, y);
        if (isIndexValid(index)) {
            super.startSelection(x, y);
            Color color = sortedColors[index];
            PaletteController.addSelectedColor(color);
        }
    }

    @Override
    public void dragSelection(int x, int y) {
        int index = TileHelper.calculateColorsIndex(x, y);
        if (isIndexValid(index)) {
            super.dragSelection(x, y);
            Color color = sortedColors[index];
            PaletteController.addSelectedColor(color);
        }
    }

    @Override
    public void removeSelection(int x, int y) {
        int index = TileHelper.calculateColorsIndex(x, y);
        if (isIndexValid(index)) {
            super.removeSelection(x, y);
            Color color = sortedColors[index];
            PaletteController.removeSelectedColor(color);
        }
    }

    @Override
    public void changeSelector(Selector selector) {
        super.changeSelector(selector);
        updateMultiSelector();
    }

    @Override
    public boolean hasRepresentation(){
        return sortedColors != null;
    }

    @Override
    void setGridBounds() {
        gridViewer.setGridBounds(TileHelper.createDimension(IMG_DIMENSIONS));
    }

    public void setSelectedColors(List<Color> selectedColors) {
        this.selectedColors = selectedColors;
    }
}
