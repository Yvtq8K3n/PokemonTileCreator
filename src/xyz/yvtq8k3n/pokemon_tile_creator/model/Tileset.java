package xyz.yvtq8k3n.pokemon_tile_creator.model;

import lombok.Data;

import xyz.yvtq8k3n.pokemon_tile_creator.FileWatcher;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

@Data
public class Tileset {
    protected static final int PALETTE_LIMIT = 16;
    private File imageFile;
    private BufferedImage image;
    private Color[] palette;
    private ColorModel colorModel;
    private MyFileWatcher fileWatcher;

    protected Tileset() {
        this.palette = new Color[0];
    }

    public Tileset(File imageFile) throws IOException {
        super();
        this.imageFile = imageFile;
        this.image = TileHelper.readImage(imageFile);
        this.colorModel = new ColorModel(this);
        this.palette = calculatePalette();

        if (fileWatcher != null) fileWatcher.stopThread();
        fileWatcher = new MyFileWatcher(imageFile);
        fileWatcher.start();
    }

    public boolean hasImage(){
        return image != null;
    }

    public void generateImage(BufferedImage image, Color[] colors){
        BufferedImage image_clone = TileHelper.copyImage(image);

        //Load both palettes
        ArrayList<Color> colorsArray = new ArrayList<>(Arrays.asList(colors));

        //Match Converted Palette with Original Palette by index
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                int colorIndex = colorsArray.indexOf(pixelColor);
                if (colorIndex == -1) colorIndex = PALETTE_LIMIT - 1;
                image_clone.setRGB(x, y, palette[colorIndex].getRGB());
            }
        }
        this.image = image_clone;
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

    public Color[] calculatePalette() {
        Color[] distinctColors = colorModel.getAllDistinctColors();
        int limit = Math.min(PALETTE_LIMIT, distinctColors.length);
        return Arrays.copyOfRange(distinctColors, 0, limit);
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

    class MyFileWatcher extends FileWatcher {
        public MyFileWatcher(File file) {
            super(file);
            setDelay(200);
        }

        int count = 1;
        @Override
        protected void doOnChange() {
            MainController.loadImage(imageFile);
        }
    }
}


