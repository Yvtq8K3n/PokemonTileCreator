package xyz.yvtq8k3n.pokemon_tile_creator.controller;

import xyz.yvtq8k3n.pokemon_tile_creator.FileReader;
import xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel;
import xyz.yvtq8k3n.pokemon_tile_creator.view.ApplicationView;

import java.awt.image.BufferedImage;

public enum MainController {
    MAIN_CONTROLLER;

    static ApplicationModel model;
    static ApplicationView view;
    public static void launchApplication(ApplicationModel model, ApplicationView view) {
        MAIN_CONTROLLER.model = model;
        MAIN_CONTROLLER.view = view;
    }

    public void loadPalette(){
        //Call Helper File Reader to load Palette
        //Write to model

    }

    public void loadImage(){
        //Call Helper File Reader to load Image
        BufferedImage image = FileReader.loadImage();
        model.addImage(image);
        view.imgDisplayOriginal.pnlTileRepresentation.setImage(model.getPaletteOriginal().getImage());

        updateView();
    }

    public void convertImage(){

    }

    public void exportImage() {
        //Read Model
        //Call Helper File Reader to export Image
        //Write to model
    }

    public static void updateView() {
        view.repaint();
    }
}
