package xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.view.components.GridViewer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileRepresentation extends MultiSelectableRepresentation {
    private static final int[] IMG_DIMENSIONS = {128, 320};
    protected GridViewer gridViewer;
    protected BufferedImage image;
    //paintfilter

    public TileRepresentation() {
        super();
        gridViewer = new GridViewer(TileHelper.createDimension(IMG_DIMENSIONS));
        setPreferredSize(TileHelper.createDimension(IMG_DIMENSIONS));
    }

    @Override
    protected  void drawRepresentation(Graphics g) {
        g.drawImage(image, 0, 0, this);

        //Draw other components
        drawComponents(g);
    }

    private void drawComponents(Graphics g) {
        //Draw selector
        if (gridViewer != null) gridViewer.drawGrid(g);

        //Draw selector
        if (selectorInUse != null) selectorInUse.drawComponent(g);
    }

    public void changeGrid() {
        gridViewer.changeGridIndex();
        repaint();
    }

    public boolean hasRepresentation(){
        return image != null;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
