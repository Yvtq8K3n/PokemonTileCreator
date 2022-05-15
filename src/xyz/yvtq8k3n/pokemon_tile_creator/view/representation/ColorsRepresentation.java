package xyz.yvtq8k3n.pokemon_tile_creator.view.representation;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.PaletteController;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.Selector;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class ColorsRepresentation extends SelectableRepresentation {
    private static final int[] IMG_DIMENSIONS = {128, 320};
    protected List<Color> selectedColors;
    protected Color[] sortedColors;

    public ColorsRepresentation() {
        super();
        this.selectedColors = new ArrayList<>();
        setPreferredSize(TileHelper.createDimension(IMG_DIMENSIONS));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (hasRepresentation()) {
            for (int i = 0; i < sortedColors.length; i++) {
                int x = i % 8;
                int y = i / 8;
                g.setColor(sortedColors[i]);
                g.fillRect(x * BLOCK, y * BLOCK, BLOCK, BLOCK);
            }

            //Draw Selectors
            paintSelectors(g);
        }
    }

    /*@Override
    public void reset(){
        super.reset();

        //Removes selectedColors from Selector
        selectedColors.clear();
    }*/


    public void sortMethodChanged() {
       /* multiSelector.resetSelectionEntries();
        for (int i = 0; i < sortedColors.length; i++) {
            if (selectedColors.contains(sortedColors[i])){
                System.out.println(i);
                Point point = TileHelper.calculateXYCoordinates(i);
                multiSelector.addSelectionEntry(point.x, point.y);
                System.out.println(point.x+":"+point.y);
            }
        }*/
    }

    @Override
    protected void drawPaintFilter(Graphics g, Selector selector) {}

   /* @Override
    public void moveSingleSelector(int x, int y){
        super.moveSingleSelector(x, y);

        //Calculate the index of the wanted color by converting the x and y coordinates
        int index = TileHelper.calculateColorsIndex(x, y);
        if (index>= 0 && index<sortedColors.length) {
            Color selectedColor = sortedColors[index];
            PaletteController.setDisplayBlockColor(selectedColor);
        }
    }

    @Override
    public void addMultiSelectorPoint(int x, int y) {
        super.addMultiSelectorPoint(x, y);
        addSelectedColor(x, y);
    }

    @Override
    public void removeMultiSelectorPoint(int x, int y) {
        super.removeMultiSelectorPoint(x, y);
        removeSelectedColor(x, y);
    }*/

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

    public boolean hasRepresentation(){
        return sortedColors != null;
    }

    public void setSortedColors(Color[] sortedColors) {
        this.sortedColors = sortedColors;
    }
}
