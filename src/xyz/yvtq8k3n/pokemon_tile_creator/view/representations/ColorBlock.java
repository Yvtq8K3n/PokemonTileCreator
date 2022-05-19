package xyz.yvtq8k3n.pokemon_tile_creator.view.representations;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ColorBlock extends BlockRepresentation{
    public static final int[] DISPLAY_DIMENSIONS = {72, 72};
    private boolean enableColorInfo;
    protected Color colorSlot1;
    protected Color colorSlot2;

    public ColorBlock() {
        super();
        setPreferredSize(TileHelper.createDimension(DISPLAY_DIMENSIONS));
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

        if(enableColorInfo){
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            g2.setFont(new Font("Arial", Font.BOLD, 12));
            g2.drawString(String.valueOf(colorSlot1.getRed()), 5, 16);
            g2.drawString(String.valueOf(colorSlot1.getGreen()), 5, 40);
            g2.drawString(String.valueOf(colorSlot1.getBlue()), 5, 64);
        }
    }

    public void setColorSlot1(Color selectedColor) {
        this.colorSlot1 = selectedColor;
        this.colorSlot2 = null;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        enableColorInfo = !enableColorInfo;
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
