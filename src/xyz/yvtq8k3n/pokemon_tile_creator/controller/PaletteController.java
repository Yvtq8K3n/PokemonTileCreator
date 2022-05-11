package xyz.yvtq8k3n.pokemon_tile_creator.controller;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel;
import xyz.yvtq8k3n.pokemon_tile_creator.model.ColorModel;
import xyz.yvtq8k3n.pokemon_tile_creator.model.Tileset;
import xyz.yvtq8k3n.pokemon_tile_creator.view.ApplicationView;

import java.awt.*;

import static xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel.OPERATOR_AREA;
import static xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel.OPERATOR_SINGLE;

public enum PaletteController{
    PALETTE_CONTROLLER;

    static ApplicationModel model;
    static ApplicationView view;

    public static void initController(ApplicationModel model, ApplicationView view) {
        PaletteController.model = model;
        PaletteController.view = view;
    }

    public static void loadSortedColors(){
        Tileset originalTileset = model.getOriginalTileset();
        if (originalTileset == null) return;

        ColorModel colorModel = originalTileset.getColorModel();
        if (colorModel == null) return;

        view.colorsPanelOriginal.setSortedColors(colorModel.getSortingMethod(),
                colorModel.getSortedColors());
    }

    public static void changeSortingMethod(){
        Tileset originalTileset = model.getOriginalTileset();
        if (originalTileset == null) return;

        ColorModel colorModel = originalTileset.getColorModel();
        if (colorModel == null) return;

        colorModel.changeSortingMethod();
    }

    //*Needs substantial changes into model
    //Create a new tmp tileset Apply over it the change
    public static void setImageColorFilter(Color color) {
        Operator currentOperator = model.getCurrentOperator();
        if(currentOperator == null) return;

        if(!model.isCurrentOperator(OPERATOR_SINGLE)){
            /*view.originalTilesetPanel.tileRepresentation.setColorFilter(color);
            if (operator.isC
            if (selectedOperator == operators[0]){
                view.loadPanel.blockRepresentation.setColorFilter(color);
            }*/
        }
    }


    public static void setDisplayBlockColor(Color color) {
        view.menuColorsPanel.setDisplayColor(color);
        view.menuColorsPanel.repaint();
    }

    public static void setDisplayBlockReplacing(Color color) {
        view.menuColorsPanel.setChangingColor(color);
        view.menuColorsPanel.repaint();
    }

}
