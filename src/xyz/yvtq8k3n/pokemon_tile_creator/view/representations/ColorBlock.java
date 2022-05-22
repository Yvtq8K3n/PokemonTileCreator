package xyz.yvtq8k3n.pokemon_tile_creator.view.representations;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ColorBlock extends BlockRepresentation{
    public static final int DISPLAY_DIMENSION = 72;
    private boolean enableColorInfo;
    protected Color colorSlot1;
    protected Color colorSlot2;

    public ColorBlock() {
        super();
        setPreferredSize(TileHelper.createDimension(new int[]{DISPLAY_DIMENSION, DISPLAY_DIMENSION}));
    }

    @Override
    protected void drawRepresentation(Graphics g) {
        g.setColor(colorSlot1);
        g.fillRect(0, 0, DISPLAY_DIMENSION, DISPLAY_DIMENSION);

        if(hasChangingColor()){
            g.setColor(colorSlot2);
            g.fillRect( DISPLAY_DIMENSION/2, 0,
                    DISPLAY_DIMENSION, DISPLAY_DIMENSION);
        }

        if(enableColorInfo){
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.RED);
            g2.setFont(new Font("Arial", Font.BOLD, 12));
            g2.drawString(String.valueOf(colorSlot1.getRed()), 5, 16);
            g2.drawString(String.valueOf(colorSlot1.getGreen()), 5, 40);
            g2.drawString(String.valueOf(colorSlot1.getBlue()), 5, 64);

            String colorR = String.valueOf(colorSlot1.getRed() / STEP);
            String colorB = String.valueOf(colorSlot1.getGreen() / STEP);
            String colorG = String.valueOf(colorSlot1.getBlue() / STEP);
            g2.drawString(colorR, DISPLAY_DIMENSION - (5 * (colorR.length() + 1)), 16);
            g2.drawString(colorG, DISPLAY_DIMENSION - (5 * (colorG.length() + 1)), 40);
            g2.drawString(colorB, DISPLAY_DIMENSION - (5 * (colorB.length() + 1)), 64);
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
