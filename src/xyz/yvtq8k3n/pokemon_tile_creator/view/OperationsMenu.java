package xyz.yvtq8k3n.pokemon_tile_creator.view;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import xyz.yvtq8k3n.pokemon_tile_creator.HelperCreator;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;

import javax.swing.*;

import java.awt.*;


public class OperationsMenu extends JToolBar {
    private static final int[] OPTIONS_DIMENSIONS = {32, 32};

    public ButtonGroup btnGroupOperators;
    public JToggleButton btnSelectOperator;
    public JToggleButton btnAreaSelectOperator;


    public OperationsMenu() {
        setLayout(new FlowLayout(FlowLayout.LEADING));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFloatable(false);
        setRollover(false);
        initComponents();
        addEventListeners();
    }

    private void initComponents() {
        btnSelectOperator = new JToggleButton();
        btnSelectOperator.setSelected(true);
        btnSelectOperator.setFocusPainted(false);
        btnSelectOperator.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/select.svg", 24, 24));
        btnSelectOperator.setPreferredSize(HelperCreator.createDimension(OPTIONS_DIMENSIONS));
        btnSelectOperator.setToolTipText("Activate/Disable selection mode");
        add(btnSelectOperator);

        btnAreaSelectOperator = new JToggleButton();
        btnAreaSelectOperator.setFocusPainted(false);
        btnAreaSelectOperator.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/area_select.svg", 24, 24));
        btnAreaSelectOperator.setPreferredSize(HelperCreator.createDimension(OPTIONS_DIMENSIONS));
        btnAreaSelectOperator.setToolTipText("Activate/Disable selection mode");
        add(btnAreaSelectOperator);

        btnGroupOperators = new ButtonGroup();
        btnGroupOperators.add(btnSelectOperator);
        btnGroupOperators.add(btnAreaSelectOperator);
    }

    private void addEventListeners() {
        btnSelectOperator.addActionListener(e -> MainController.setOperatorSelection());
        btnAreaSelectOperator.addActionListener(e -> MainController.setOperatorAreaSelection());
    }
}
