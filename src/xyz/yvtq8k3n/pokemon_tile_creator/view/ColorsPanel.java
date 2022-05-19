package xyz.yvtq8k3n.pokemon_tile_creator.view;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.PaletteController;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable.multiple.ColorsRepresentation;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable.PaletteRepresentation;

import javax.swing.*;
import java.awt.*;

public class ColorsPanel extends ActionPanel{
    private static final int[] SORT_DISPLAY_FILLER = {1, 3};
    private static final int[] OPTIONS_DIMENSIONS = {28, 28};

    public JLabel lblCriteria;
    public JButton btnColorGrid;
    public JButton btnColorSorter;
    public ColorsRepresentation pnlColorsRepresentation;
    public PaletteRepresentation pnlPaletteRepresentation;

    public ColorsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        initComponents();
        addEventListeners();
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

        //SorterDisplay
        JPanel pnlSorterDisplay = new JPanel();

        JPanel pnlCriteria = new JPanel();
        lblCriteria = new JLabel();
        lblCriteria.setText("None");
        lblCriteria.setHorizontalAlignment(JLabel.CENTER);
        lblCriteria.setFont(new Font("SansSerif", Font.BOLD, 12));
        pnlCriteria.setLayout(new BorderLayout());
        pnlCriteria.add(lblCriteria, BorderLayout.CENTER);

        JPanel pnlColorSorter = new JPanel();
        btnColorSorter = new JButton();
        btnColorSorter.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/color_sorting.svg", 16, 16));
        btnColorSorter.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnColorSorter.setToolTipText("Toggle for grid");
        pnlColorSorter.setLayout(new CustomFlow(FlowLayout.RIGHT));
        pnlColorSorter.add(btnColorSorter);

        pnlSorterDisplay.setLayout(new BorderLayout());
        pnlSorterDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlSorterDisplay.add(pnlCriteria, BorderLayout.CENTER);
        pnlSorterDisplay.add(pnlColorSorter, BorderLayout.LINE_END);

        pnlMenu.setLayout(new BorderLayout());
        pnlMenu.add(pnlGrid, BorderLayout.LINE_START);
        pnlMenu.add(pnlSorterDisplay, BorderLayout.CENTER);

        pnlColorsRepresentation = new ColorsRepresentation();
        pnlPaletteRepresentation = new PaletteRepresentation();

        //Add Components
        Dimension sortDisplayFiller = TileHelper.createDimension(SORT_DISPLAY_FILLER);
        Dimension dimFiller = TileHelper.createDimension(FILLER_DIMENSIONS);
        add(pnlMenu);
        add(new Box.Filler(sortDisplayFiller, sortDisplayFiller, sortDisplayFiller));
        add(pnlColorsRepresentation);
        add(new Box.Filler(dimFiller, dimFiller, dimFiller));
        add(pnlPaletteRepresentation);
    }

    private void addEventListeners() {
        btnColorGrid.addActionListener(e -> pnlColorsRepresentation.changeGrid());
        btnColorSorter.addActionListener(e ->{
            PaletteController.changeSortingMethod();
            PaletteController.loadSortedColors();
        });
    }

    public void setSortedColors(String sortingMethod, Color[] sortedColors){
        lblCriteria.setText(sortingMethod);
        pnlColorsRepresentation.setSortedColors(sortedColors);
        repaint();
    }


    public void updateSelectedColors() {
        pnlColorsRepresentation.setSelectedColors(PaletteController.getSelectedColors());
    }
}
