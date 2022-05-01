package xyz.yvtq8k3n.pokemon_tile_creator.view;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;

import javax.swing.*;

import java.awt.*;


public class OperationsMenu extends JToolBar {
    private static final int[] OPTIONS_DIMENSIONS = {32, 32};

    public ButtonGroup btnGroupOperators;
    public JToggleButton btnSelectOperator;
    public JToggleButton btnAreaSelectOperator;
    public JToggleButton btnMultiSelectOperator;

    public ButtonGroup btnGroupInterfaces;
    public JToggleButton btnInterfaceA;
    public JToggleButton btnInterfaceB;
    public JToggleButton btnInterfaceC;


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
        btnSelectOperator.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnSelectOperator.setToolTipText("Activate/Disable selection mode");
        add(btnSelectOperator);

        btnAreaSelectOperator = new JToggleButton();
        btnAreaSelectOperator.setFocusPainted(false);
        btnAreaSelectOperator.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/area_select.svg", 24, 24));
        btnAreaSelectOperator.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnAreaSelectOperator.setToolTipText("Activate/Disable selection mode");
        add(btnAreaSelectOperator);

        btnMultiSelectOperator = new JToggleButton();
        btnMultiSelectOperator.setFocusPainted(false);
        btnMultiSelectOperator.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/multi_select.svg", 24, 24));
        btnMultiSelectOperator.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnMultiSelectOperator.setToolTipText("Activate/Disable selection mode");
        add(btnMultiSelectOperator);

        btnGroupOperators = new ButtonGroup();
        btnGroupOperators.add(btnSelectOperator);
        btnGroupOperators.add(btnAreaSelectOperator);
        btnGroupOperators.add(btnMultiSelectOperator);

        addSeparator(new Dimension(5,OPTIONS_DIMENSIONS[1]));

        btnInterfaceA = new JToggleButton();
        btnInterfaceA.setSelected(true);
        btnInterfaceA.setFocusPainted(false);
        btnInterfaceA.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/select.svg", 24, 24));
        btnInterfaceA.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnInterfaceA.setToolTipText("Activate/Disable selection mode");
        add(btnInterfaceA);

        btnInterfaceB = new JToggleButton();
        btnInterfaceB.setFocusPainted(false);
        btnInterfaceB.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/area_select.svg", 24, 24));
        btnInterfaceB.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnInterfaceB.setToolTipText("Activate/Disable selection mode");
        add(btnInterfaceB);

        btnInterfaceC = new JToggleButton();
        btnInterfaceC.setFocusPainted(false);
        btnInterfaceC.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/multi_select.svg", 24, 24));
        btnInterfaceC.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnInterfaceC.setToolTipText("Activate/Disable selection mode");
        add(btnInterfaceC);

        btnGroupInterfaces = new ButtonGroup();
        btnGroupInterfaces.add(btnInterfaceA);
        btnGroupInterfaces.add(btnInterfaceB);
        btnGroupInterfaces.add(btnInterfaceC);
    }

    private void addEventListeners() {
        btnSelectOperator.addActionListener(e -> MainController.setOperatorSelection());
        btnAreaSelectOperator.addActionListener(e -> MainController.setOperatorAreaSelection());
        btnMultiSelectOperator.addActionListener(e -> MainController.setOperatorMultiSelection());
    }
}
