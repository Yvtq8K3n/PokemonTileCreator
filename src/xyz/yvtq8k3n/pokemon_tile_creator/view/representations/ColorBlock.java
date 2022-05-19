package xyz.yvtq8k3n.pokemon_tile_creator.view.representations;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import javax.swing.*;
import java.awt.*;

public class ColorBlock extends Representation{
    public static final int[] DISPLAY_DIMENSIONS = {72, 72};
    protected Color colorSlot1;
    protected Color colorSlot2;

    public ColorBlock() {
        setPreferredSize(TileHelper.createDimension(DISPLAY_DIMENSIONS));
        setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    @Override
    protected void drawRepresentation(Graphics g) {
        g.setColor(colorSlot1);
        g.fillRect(0, 0, DISPLAY_DIMENSIONS[0], DISPLAY_DIMENSIONS[1]);

        if(hasChangingColor()){
            g.setColor(colorSlot2);
            g.fillRect( DISPLAY_DIMENSIONS[0]/2, 0,
                    DISPLAY_DIMENSIONS[0], DISPLAY_DIMENSIONS[1]);
        }
    }

    public void setColorSlot1(Color selectedColor) {
        this.colorSlot1 = selectedColor;
        this.colorSlot2 = null;
        repaint();
    }

    @Override
    public boolean hasRepresentation() {return colorSlot1 != null; }

    public boolean hasChangingColor(){
        return colorSlot2 != null;
    }

    public void setColorSlot2(Color colorSlot2) {
        this.colorSlot2 = colorSlot2;
    }
}
