package xyz.yvtq8k3n.pokemon_tile_creator.model;

import lombok.Data;

import xyz.yvtq8k3n.pokemon_tile_creator.FileWatcher;
import xyz.yvtq8k3n.pokemon_tile_creator.HelperCreator;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static xyz.yvtq8k3n.pokemon_tile_creator.model.ColorModel.PALETTE_LIMIT;


@Data
public class Tileset {
    private MyFileWatcher fileWatcher;
    private File imageFile;
    private BufferedImage image;
    private ColorModel colorModel;

    public Tileset() {
        colorModel = new ColorModel();
    }

    public Tileset(File imageFile) throws IOException {
        this.imageFile = imageFile;
        this.image = HelperCreator.readImage(imageFile);
        this.colorModel = new ColorModel(image);

        if (fileWatcher != null) fileWatcher.stopThread();
        fileWatcher = new MyFileWatcher(imageFile);
        fileWatcher.start();
    }

    public void reloadImage() throws IOException {
        this.image = ImageIO.read(this.imageFile);
    }

    public boolean hasImage(){
        return image != null;
    }

    public boolean hasPalette(){
        return colorModel != null && colorModel.hasPalette();
    }

    //Converts the palette to a writable byte[]
    public byte[] getWritablePalette() {
       return colorModel.getWritablePalette();
    }

    public void generateImage(BufferedImage image, Color[] colors){
        BufferedImage image_clone = HelperCreator.copyImage(image);

        //Load both palettes
        Color[] palette = colorModel.getPalette();
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

    public boolean isSamePalette(Color[] palette) {
        return getColorModel().isSamePalette(palette);
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


