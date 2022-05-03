package xyz.yvtq8k3n.pokemon_tile_creator.view.panel;

import javax.swing.*;
import java.awt.*;

public abstract class ActionPanel extends JPanel {

    class CustomFlow extends FlowLayout{
        public CustomFlow(int align) {
            super(align);
            setHgap(0);
            setVgap(0);
        }
    }
}
