package xyz.yvtq8k3n.pokemon_tile_creator.view.representations;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class BlockRepresentation extends Representation {
    protected static final int[] DISPLAY_DIMENSIONS = {128, 128};

    public BlockRepresentation() {
        super();
        setPreferredSize(TileHelper.createDimension(DISPLAY_DIMENSIONS));
        setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Can hava press event
        //On Color shows rgbs
        //On Image shows grid
    }
}