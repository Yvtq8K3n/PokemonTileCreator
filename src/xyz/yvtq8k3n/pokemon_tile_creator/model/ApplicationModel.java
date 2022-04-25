package xyz.yvtq8k3n.pokemon_tile_creator.model;

import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;
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

    public void addImage(BufferedImage image){
        if (image == null) throw new IllegalArgumentException("Expecting an image got invalid object");
        this.paletteOriginal.setImage(image);
        this.paletteOriginal.calculatePalette();


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
