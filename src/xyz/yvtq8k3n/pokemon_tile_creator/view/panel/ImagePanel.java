package xyz.yvtq8k3n.pokemon_tile_creator.view.panel;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representation.PaletteRepresentation;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representation.TileRepresentation;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends ActionPanel{
    private static final int[] FILLER_DIMENSIONS = {1, 5};
    private static final int[] OPTIONS_DIMENSIONS = {28, 28};

    public JButton btnGrid;
    public TileRepresentation pnlTileRepresentation;
    public PaletteRepresentation pnlPaletteRepresentation;

    public ImagePanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        initComponents();
        addEventListeners();
    }

    private void addEventListeners() {
        btnGrid.addActionListener(e -> pnlTileRepresentation.changeGridIndex());
    }

    private void initComponents() {
        JPanel pnlMenu = new JPanel();

        //Replace for icon later
        JPanel pnlButtons = new JPanel();
        btnGrid = new JButton();
        btnGrid.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/grid.svg", 16, 16));
        btnGrid.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnGrid.setToolTipText("Toggle for grid");
        pnlButtons.setLayout(new CustomFlow(FlowLayout.LEFT));
        pnlButtons.add(btnGrid);


        pnlMenu.setLayout(new BoxLayout(pnlMenu, BoxLayout.LINE_AXIS));
        pnlMenu.add(pnlButtons);

        pnlTileRepresentation = new TileRepresentation();
        pnlTileRepresentation.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        pnlPaletteRepresentation = new PaletteRepresentation();
        pnlPaletteRepresentation.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //Add Components
        Dimension dimFiller = TileHelper.createDimension(FILLER_DIMENSIONS);
        add(pnlMenu);
        add(new Box.Filler(dimFiller, dimFiller, dimFiller));
        add(pnlTileRepresentation);
        add(new Box.Filler(dimFiller, dimFiller, dimFiller));
        add(pnlPaletteRepresentation);
    }
}
