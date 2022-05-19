package xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable.multiple;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.LoadController;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.Operator;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileRepresentation extends PaintFilterRepresentation {
    private static final int[] IMG_DIMENSIONS = {128, 320};
    protected BufferedImage image;

    public TileRepresentation() {
        super();
        setPreferredSize(TileHelper.createDimension(IMG_DIMENSIONS));
    }

    @Override
    protected  void drawRepresentation(Graphics g) {
        g.drawImage(image, 0, 0, this);

        //Draw other components
        if (paintFilterInUse != null) paintFilterInUse.drawComponent(g);

        //Draw grid
        if (gridViewer != null) gridViewer.drawGrid(g);

        //Draw selector
        if (selectorInUse != null) selectorInUse.drawComponent(g);
    }

    @Override
    public void startSelection(int x, int y){
        super.startSelection(x, y);
        LoadController.setDisplayBlock(image, x, y);
    }

    @Override
    public void dragSelection(int x, int y){
        super.dragSelection(x, y);
        LoadController.setDisplayBlock(image, x, y);
    }

    @Override
    public void setOperator(Operator current) {
        super.setOperator(current);
    }

    @Override
    void setGridBounds() {
        gridViewer.setGridBounds(TileHelper.createDimension(IMG_DIMENSIONS));
    }

    @Override
    public boolean hasRepresentation(){
        return image != null;
    }

    @Override
    public void setImage(BufferedImage image) {
        super.setImage(image);
        this.image = image;
    }
}
