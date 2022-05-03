package xyz.yvtq8k3n.pokemon_tile_creator.view.panel;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representation.ColorsRepresentation;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representation.PaletteRepresentation;

import javax.swing.*;
import java.awt.*;

public class ColorsPanel extends ActionPanel{
    private static final int[] FILLER_DIMENSIONS = {1, 5};
    private static final int[] OPTIONS_DIMENSIONS = {28, 28};

    public JButton btnColorGrid;
    public JButton btnColorSorter;
    public ColorsRepresentation pnlColorsRepresentation;
    public PaletteRepresentation pnlPaletteRepresentation;

    public ColorsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        initComponents();
        addEventListeners();
    }

    private void addEventListeners() {
        btnColorGrid.addActionListener(e -> pnlColorsRepresentation.changeGridIndex());
        btnColorSorter.addActionListener(e -> MainController.changeColorSortingOrder());
    }

    private void initComponents() {
        //Replace for icon later
        JPanel pnlMenu = new JPanel();

        JPanel pnlGrid = new JPanel();
        btnColorGrid = new JButton();
        btnColorGrid.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/grid.svg", 16, 16));
        btnColorGrid.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnColorGrid.setToolTipText("Toggle for color sorting");
        pnlGrid.setLayout(new CustomFlow(FlowLayout.LEFT));
        pnlGrid.add(btnColorGrid);

        JPanel pnlColorSorter = new JPanel();
        btnColorSorter = new JButton();
        btnColorSorter.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/color_sorting.svg", 16, 16));
        btnColorSorter.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnColorSorter.setToolTipText("Toggle for grid");
        pnlColorSorter.setLayout(new CustomFlow(FlowLayout.RIGHT));
        pnlColorSorter.add(btnColorSorter);

        pnlMenu.setLayout(new GridLayout(1,2));
        pnlMenu.add(pnlGrid);
        pnlMenu.add(pnlColorSorter);

        pnlColorsRepresentation = new ColorsRepresentation();
        pnlPaletteRepresentation = new PaletteRepresentation();

        //Add Components
        Dimension dimFiller = TileHelper.createDimension(FILLER_DIMENSIONS);
        add(pnlMenu);
        add(new Box.Filler(dimFiller, dimFiller, dimFiller));
        add(pnlColorsRepresentation);
        add(new Box.Filler(dimFiller, dimFiller, dimFiller));
        add(pnlPaletteRepresentation);
    }
}
