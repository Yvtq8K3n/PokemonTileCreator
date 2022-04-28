package xyz.yvtq8k3n.pokemon_tile_creator.view;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import xyz.yvtq8k3n.pokemon_tile_creator.HelperCreator;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;

import javax.swing.*;

import java.awt.*;

import static xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController.MAIN_CONTROLLER;

public class OperationsMenu extends JToolBar {
    private static final int[] OPTIONS_DIMENSIONS = {32, 32};

    public JButton btnRefresh;
    public JToggleButton btnSelect;

    public OperationsMenu() {
        setLayout(new FlowLayout(FlowLayout.LEADING));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFloatable(false);
        setRollover(false);
        initComponents();
        addEventListeners();
    }

    private void initComponents() {
        btnSelect = new JToggleButton();
        btnSelect.setFocusPainted(false);
        btnSelect.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/select.svg", 24, 24));
        btnSelect.setPreferredSize(HelperCreator.createDimension(OPTIONS_DIMENSIONS));
        btnSelect.setToolTipText("Activate/Disable selection mode");
        add(btnSelect);
    }

    private void addEventListeners() {
        btnSelect.addActionListener(e -> MainController.changeState());
    }
}
