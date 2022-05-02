package xyz.yvtq8k3n.pokemon_tile_creator.view.panel;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representation.PaletteRepresentation;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representation.TileRepresentation;

import javax.swing.*;
import java.awt.*;

public class ColorsPanel extends ActionPanel{
    private static final int[] FILLER_DIMENSIONS = {1, 5};
    private static final int[] OPTIONS_DIMENSIONS = {28, 28};

    public JButton btnA;
    public JButton btnB;
    public JButton btnC;
    public JButton btnD;
    public TileRepresentation pnlTileRepresentation;
    public PaletteRepresentation pnlPaletteRepresentation;

    public ColorsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        initComponents();
        addEventListeners();
    }

    private void addEventListeners() {
        //btnA.addActionListener(e -> pnlTileRepresentation.changeGridIndex());
        //btnB.addActionListener(e -> pnlTileRepresentation.changeGridIndex());
        //btnC.addActionListener(e -> pnlTileRepresentation.changeGridIndex());
        //btnD.addActionListener(e -> pnlTileRepresentation.changeGridIndex());
    }

    private void initComponents() {
        //Replace for icon later
        JPanel pnlButtons = new JPanel();

        btnA = new JButton();
        btnA.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/grid.svg", 16, 16));
        btnA.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnA.setToolTipText("Toggle for grid");

        btnB = new JButton();
        btnB.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/grid.svg", 16, 16));
        btnB.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnB.setToolTipText("Toggle for grid");

        btnC = new JButton();
        btnC.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/grid.svg", 16, 16));
        btnC.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnC.setToolTipText("Toggle for grid");

        btnD = new JButton();
        btnD.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/grid.svg", 16, 16));
        btnD.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnD.setToolTipText("Toggle for grid");

        pnlButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnlButtons.add(btnA);
        pnlButtons.add(btnB);
        pnlButtons.add(btnC);
        pnlButtons.add(btnD);


        pnlTileRepresentation = new TileRepresentation();
        pnlTileRepresentation.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        pnlPaletteRepresentation = new PaletteRepresentation();
        pnlPaletteRepresentation.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //Add Components
        Dimension dimFiller = TileHelper.createDimension(FILLER_DIMENSIONS);
        add(pnlButtons);
        add(new Box.Filler(dimFiller, dimFiller, dimFiller));
        add(pnlTileRepresentation);
        add(new Box.Filler(dimFiller, dimFiller, dimFiller));
        add(pnlPaletteRepresentation);
    }
}
