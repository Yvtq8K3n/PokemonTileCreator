package xyz.yvtq8k3n.pokemon_tile_creator.model;

import lombok.Data;

import java.awt.image.BufferedImage;

@Data
public class ApplicationModel {
    PaletteImage paletteOriginal;
    PaletteImage paletteConverted;

    public ApplicationModel() {
        this.paletteOriginal = new PaletteImage();
        this.paletteConverted = new PaletteImage();
    }

    public void addImage(BufferedImage image){
        if (image == null) throw new IllegalArgumentException("Expecting an image got invalid object");
        this.paletteOriginal.setImage(image);
    }
}
