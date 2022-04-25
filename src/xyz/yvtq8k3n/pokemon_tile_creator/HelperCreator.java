package xyz.yvtq8k3n.pokemon_tile_creator;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HelperCreator {
    public static Dimension createDimension(int[] dimension){
        return new Dimension(dimension[0], dimension[1]);
    }

    public static BufferedImage copyImage(BufferedImage source){
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics2D g2 = b.createGraphics();
        g2.drawImage(source, 0, 0, null);
        g2.dispose();
        return b;
    }
}

