package xyz.yvtq8k3n.pokemon_tile_creator;

import java.awt.*;

import static xyz.yvtq8k3n.pokemon_tile_creator.TileHelper.BLOCK;

public abstract class ColorHelper {
    protected static final int[] COLOR_RANGE = {0, 248};
    public static final int COLOR_STEP = 8;

    public static double calculateLuminosity(Color color){
        double red = (float) (color.getRed() / 255.0);
        double green = (float) (color.getGreen() / 255.0);
        double blue = (float) (color.getBlue() / 255.0);

        return Math.sqrt( 0.241 * red + 0.691 * green + 0.068 * blue);

        //return Math.pow((0.299 * red + 0.587 * green + 0.114 * blue), 1/2.2);
    }

    public static double[] convertRGBToHSV(Color color){
        //1. Divide r, g, b by 255
        // R, G, B values are divided by 255
        // to change the range from 0..255 to 0..1
        float red = (float) (color.getRed() / 255.0);
        float green = (float) (color.getGreen() / 255.0);
        float blue = (float) (color.getBlue() / 255.0);

        //2. Compute cmax, cmin, difference
        // h, s, v = hue, saturation, value
        double cmax = Math.max(red, Math.max(green, blue)); // maximum of r, g, b
        double cmin = Math.min(red, Math.min(green, blue)); // minimum of r, g, b
        double diff = cmax - cmin; // diff of cmax and cmin.
        double h = -1, s;

        //3. Hue calculation (Circle of colors)
        if (cmax == cmin) h = 0;
        else if (cmax == red) h = (60 * ((green - blue) / diff) + 360) % 360 / 360;
        else if (cmax == green) h = (60 * ((blue - red) / diff) + 120) % 360 / 360;
        else if (cmax == blue) h = (60 * ((red - green) / diff) + 240) % 360 / 360;

        //4. Hue calculation
        if (cmax == 0)  s = 0;
        else s = (diff / cmax);

        // compute v

        return new double[]{h,s, cmax};
    }


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
    public static int round(int colorValue){
        colorValue = Math.floorDiv(colorValue, COLOR_STEP) * COLOR_STEP;
        colorValue = Math.min(colorValue, COLOR_RANGE[1]);
        colorValue = Math.max(colorValue, COLOR_RANGE[0]);
        return colorValue;
    }
}
