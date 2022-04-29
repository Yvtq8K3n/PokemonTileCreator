package xyz.yvtq8k3n.pokemon_tile_creator.view;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import xyz.yvtq8k3n.pokemon_tile_creator.HelperCreator;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OperationsMenu extends JToolBar {
    private static final int[] OPTIONS_DIMENSIONS = {32, 32};

    public ButtonGroup btnGroupOperators;
    public JToggleButton btnSelectOperator;
    public JToggleButton btnMultiSelectOperator;


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
        btnSelectOperator.setActionCommand("SingleOperator");
        btnSelectOperator.setSelected(true);
        btnSelectOperator.setFocusPainted(false);
        btnSelectOperator.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/select.svg", 24, 24));
        btnSelectOperator.setPreferredSize(HelperCreator.createDimension(OPTIONS_DIMENSIONS));
        btnSelectOperator.setToolTipText("Activate/Disable selection mode");
        add(btnSelectOperator);

        btnMultiSelectOperator = new JToggleButton();
        btnMultiSelectOperator.setActionCommand("MultiOperator");
        btnMultiSelectOperator.setFocusPainted(false);
        btnMultiSelectOperator.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/multi_select.svg", 24, 24));
        btnMultiSelectOperator.setPreferredSize(HelperCreator.createDimension(OPTIONS_DIMENSIONS));
        btnMultiSelectOperator.setToolTipText("Activate/Disable selection mode");
        add(btnMultiSelectOperator);

        btnGroupOperators = new ButtonGroup();
        btnGroupOperators.add(btnSelectOperator);
        btnGroupOperators.add(btnMultiSelectOperator);
    }

    private void addEventListeners() {
        btnSelectOperator.addActionListener(e -> MainController.setOperatorSelection());
        btnMultiSelectOperator.addActionListener(e -> MainController.setOperatorMultiSelection());
    }
}
