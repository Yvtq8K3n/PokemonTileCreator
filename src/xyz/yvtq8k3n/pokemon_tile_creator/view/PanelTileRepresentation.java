package xyz.yvtq8k3n.pokemon_tile_creator.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelTileRepresentation extends JPanel {
    BufferedImage image;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
