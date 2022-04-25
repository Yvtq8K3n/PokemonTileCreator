package xyz.yvtq8k3n.pokemon_tile_creator.controller;

import xyz.yvtq8k3n.pokemon_tile_creator.FileReader;
import xyz.yvtq8k3n.pokemon_tile_creator.FileWriter;
import xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel;
import xyz.yvtq8k3n.pokemon_tile_creator.model.Tileset;
import xyz.yvtq8k3n.pokemon_tile_creator.view.ApplicationView;

import java.awt.image.BufferedImage;

public enum MainController {
    MAIN_CONTROLLER;

    static ApplicationModel model;
    static ApplicationView view;
    public static void launchApplication(ApplicationModel model, ApplicationView view) {
        MainController.model = model;
        MainController.view = view;
    }

    public void loadPalette(){
        //Call Helper File Reader to load Palette
        byte [] palette = FileReader.loadPalette();
        model.addNewPalette(palette);

        //Call Model and Update
        Tileset paletteConverted = model.getPaletteConverted();
        view.imgDisplayConverted.pnlPaletteRepresentation.setPalette(paletteConverted.getPalette());

        convertImage();
        updateView();
    }

    public void loadImage(){
        //Call Helper File Reader to load Image
        BufferedImage image = FileReader.loadImage();
        model.addImage(image);
        view.imgDisplayOriginal.pnlTileRepresentation.setImage(model.getPaletteOriginal().getImage());
        view.imgDisplayOriginal.pnlPaletteRepresentation.setPalette(model.getPaletteOriginal().getPalette());

        convertImage();
        updateView();
    }

    public void convertImage(){
        model.convertImage();
        view.imgDisplayConverted.pnlTileRepresentation.setImage(model.getPaletteConverted().getImage());
        view.imgDisplayOriginal.pnlPaletteRepresentation.setPalette(model.getPaletteOriginal().getPalette());
    }

    public static void updateView() {
        view.repaint();
    }

    public void exportTileset() {
        if (model.hasConvertedImage()){
            Tileset paletteConverted = model.getPaletteConverted();
            FileWriter.exportTileset(paletteConverted.getImage());
        }
    }
}
