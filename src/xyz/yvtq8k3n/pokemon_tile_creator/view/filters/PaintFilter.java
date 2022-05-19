package xyz.yvtq8k3n.pokemon_tile_creator.view.filters;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class PaintFilter {
    protected static Color FILTER_BACKGROUND = Color.BLACK;

    protected BufferedImage image;
    protected Color colorFilter;

    public final void drawComponent(Graphics g){
        if (hasColorFilter()){
            drawPaintFilter(g);
        }
    }
    protected abstract void drawPaintFilter(Graphics g);

    protected void setColorFilter(Color colorFilter){
        this.colorFilter = colorFilter;
    }
    public boolean hasColorFilter() {return colorFilter != null;}
    public void resetFilter() {
        this.colorFilter = null;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
