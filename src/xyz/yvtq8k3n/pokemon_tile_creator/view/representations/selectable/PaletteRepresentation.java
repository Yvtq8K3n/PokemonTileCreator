package xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.TileController;

import java.awt.*;
import java.awt.event.*;

public class PaletteRepresentation extends SelectableRepresentation {
    private static final int[] PALETTE_BOX = {128, 32};
    private Color[] palette;

    public PaletteRepresentation() {
        super();
        palette = new Color[0];
        setPreferredSize(TileHelper.createDimension(PALETTE_BOX));
    }

    @Override
    protected void drawRepresentation(Graphics g) {
        int count = 0;
        for(int j = 0; j <= BLOCK; j += BLOCK){
             for(int i = 0; i < BLOCK * 8; i += BLOCK){
                g.setColor(palette[count]);
                g.fillRect(i, j, BLOCK, BLOCK);
                count++;
            }
        }

        selectorInUse.drawComponent(g);
    }

    @Override
    public void startSelection(int x, int y) {
        super.startSelection(x, y);
        int index = TileHelper.calculateColorsIndex(x, y);
        index = TileHelper.applyBoundsConstraint(index);
        TileController.setColorFilter(palette[index]);
    }

    @Override
    public void dragSelection(int x, int y) {
        super.dragSelection(x, y);
        int index = TileHelper.calculateColorsIndex(x, y);
        index = TileHelper.applyBoundsConstraint(index);
        TileController.setColorFilter(palette[index]);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        TileController.softResetColorFilter();
    }

    @Override
    public boolean hasRepresentation() {
        return palette.length > 0;
    }

    public void setPalette(Color[] palette) {
        this.palette = palette;
    }
}
