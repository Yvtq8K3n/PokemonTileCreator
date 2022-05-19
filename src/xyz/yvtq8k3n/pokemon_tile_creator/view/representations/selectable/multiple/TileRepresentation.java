package xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable.multiple;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.LoadController;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.Operator;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

public class TileRepresentation extends GridRepresentation {
    protected static Color FILTER_BACKGROUND = Color.BLACK;
    private static final int[] IMG_DIMENSIONS = {128, 320};
    protected BufferedImage image;
    private Color colorFilter;

    public TileRepresentation() {
        super();
        setPreferredSize(TileHelper.createDimension(IMG_DIMENSIONS));
    }

    @Override
    protected  void drawRepresentation(Graphics g) {
        g.drawImage(image, 0, 0, this);

        //Draw other components
        drawColorFilter(g);

        //Draw grid
        if (gridViewer != null) gridViewer.drawGrid(g);

        //Draw selector
        if (selectorInUse != null) selectorInUse.drawComponent(g);
    }

    private void drawColorFilter(Graphics g) {
        if(hasColorFilter()){
            Area area = selectorInUse.getSelectionArea();
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
    void setGridBounds() {
        gridViewer.setGridBounds(TileHelper.createDimension(IMG_DIMENSIONS));
    }

    public boolean hasColorFilter() {return colorFilter!=null;}

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

    public boolean hasRepresentation(){
        return image != null;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setColorFilter(Color color) {
        this.colorFilter = color;
        repaint();
    }

    @Override
    public void setOperator(Operator current) {
        super.setOperator(current);
    }
}
