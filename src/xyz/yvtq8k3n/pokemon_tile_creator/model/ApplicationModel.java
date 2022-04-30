package xyz.yvtq8k3n.pokemon_tile_creator.model;

import lombok.Data;
import java.io.*;

@Data
public class ApplicationModel {
    Tileset tilesetOriginal;
    Tileset tilesetConverted;

    public ApplicationModel() {
        this.tilesetOriginal = new Tileset();
        this.tilesetConverted = new Tileset();
    }

    public void createTileset(File imageFile) {
        if (imageFile == null) throw new IllegalArgumentException("Expecting an image got invalid object");
        try {
            this.tilesetOriginal = new Tileset(imageFile);
            applyPaletteToImage();
        } catch (Exception e) {
            throw new IllegalArgumentException("Expecting an image got invalid object");
        }
    }

    public void addNewPalette(byte[] palette) {
        if (palette.length == 0) throw new IllegalArgumentException("Invalid Palette");
        this.tilesetConverted.getColorModel().addPalette(palette);
        applyPaletteToImage();
    }

    public void applyPaletteToImage(){
        if (tilesetOriginal.hasImage()
                && tilesetConverted.hasPalette()){
            //Rearrange palette if it matches previous loaded palette
            if (tilesetOriginal.isSamePalette(tilesetConverted.getColorModel().getPalette())){
                tilesetOriginal.getColorModel().setPalette(tilesetConverted.getColorModel().getPalette());
            }

            //Automatically generates Image when both Palette and Image are loaded
            tilesetConverted.generateImage(tilesetOriginal.getImage(),
            tilesetOriginal.getColorModel().getPalette());
        }
    }

    public boolean hasConvertedImage(){
        return tilesetConverted.hasImage();
    }

}
