package xyz.yvtq8k3n.pokemon_tile_creator;

import java.awt.*;

public class ColorHelper {
    protected static final int[] COLOR_RANGE = {0, 248};
    protected static final int COLOR_STEP = 8;


    /** Given a Color
     * @param color rounds its properties to the supported scaling factor of the ROM
     * @return the color int representation
     */
    public static int round(Color color){
        int r = (round(color.getRed()) << 16) & 0x00F80000; //Shift red 16-bits and mask out other stuff
        int g = (round(color.getGreen()) << 8) & 0x0000F800; //Shift Green 8-bits and mask out other stuff
        int b = round(color.getBlue()) & 0x000000F8; //Mask out anything not blue.
        return 0xFF000000 | r | g | b;
    }

    /** Given a
     * @param colorValue rounds it to the supported scaling factor of the ROM
     */
    private static int round(int colorValue){
        colorValue = Math.floorDiv(colorValue, COLOR_STEP) * COLOR_STEP;
        colorValue = Math.min(colorValue, COLOR_RANGE[1]);
        colorValue = Math.max(colorValue, COLOR_RANGE[0]);
        return colorValue;
    }
}
