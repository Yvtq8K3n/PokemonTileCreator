package xyz.yvtq8k3n.pokemon_tile_creator.view.representations;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;

import javax.swing.*;
import java.awt.*;

public class ColorBlock extends Representation{
    public static final int[] DISPLAY_DIMENSIONS = {72, 72};
    protected Color color;
    protected Color changingColor;

    public ColorBlock() {
        setPreferredSize(TileHelper.createDimension(DISPLAY_DIMENSIONS));
        setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    @Override
    public boolean hasRepresentation() {
        return false;
    }

    @Override
    protected void drawRepresentation(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, DISPLAY_DIMENSIONS[0], DISPLAY_DIMENSIONS[1]);

        if(hasChangingColor()){
            g.setColor(changingColor);
            g.fillRect( DISPLAY_DIMENSIONS[0]/2, 0,
                    DISPLAY_DIMENSIONS[0], DISPLAY_DIMENSIONS[1]);
        }
    }

    public void setColor(Color selectedColor) {
        this.color = selectedColor;
        this.changingColor = null;
    }

    public boolean hasChangingColor(){
        return changingColor != null;
    }

    public void setChangingColor(Color changingColor) {
        this.changingColor = changingColor;
    }
}
