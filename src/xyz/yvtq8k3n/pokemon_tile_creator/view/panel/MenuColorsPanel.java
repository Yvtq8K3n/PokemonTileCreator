package xyz.yvtq8k3n.pokemon_tile_creator.view.panel;

import xyz.yvtq8k3n.pokemon_tile_creator.view.component.ColorScrollbar;

import javax.swing.*;
import java.awt.*;

public class MenuColorsPanel extends ActionPanel{

    ColorScrollbar colorScrollbar;

    public MenuColorsPanel() {
        setLayout(new BorderLayout());
        initComponents();
        addEventListeners();
    }

    private void initComponents() {
        colorScrollbar = new ColorScrollbar();

        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(colorScrollbar);
    }

    private void addEventListeners() {
    }
}
