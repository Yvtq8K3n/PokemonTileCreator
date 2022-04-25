package xyz.yvtq8k3n.pokemon_tile_creator.view;

import xyz.yvtq8k3n.pokemon_tile_creator.HelperCreator;

import javax.swing.*;
import java.awt.*;

public class BlockDisplay extends JPanel {
    private static final int[] BLOCK = {50, 50};

    public BlockDisplay() {
       setPreferredSize(HelperCreator.createDimension(BLOCK));
       setBorder(BorderFactory.createLineBorder(Color.RED));
    }
}
