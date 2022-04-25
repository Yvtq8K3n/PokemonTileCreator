package xyz.yvtq8k3n.pokemon_tile_creator.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelTileRepresentation extends JPanel {
    private static int[] GRID_DIMENSIONS = {8,8};
    private static int[] GRID_SIZE = {2,1,0};

    public BufferedImage image;
    int gridIndex;

    public PanelTileRepresentation() {
        this.gridIndex = GRID_SIZE.length-1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
        if (image != null && GRID_SIZE[gridIndex] > 0){
            for (int i = 0; i < image.getWidth(); i+= GRID_DIMENSIONS[0] * GRID_SIZE[gridIndex]) {
                for (int j = 0; j < image.getHeight(); j+= GRID_DIMENSIONS[1] * GRID_SIZE[gridIndex]) {
                    g.setColor(Color.RED);
                    g.drawRect(i, j, GRID_DIMENSIONS[0]*GRID_SIZE[gridIndex],
                            GRID_DIMENSIONS[1]*GRID_SIZE[gridIndex]);
                }
            }
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void changeGridIndex() {
        this.gridIndex++;
        if(gridIndex>=GRID_SIZE.length) this.gridIndex = 0;
        this.repaint();
    }
}
