package xyz.yvtq8k3n.pokemon_tile_creator.controller;

import xyz.yvtq8k3n.pokemon_tile_creator.FileReader;
import xyz.yvtq8k3n.pokemon_tile_creator.FileWriter;
import xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel;
import xyz.yvtq8k3n.pokemon_tile_creator.model.Tileset;
import xyz.yvtq8k3n.pokemon_tile_creator.model.operators.AreaSelectorOperator;
import xyz.yvtq8k3n.pokemon_tile_creator.model.operators.MultiSelectorOperator;
import xyz.yvtq8k3n.pokemon_tile_creator.model.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.model.operators.SingleSelectorOperator;
import xyz.yvtq8k3n.pokemon_tile_creator.view.ApplicationView;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.AreaSelectableBehaviour;
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
    private static ArrayList<AreaSelectableBehaviour> areaSelectableBehaviours;
    private static ArrayList<MultiSelectableBehaviour> multiSelectableBehaviours;

    public static void launchApplication(ApplicationModel model, ApplicationView view) {
        //Create Operator
        MainController.operators = new Operator[]{
                new SingleSelectorOperator(),
                new AreaSelectorOperator(),
                new MultiSelectorOperator()
        };
        selectedOperator = operators[0];
        MainController.model = model;
        MainController.view = view;
    }

    public static void setDisplayBlock(BufferedImage image, int[] initialLocation) {
         view.loadPanel.blockRepresentation.setImage(image, initialLocation[0], initialLocation[1]);
    }

    public static void setDisplayColor(Color color) {
        view.menuColorsPanel.setSelectedColor(color);
        view.menuColorsPanel.repaint();
    }

    public static void setDisplayChangingColor(Color color) {
        view.menuColorsPanel.setChangingColor(color);
        view.menuColorsPanel.repaint();
    }

    public static void setImageColorFilter(Color color) {
        view.originalTilesetPanel.tileRepresentation.setColorFilter(color);
        if (selectedOperator == operators[0]){
            view.loadPanel.blockRepresentation.setColorFilter(color);
        }
    }



   /* public static void loadImage(File imageFile){
        model.createTileset(imageFile);
        view.originalTilesetPanel.tileRepresentation.setImage(model.getOriginalTileset().getImage());
        view.originalTilesetPanel.paletteRepresentation.setPalette(model.getOriginalTileset().getPalette());
        if (model.getGeneratedTileset().hasPalette()){
            view.tilesetPanelConverted.tileRepresentation.setImage(model.getGeneratedTileset().getImage());
        }
        view.loadPanel.blockRepresentation.setImage(model.getOriginalTileset().getImage());

        if (model.getOriginalTileset().hasPalette()){
            view.colorsPanelOriginal.pnlColorsRepresentation.setSortedColors(
                    model.getOriginalColorModel().getSortedColors());
            view.colorsPanelOriginal.lblCriteria.setText(
                    model.getOriginalColorModel().getSortingMethod());
        }
        updateView();
    }*/

    /*public static void changeColorSortingOrder() {
        if (model.getOriginalTileset().hasPalette()){
            model.getOriginalColorModel().changeSortingMethod();

            view.colorsPanelOriginal.pnlColorsRepresentation.setSortedColors(
                    model.getOriginalColorModel().getSortedColors());
            view.colorsPanelOriginal.lblCriteria.setText(
                    model.getOriginalColorModel().getSortingMethod());
            view.colorsPanelOriginal.pnlColorsRepresentation.sortMethodChanged();
        }
        updateView();
    }*/


    public static void updateView() {
        view.repaint();
    }


    public static void setOperatorSelection() {
        if (!selectedOperator.equals(operators[0])){
            System.out.println("Setting selector: Single Selector");
            selectedOperator = operators[0];
            reset();
        }
    }

    public static void setOperatorAreaSelection() {
         if (!selectedOperator.equals(operators[1])){
            System.out.println("Setting selector: Area Selector");
            selectedOperator = operators[1];
            reset();
         }
    }

    public static void setOperatorMultiSelection() {
        if (!selectedOperator.equals(operators[2])){
            System.out.println("Setting selector: Area Selector");
            selectedOperator = operators[2];
            reset();
        }
    }

    public static void addSelectableBehaviour(SelectableBehaviour c){
        if (selectableBehaviours == null){
            selectableBehaviours = new ArrayList<>();
        }
        MainController.selectableBehaviours.add(c);
    }

    public static void addAreaSelectableBehaviour(AreaSelectableBehaviour c){
        if (areaSelectableBehaviours == null){
            areaSelectableBehaviours = new ArrayList<>();
        }
        MainController.areaSelectableBehaviours.add(c);
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
