package xyz.yvtq8k3n.pokemon_tile_creator.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelPaletteRepresentation extends JPanel {
    private final static int[] BOX_DIMENSIONS = {16, 16};
    Color[] palette;

    public PanelPaletteRepresentation() {
        palette = new Color[0];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int count = 0;

        if(palette.length > 0){
            for(int j = 0; j < BOX_DIMENSIONS[1]+2; j += BOX_DIMENSIONS[1]){
                 for(int i = 0; i < BOX_DIMENSIONS[0]*8; i += BOX_DIMENSIONS[0]){
                    g.setColor(palette[count]);
                    g.fillRect(i, j, BOX_DIMENSIONS[0], BOX_DIMENSIONS[1]);
                    count++;
                }
            }
        }
    }

    public void setPalette( Color[] palette) {
        this.palette = palette;
    }
}
