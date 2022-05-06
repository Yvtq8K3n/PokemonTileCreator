package xyz.yvtq8k3n.pokemon_tile_creator.view.panel;

import javax.swing.*;
import java.awt.*;

public abstract class ActionPanel extends JPanel {
    protected static final int[] MENU_DIMENSION = {128, 186};
    protected static final int[] FILLER_DIMENSIONS = {1, 5};

    class CustomFlow extends FlowLayout{
        public CustomFlow(int align) {
            super(align);
            setHgap(0);
            setVgap(0);
        }
    }
}
