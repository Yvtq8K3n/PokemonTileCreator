package xyz.yvtq8k3n.pokemon_tile_creator.view.representation;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;

import javax.swing.*;
import java.awt.*;

public class ColorRepresentation extends Representation{
    public static final int[] DISPLAY_DIMENSIONS = {72, 72};
    protected Color selectedColor;
    protected Color changingColor;

    public ColorRepresentation() {
        setPreferredSize(TileHelper.createDimension(DISPLAY_DIMENSIONS));
        setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(selectedColor);
        g.fillRect(0, 0, DISPLAY_DIMENSIONS[0], DISPLAY_DIMENSIONS[1]);

        if(hasChangingColor()){
            g.setColor(changingColor);
            g.fillRect( DISPLAY_DIMENSIONS[0]/2, 0,
                    DISPLAY_DIMENSIONS[0], DISPLAY_DIMENSIONS[1]);
        }
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
        this.changingColor = null;
    }

    public boolean hasChangingColor(){
        return changingColor != null;
    }

    public void setChangingColor(Color changingColor) {
        this.changingColor = changingColor;
    }
}
