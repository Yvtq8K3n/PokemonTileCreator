package xyz.yvtq8k3n.pokemon_tile_creator.model;

import lombok.Data;

import org.apache.log4j.helpers.FileWatchdog;
import xyz.yvtq8k3n.pokemon_tile_creator.HelperCreator;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

@Data
public class Tileset {
    private SomeWatchFile watchFile;
    private static final int PALETTE_LIMIT = 16;
    private File imageFile;
    private BufferedImage image;
    private Color[] palette;

    public Tileset() {
        palette = new Color[0];
    }

    public void setImageFile(File imageFile) throws IOException {
        this.imageFile = imageFile;
        loadImage();
        calculatePalette();

        //Create watcher for file
        if (watchFile != null) watchFile.stop();
        watchFile = new SomeWatchFile(imageFile.getPath());
        watchFile.start();
    }

    public void loadImage() throws IOException {
        this.image = ImageIO.read(this.imageFile);
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

    private void calculatePalette() {
        Color[] colors = new Color[PALETTE_LIMIT];

        int count = 0;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color pixelColor = new Color(image.getRGB(x, y));

                boolean isOnPalette = false;
                for (int k = 0; k<count; k++){
                    if (pixelColor.equals(colors[k])){
                        isOnPalette = true;
                        break;
                    }
                }

                //Check if is already on palette
                if (!isOnPalette && count < PALETTE_LIMIT) {
                    colors[count] = pixelColor;
                    count++;
                }
            }
        }

        this.palette = colors;
    }

    public boolean hasImage(){
        return image != null;
    }

    public boolean hasPalette(){
        return palette.length > 0;
    }

    public void applyPalette(BufferedImage image, Color[] colors) {
        BufferedImage image_clone = HelperCreator.copyImage(image);

        ArrayList<Color> colorsArray = new ArrayList<>(Arrays.asList(colors));
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                int colorIndex = colorsArray.indexOf(pixelColor);
                if (colorIndex == -1) colorIndex = PALETTE_LIMIT - 1;
                image_clone.setRGB(x, y, this.palette[colorIndex].getRGB());
            }
        }
        this.image = image_clone;
    }

    //Converts the palette to a writable byte[]
    public byte[] getWritablePalette() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (Color color:palette) {
            outputStream.write(color.getRed());
            outputStream.write(color.getGreen());
            outputStream.write(color.getBlue());
            outputStream.write(color.getAlpha());
        }
        return outputStream.toByteArray();
    }

    class SomeWatchFile extends FileWatchdog {
        protected SomeWatchFile(String filename) {
            super(filename);
            setDelay(1000);
        }

        int count = 1;
        @Override
        protected void doOnChange() {
            MainController.reloadImage();
            System.out.println(count);
            count ++;
        }
    }
}


