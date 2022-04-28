package xyz.yvtq8k3n.pokemon_tile_creator.model;

import lombok.Data;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

@Data
public class ApplicationModel {
    Tileset paletteOriginal;
    Tileset paletteConverted;

    public ApplicationModel() {
        this.paletteOriginal = new Tileset();
        this.paletteConverted = new Tileset();
    }

    public void addImageFile(File imageFile) {
        if (imageFile == null) throw new IllegalArgumentException("Expecting an image got invalid object");
        try {
            this.paletteOriginal.setImageFile(imageFile);
        } catch (Exception e) {
            throw new IllegalArgumentException("Expecting an image got invalid object");
        }
    }

    public void reload() {
        try {
            this.paletteOriginal.loadImage();
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to reload the image");
        }
    }

    //Applies in case the final palette is the source of the provided image
    public void correctTileset(){
        ArrayList<Color> colors = new ArrayList<>(Arrays.asList(paletteConverted.getPalette()));
        Color[] generatedPalette = paletteOriginal.getPalette();
        for (int i = 0; i < colors.size(); i++) {
            if (!colors.contains(generatedPalette[i])) return;
        }

        //Replace generated Image and Tileset
        paletteConverted.setImage(paletteOriginal.getImage());
        paletteOriginal.setPalette(paletteConverted.getPalette());
    }

    public void addNewPalette(byte[] palette) {
        if (palette.length == 0) throw new IllegalArgumentException("Invalid Palette");
        this.paletteConverted.addPalette(palette);
    }

    public void convertImage(){
        if (paletteOriginal.hasImage()
                && paletteConverted.hasPalette()){
            paletteConverted.applyPalette(paletteOriginal.getImage(), paletteOriginal.getPalette());
            correctTileset();
        }
    }

    public boolean hasConvertedImage(){
        return paletteConverted.hasImage();
    }

}
