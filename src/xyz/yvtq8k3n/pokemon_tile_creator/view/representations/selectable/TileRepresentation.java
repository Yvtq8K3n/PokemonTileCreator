package xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.LoadController;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.view.components.GridViewer;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

public class TileRepresentation extends MultiSelectableRepresentation {
    protected static Color FILTER_BACKGROUND = Color.BLACK;
    private static final int[] IMG_DIMENSIONS = {128, 320};
    protected GridViewer gridViewer;
    protected BufferedImage image;
    private Color colorFilter;

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
        drawColorFilter(g);

        //Draw selector
        if (gridViewer != null) gridViewer.drawGrid(g);

        //Draw selector
        if (selectorInUse != null) {
            selectorInUse.drawComponent(g);
        }
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

    public void setColorFilter(Color color) {
        this.colorFilter = color;
        repaint();
    }

    @Override
    public void setOperator(Operator current) {
        super.setOperator(current);
    }
}
