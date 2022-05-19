package xyz.yvtq8k3n.pokemon_tile_creator.view.filters;

import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.Selector;
import java.awt.*;
import java.awt.geom.Area;

public class PaletteColorFilter extends PaintFilter{
    private Selector selector;

    public PaletteColorFilter() {
    }

    @Override
    protected void drawPaintFilter(Graphics g) {
        g.setColor(FILTER_BACKGROUND);
        Area area = selector.getSelectionArea();

        if (area != null) {
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    Color pixelColor = new Color(image.getRGB(i, j));
                    if (area.contains(i, j) &&
                            !pixelColor.equals(colorFilter)) {
                        g.fillRect(i, j, 1, 1);
                    }
                }
            }
        }
    }

    public void setColorFilter(Color colorFilter, Selector selector) {
        setColorFilter(colorFilter);
        this.selector = selector;
    }
}
