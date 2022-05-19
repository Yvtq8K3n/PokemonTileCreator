package xyz.yvtq8k3n.pokemon_tile_creator.controller;

import xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel;
import xyz.yvtq8k3n.pokemon_tile_creator.model.ColorModel;
import xyz.yvtq8k3n.pokemon_tile_creator.model.Tileset;
import xyz.yvtq8k3n.pokemon_tile_creator.view.ApplicationView;

import java.awt.*;
import java.util.List;

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

    public static void addSelectedColor(Color color) {
        Tileset originalTileset = model.getOriginalTileset();
        if (originalTileset == null) return;

        ColorModel colorModel = originalTileset.getColorModel();
        if (colorModel == null) return;

        colorModel.setCurrentColor(color);
        view.menuColorsPanel.setColorBlockSlot1(color);

        //If multi is selected add to list too
        if (model.isCurrentOperator(ApplicationModel.OPERATOR_MULTI)){
            colorModel.addSelectedColor(color);
            view.colorsPanelOriginal.updateSelectedColors();
        }
    }

    public static void removeSelectedColor(Color color) {
        if (model.isCurrentOperator(ApplicationModel.OPERATOR_MULTI)){
            Tileset originalTileset = model.getOriginalTileset();
            if (originalTileset == null) return;

            ColorModel colorModel = originalTileset.getColorModel();
            if (colorModel == null) return;
            colorModel.removeSelectedColor(color);

            view.colorsPanelOriginal.updateSelectedColors();
        }
    }

    public static void setColorBlockSlot2(Color color) {
        Tileset originalTileset = model.getOriginalTileset();
        if (originalTileset == null) return;

        ColorModel colorModel = originalTileset.getColorModel();
        if (colorModel == null) return;

        Color original = colorModel.getCurrentColor();
        view.originalTilesetPanel.tileRepresentation.setColorFilter(original, color);
        view.menuColorsPanel.setColorBlockSlot2(color);
    }

    public static List<Color> getSelectedColors() {
        Tileset originalTileset = model.getOriginalTileset();
        if (originalTileset == null) throw new IllegalArgumentException("Invalid Tileset");

        ColorModel colorModel = originalTileset.getColorModel();
        if (colorModel == null) throw new IllegalArgumentException("Invalid colorModel");
        return colorModel.getSelectedColors();
    }

}
