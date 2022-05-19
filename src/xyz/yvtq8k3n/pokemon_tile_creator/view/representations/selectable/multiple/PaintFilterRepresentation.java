package xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable.multiple;

import xyz.yvtq8k3n.pokemon_tile_creator.view.filters.ColorFilter;
import xyz.yvtq8k3n.pokemon_tile_creator.view.filters.PaletteColorFilter;
import xyz.yvtq8k3n.pokemon_tile_creator.view.filters.PaintFilter;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class PaintFilterRepresentation extends GridRepresentation{
    private final int COLOR_FILTER = 0;
    private final int PALETTE_COLOR_FILTER = 1;
    protected PaintFilter paintFilterInUse;
    protected PaintFilter[] paintFilters;

    public PaintFilterRepresentation() {
        super();
        paintFilters =new PaintFilter[]{
                new ColorFilter(),
                new PaletteColorFilter()
        };
        paintFilterInUse = paintFilters[COLOR_FILTER];
    }

    //Set Custom Filters images
    public void setColorFilter(Color original, Color filter) {
        setPaintFilterInUse(paintFilters[COLOR_FILTER]);
        if (paintFilterInUse instanceof ColorFilter colorFilter) colorFilter.setColorFilter(original, filter);
        repaint();
    }

    public void setPaletteColorFilter(Color color) {
        setPaintFilterInUse(paintFilters[PALETTE_COLOR_FILTER]);
        if (paintFilterInUse instanceof PaletteColorFilter paletteColorFilter) paletteColorFilter.setColorFilter(color, selectorInUse);
        repaint();
    }

    public void softResetColorFilter() {
        this.paintFilterInUse = paintFilters[COLOR_FILTER];
        repaint();
    }

    //Set Images
    public void setPaintFilterInUse(PaintFilter paintFilterInUse) {
        this.paintFilterInUse.resetFilter();
        this.paintFilterInUse = paintFilterInUse;
    }
    public void setImage(BufferedImage image){
        for (PaintFilter paintFilter: paintFilters){
            paintFilter.setImage(image);
        }
    }
}
