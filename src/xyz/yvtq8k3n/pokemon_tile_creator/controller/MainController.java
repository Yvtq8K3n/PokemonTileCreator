package xyz.yvtq8k3n.pokemon_tile_creator.controller;

import xyz.yvtq8k3n.pokemon_tile_creator.FileReader;
import xyz.yvtq8k3n.pokemon_tile_creator.FileWriter;
import xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel;
import xyz.yvtq8k3n.pokemon_tile_creator.model.Tileset;
import xyz.yvtq8k3n.pokemon_tile_creator.operators.MultiSelectorOperator;
import xyz.yvtq8k3n.pokemon_tile_creator.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.operators.SelectorOperator;
import xyz.yvtq8k3n.pokemon_tile_creator.view.ApplicationView;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.MultiSelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.SelectableBehaviour;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public enum MainController {
    MAIN_CONTROLLER;

    static ApplicationModel model;
    static ApplicationView view;

    static Operator[] operators;
    public static Operator selectedOperator;

    private static ArrayList<SelectableBehaviour> selectableBehaviours;
    private static ArrayList<MultiSelectableBehaviour> multiSelectableBehaviours;

    public static void launchApplication(ApplicationModel model, ApplicationView view) {
        //Create Operator
        MainController.operators = new Operator[]{
                new SelectorOperator(),
                new MultiSelectorOperator()
        };
        selectedOperator = operators[0];
        MainController.model = model;
        MainController.view = view;
    }

    public static void setDisplayBlock(BufferedImage image, int[] initialLocation) {
         view.blockDisplay.setImage(image, initialLocation[0], initialLocation[1]);
    }

    public static void setImageColorFilter(Color color) {
        if (selectedOperator == operators[0]){
            view.blockDisplay.setColorFilter(color);
        }
        if (selectedOperator == operators[1]){
            view.imgDisplayOriginal.pnlTileRepresentation.setColorFilter(color);
        }
    }

    public static void loadPalette(){
        //Call Helper File Reader to load Palette
        byte [] palette = FileReader.loadPalette();
        model.addNewPalette(palette);

        //Call Model and Update
        view.imgDisplayConverted.pnlPaletteRepresentation.setPalette(model.getTilesetConverted().getColorModel().getPalette());
        if (model.getTilesetOriginal().hasImage()){
            view.imgDisplayConverted.pnlTileRepresentation.setImage(model.getTilesetConverted().getImage());
        }
        updateView();
    }

    public static void loadImage(){
        //Call Helper File Reader to load Image
        File image = FileReader.loadImage();
        loadImage(image);
    }

    public static void loadImage(File imageFile){
        model.createTileset(imageFile);
        view.imgDisplayOriginal.pnlTileRepresentation.setImage(model.getTilesetOriginal().getImage());
        view.imgDisplayOriginal.pnlPaletteRepresentation.setPalette(model.getTilesetOriginal().getColorModel().getPalette());
        if (model.getTilesetConverted().hasPalette()){
            view.imgDisplayConverted.pnlTileRepresentation.setImage(model.getTilesetConverted().getImage());
        }
        view.blockDisplay.setImage(model.getTilesetOriginal().getImage());
        updateView();
    }

    public static void updateView() {
        view.repaint();
    }

    public static void exportTileset() {
        if (model.hasConvertedImage()){
            Tileset paletteConverted = model.getTilesetConverted();
            FileWriter.writeTileset(paletteConverted.getImage(),
                    model.getTilesetOriginal().getWritablePalette());
        }
    }


    public static void setOperatorSelection() {
        if (!selectedOperator.equals(operators[0])){
            System.out.println("Setting selector: Single Selector");
            selectedOperator = operators[0];
            reset();
        }
    }

    public static void setOperatorMultiSelection() {
         if (!selectedOperator.equals(operators[1])){
            System.out.println("Setting selector: Multi Selector");
            selectedOperator = operators[1];
            reset();
        }
    }



    public static void addSelectableBehaviour(SelectableBehaviour c){
        if (selectableBehaviours == null){
            selectableBehaviours = new ArrayList<>();
        }
        MainController.selectableBehaviours.add(c);
    }

    public static void addMultiSelectableBehaviour(MultiSelectableBehaviour c){
        if (multiSelectableBehaviours == null){
            multiSelectableBehaviours = new ArrayList<>();
        }
        MainController.multiSelectableBehaviours.add(c);
    }

    public static void reset() {
        for (SelectableBehaviour c: selectableBehaviours) {
            c.reset();
        }
    }

}
