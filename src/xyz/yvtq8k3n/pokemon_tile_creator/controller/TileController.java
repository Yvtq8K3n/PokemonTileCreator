package xyz.yvtq8k3n.pokemon_tile_creator.controller;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel;
import xyz.yvtq8k3n.pokemon_tile_creator.model.ColorModel;
import xyz.yvtq8k3n.pokemon_tile_creator.model.Tileset;
import xyz.yvtq8k3n.pokemon_tile_creator.view.ApplicationView;

import java.awt.*;
import java.util.List;

import static xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel.OPERATOR_SINGLE;

public enum TileController{
    TILE_CONTROLLER;

    static ApplicationModel model;
    static ApplicationView view;

    public static void initController(ApplicationModel model, ApplicationView view) {
        TileController.model = model;
        TileController.view = view;
    }

    public static List<Point> getColor(Color color){
        Tileset originalTileset = model.getOriginalTileset();
        if (originalTileset == null) throw new IllegalArgumentException("Tileset doesnt exist");

        ColorModel colorModel = originalTileset.getColorModel();
        return colorModel.getColorLocations(color);
    }

    public static void setColorFilter(Color color) {
        view.originalTilesetPanel.tileRepresentation.setColorFilter(color);
    }
}
