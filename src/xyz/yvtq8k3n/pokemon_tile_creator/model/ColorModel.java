package xyz.yvtq8k3n.pokemon_tile_creator.model;

import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

@Data
public class ColorModel {
    protected static final int PALETTE_LIMIT = 16;
    private BufferedImage image;
    private Color[] palette;
    private Color[] colors;

    public ColorModel() {
        palette = new Color[0];
    }

    public ColorModel(BufferedImage image) {
        super();
        this.image = image;
        retrieveColors();
        calculatePalette();
    }

    private void retrieveColors() {
    }

    private void calculatePalette() {
        Color[] palette = new Color[PALETTE_LIMIT];

        int count = 0;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color pixelColor = new Color(image.getRGB(x, y));

                boolean isOnPalette = false;
                for (int k = 0; k<count; k++){
                    if (pixelColor.equals(palette[k])){
                        isOnPalette = true;
                        break;
                    }
                }

                //Check if is already on palette
                if (!isOnPalette && count < PALETTE_LIMIT) {
                    palette[count] = pixelColor;
                    count++;
                }
            }
        }
        this.palette = palette;
    }

    public void addPalette(byte[] palette) {
        Color[] colors = new Color[PALETTE_LIMIT];

        int counter = 0;
        for (int i=0;i<palette.length;i+=4) {
            byte blue = palette[i];
            byte green = palette[i+1];
            byte red = palette[i+2];
            colors [counter] = new Color(red & 0xFF, green & 0xFF, blue & 0xFF);
            counter++;
        }
        this.palette = colors;
    }

    public boolean hasPalette(){
        return palette.length > 0;
    }


    public boolean isSamePalette(Color[] convertPalette) {
        ArrayList<Color> originalPalette = new ArrayList<>(Arrays.asList(palette));
        for (Color colorSlot:convertPalette){
            if (!originalPalette.contains(colorSlot)) return false;
        }
        return true;
    }
}
