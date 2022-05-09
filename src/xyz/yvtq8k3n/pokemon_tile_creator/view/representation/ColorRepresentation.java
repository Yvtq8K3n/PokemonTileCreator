package xyz.yvtq8k3n.pokemon_tile_creator.view.representation;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;

import javax.swing.*;
import java.awt.*;

public class ColorRepresentation extends Representation{
    public static final int[] DISPLAY_DIMENSIONS = {72, 72};
    protected Color selectedColor;

    public ColorRepresentation() {
        setPreferredSize(TileHelper.createDimension(DISPLAY_DIMENSIONS));
        setBorder(BorderFactory.createLineBorder(Color.RED));
    }
}
