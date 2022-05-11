package xyz.yvtq8k3n.pokemon_tile_creator.controller;

import xyz.yvtq8k3n.pokemon_tile_creator.FileReader;
import xyz.yvtq8k3n.pokemon_tile_creator.FileWriter;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel;
import xyz.yvtq8k3n.pokemon_tile_creator.model.Tileset;
import xyz.yvtq8k3n.pokemon_tile_creator.view.ApplicationView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public enum LoadController{
    LOAD_CONTROLLER;

    static ApplicationModel model;
    static ApplicationView view;

    public static void initController(ApplicationModel model, ApplicationView view) {
        LoadController.model = model;
        LoadController.view = view;
    }

    public static void loadImageFromFile(){
        //Call Helper File Reader to load Image
        File imageFile = FileReader.loadImage();
        if (imageFile == null) throw new IllegalArgumentException("Expecting an image got invalid object");

        BufferedImage image = TileHelper.readImage(imageFile);
        if (image == null) throw new IllegalArgumentException("Unable to convert file to a valid BufferedImage");

        Tileset originalTileset = new Tileset(
                imageFile,
                image
        );
        model.setOriginalTileset(originalTileset);
        view.originalTilesetPanel.setTileset(originalTileset);
    }

    public static void loadPalette(){
        //Call Helper File Reader to load Palette
        byte [] bytePalette = FileReader.loadPalette();
        if (bytePalette.length == 0) throw new IllegalArgumentException("Invalid Palette");

        Color[] colors = TileHelper.readPalette(bytePalette);
        if (colors.length == 0) throw new IllegalArgumentException("Invalid Palette"); //May be always 16 needs verification

        Tileset generatedTileset = new Tileset(
            colors
        );
        model.setGeneratedTileset(generatedTileset);
        view.generatedTilesetPanel.setPalette(generatedTileset);
    }

    public static void reloadTilesets(){
        Tileset tilesetOriginal = model.getOriginalTileset();
        BufferedImage image = TileHelper.readImage(tilesetOriginal.getFileImage());
        if (image == null) throw new IllegalArgumentException("Unable to convert file to a valid BufferedImage");

        tilesetOriginal.updateImage(image);
        view.originalTilesetPanel.repaint();

        Tileset tilesetGenerated = model.getGeneratedTileset();
        if(tilesetGenerated != null) tilesetGenerated.generateImage(tilesetOriginal);
        view.generatedTilesetPanel.repaint();
    }

    public static void createGenerateTileset(){
        Tileset originalTileset = model.getOriginalTileset();
        if (originalTileset == null) return;

        Tileset generatedTileset = model.getGeneratedTileset();
        if (generatedTileset == null) return;

        if (originalTileset.hasImage()
                && generatedTileset.hasPalette()){
            generatedTileset.generateImage(originalTileset);
            view.generatedTilesetPanel.setTileset(generatedTileset);
        }
    }

    public static void exportTileset() {
        Tileset generatedTileset = model.getGeneratedTileset();
        if (generatedTileset == null) return;

        if (generatedTileset.hasImage()){
            byte[] bytePalette = TileHelper.getWritablePalette(generatedTileset.getPalette());
            FileWriter.writeTileset(generatedTileset.getImage(), bytePalette);
        }
    }

    public static void setDisplayBlock(BufferedImage image, int[] initialLocation) {
        view.loadPanel.blockRepresentation.setImage(image, initialLocation[0], initialLocation[1]);
    }
}
