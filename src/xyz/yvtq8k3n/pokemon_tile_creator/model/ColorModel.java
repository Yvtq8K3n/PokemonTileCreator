package xyz.yvtq8k3n.pokemon_tile_creator.model;

import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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
        System.out.println("Colors:"+colors.length);
        System.out.println("Palette:"+palette.length);
    }

    private void retrieveColors() {
        ArrayList<Color> colors = new ArrayList<>();
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                if (!colors.contains(pixelColor)) colors.add(pixelColor);
            }
        }
        this.colors = colors.toArray(new Color[colors.size()]);
    }

    private void calculatePalette() {
        int limit = Math.min(PALETTE_LIMIT, colors.length);
        this.palette = Arrays.copyOfRange(colors, 0, limit);
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

    //Converts the palette to a writable byte[]
    public byte[] getWritablePalette() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (Color color: palette) {
            outputStream.write(color.getBlue());
            outputStream.write(color.getGreen());
            outputStream.write(color.getRed());
            outputStream.write(color.getAlpha());
        }
        return outputStream.toByteArray();
    }
}
