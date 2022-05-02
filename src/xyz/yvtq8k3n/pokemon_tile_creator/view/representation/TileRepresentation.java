package xyz.yvtq8k3n.pokemon_tile_creator.view.representation;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.Selector;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

public class TileRepresentation extends SelectableRepresentation {
    private static final int[] IMG_DIMENSIONS = {128, 320};

    protected BufferedImage image;

    public TileRepresentation() {
        super();
        setPreferredSize(TileHelper.createDimension(IMG_DIMENSIONS));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (hasRepresentation()) {
            g.drawImage(image, 0, 0, this);

            //Draw Selectors
            paintSelectors(g);
        }
    }

    @Override
    protected void drawPaintFilter(Graphics g, Selector selector) {
        if (hasColorFilter()){
            Area area = selector.getSelectorArea();
            g.setColor(FILTER_BACKGROUND);
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    Color pixelColor = new Color(image.getRGB(i, j));
                    if (area.contains(i, j ) &&
                            !pixelColor.equals(colorFilter)){
                        g.fillRect(i, j, 1,1);
                    }
                }
            }
        }
    }

    @Override
    public void moveSelector(int x, int y){
        super.moveSelector(x, y);
        MainController.setDisplayBlock(image, singleSelector.getInitialLocation());
    }

    public boolean hasRepresentation(){
        return image != null;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
