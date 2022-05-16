package xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.TileController;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representations.Representation;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.SingleSelector;

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
    public boolean hasRepresentation() {
        return palette.length > 0;
    }

    public void setPalette(Color[] palette) {
        this.palette = palette;
    }
}