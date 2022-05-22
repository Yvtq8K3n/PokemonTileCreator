package xyz.yvtq8k3n.pokemon_tile_creator.view.representations;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;

import javax.swing.*;
import java.awt.*;

public abstract class BlockRepresentation extends Representation {
    protected static final int DISPLAY_DIMENSION = 128;
    public static final int STEP = 8;

    public BlockRepresentation() {
        super();
        setPreferredSize(TileHelper.createDimension(new int[]{DISPLAY_DIMENSION, DISPLAY_DIMENSION}));
        setBorder(BorderFactory.createLineBorder(Color.RED));
    }
}