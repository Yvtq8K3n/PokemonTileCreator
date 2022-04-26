package xyz.yvtq8k3n.pokemon_tile_creator.controller;

import xyz.yvtq8k3n.pokemon_tile_creator.FileReader;
import xyz.yvtq8k3n.pokemon_tile_creator.FileWriter;
import xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel;
import xyz.yvtq8k3n.pokemon_tile_creator.model.Tileset;
import xyz.yvtq8k3n.pokemon_tile_creator.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.operators.ResetOperator;
import xyz.yvtq8k3n.pokemon_tile_creator.operators.SelectorOperator;
import xyz.yvtq8k3n.pokemon_tile_creator.view.ApplicationView;
import xyz.yvtq8k3n.pokemon_tile_creator.view.SelectableBehaviour;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public enum MainController {
    MAIN_CONTROLLER;

    static ApplicationModel model;
    static ApplicationView view;

    static Operator[] operators;
    public static Operator selectedOperator;

    private static ArrayList<SelectableBehaviour> selectableBehaviours;

    public static void launchApplication(ApplicationModel model, ApplicationView view) {
        //Create Operator
        MainController.operators = new Operator[]{
                new ResetOperator(),
                new SelectorOperator()
        };
        selectedOperator = operators[0];
        MainController.model = model;
        MainController.view = view;
    }

    public static void setDisplayBlock(BufferedImage image, int x, int y) {
        view.blockDisplay.setImage(image, x, y);
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
            FileWriter.writeTileset(paletteConverted.getImage(),
                    model.getPaletteOriginal().getWritablePalette());
        }
    }

    public void changeState() {
        Operator operator = operators[0];
        if (selectedOperator == operator){
            selectedOperator = operators[1];
        }else{
            selectedOperator = operators[0];
            reset();
        }
    }

    public void addCustomBehaviourComponents(SelectableBehaviour c){
        if (selectableBehaviours == null){
            selectableBehaviours = new ArrayList<>();
        }
        MainController.selectableBehaviours.add(c);
    }

    public void reset() {
        for (SelectableBehaviour c: selectableBehaviours) {
            c.reset();
        }
    }
}
