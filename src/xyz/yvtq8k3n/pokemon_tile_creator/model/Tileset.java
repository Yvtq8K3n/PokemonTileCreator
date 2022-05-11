package xyz.yvtq8k3n.pokemon_tile_creator.model;

import lombok.Data;

import xyz.yvtq8k3n.pokemon_tile_creator.FileWatcher;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.LoadController;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

@Data
public class Tileset {
    public static final int PALETTE_LIMIT = 16;

    private File fileImage;
    private BufferedImage image;
    private Color[] palette;

    private ColorModel colorModel;
    private MyFileWatcher fileWatcher; //I dont like this

    public Tileset(Color[] palette) {
        this.palette = palette;
    }

    public Tileset(File fileImage, BufferedImage image) {
        super();
        this.fileImage = fileImage;
        this.image = image;
        this.colorModel = new ColorModel(this);
        this.palette = calculatePalette();

        if (fileWatcher != null) fileWatcher.stopThread();
        fileWatcher = new MyFileWatcher(fileImage);
        fileWatcher.start();
    }

    public void updateImage(BufferedImage image){
        this.image = image;
        this.colorModel = new ColorModel(this);
        this.palette = calculatePalette();
    }

    public void updatePalette(Color[] palette){
        this.palette = calculatePalette();
        this.colorModel = new ColorModel(this);
    }


    public boolean hasImage(){
        return image != null;
    }


    public void generateImage(Tileset originalTileset){
        Color[] originalPalette = originalTileset.getPalette();
        if (isSamePalette(originalPalette)){
            setPalette(originalPalette);
        }

        generateImageFromTileset(originalTileset);
    }

    private void generateImageFromTileset(Tileset originalTileset){
        BufferedImage image = TileHelper.copyImage(originalTileset.getImage());
        ArrayList<Color> paletteList = new ArrayList<>(Arrays.asList(originalTileset.getPalette()));

        //Match Converted Palette with Original Palette by index
        for (int x = 0; x < image.getWidth() - 1; x++) {
            for (int y = 0; y < image.getHeight() - 1; y++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                int colorIndex = paletteList.indexOf(pixelColor);
                if (colorIndex == -1) colorIndex = PALETTE_LIMIT - 1;
                image.setRGB(x, y, palette[colorIndex].getRGB());
            }
        }
        this.image = image;
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


    class MyFileWatcher extends FileWatcher {
        public MyFileWatcher(File file) {
            super(file);
            setDelay(200);
        }

        @Override
        protected void doOnChange() {
            LoadController.reloadTilesets();
        }
    }
}


