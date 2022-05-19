package xyz.yvtq8k3n.pokemon_tile_creator.view.filters;

import java.awt.*;

public class ColorFilter extends PaintFilter{
    private Color colorOriginal;

    public ColorFilter() {
    }

    @Override
    public void drawPaintFilter(Graphics g) {
        g.setColor(colorFilter);
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color pixelColor = new Color(image.getRGB(i, j));
                if (pixelColor.equals(colorOriginal)){
                    g.fillRect(i, j, 1,1);
                }
            }
        }
    }

    public void setColorFilter(Color original, Color colorFilter) {
        this.colorOriginal = original;
        setColorFilter(colorFilter);
    }
}
