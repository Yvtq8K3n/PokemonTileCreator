package xyz.yvtq8k3n.pokemon_tile_creator.view;

import lombok.Getter;
import lombok.Setter;
import xyz.yvtq8k3n.pokemon_tile_creator.HelperCreator;

import javax.swing.*;
import java.awt.*;

public class ImageDisplay extends JPanel{
    private static final int[] FILLER_DIMENSIONS = {1, 5};
    private static final int[] OPTIONS_DIMENSIONS = {25, 25};
    private static final int[] IMG_DIMENSIONS = {128, 320};
    private static final int[] PALETTE_BOX = {128, 32};

    public JLabel lblTitle;
    public JButton btnSelect;
    public JButton btnGrid;
    public PanelTileRepresentation pnlTileRepresentation;
    public PanelPaletteRepresentation pnlPaletteRepresentation;

    public ImageDisplay() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        initComponents();
    }

    private void initComponents() {
        JPanel pnlMenu = new JPanel();

        JPanel pnlLabel = new JPanel();
        lblTitle = new JLabel();
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 16));

        pnlLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlLabel.add(lblTitle);

        //Replace for icon later
        JPanel pnlButtons = new JPanel();
        btnSelect = new JButton("S");
        btnSelect.setFont(new Font("SansSerif", Font.BOLD, 12)); //remove me after icons
        btnSelect.setMargin(new Insets(0, -1, 0, 0)); //remove me after icons
        btnSelect.setPreferredSize(HelperCreator.createDimension(OPTIONS_DIMENSIONS));
        btnSelect.setToolTipText("Select to show on rectangle red");
        btnGrid = new JButton("G");
        btnGrid.setFont(new Font("SansSerif", Font.BOLD, 12));//remove me after icons
        btnGrid.setMargin(new Insets(0, -1, 0, 0)); //remove me after icons
        btnGrid.setPreferredSize(HelperCreator.createDimension(OPTIONS_DIMENSIONS));
        btnGrid.setToolTipText("Toggle for grid");

        pnlButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnlButtons.add(btnSelect);
        pnlButtons.add(btnGrid);

        pnlMenu.setLayout(new BoxLayout(pnlMenu, BoxLayout.LINE_AXIS));
        pnlMenu.add(pnlLabel);
        pnlMenu.add(pnlButtons);

        pnlTileRepresentation = new PanelTileRepresentation();
        pnlTileRepresentation.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlTileRepresentation.setPreferredSize(HelperCreator.createDimension(IMG_DIMENSIONS));

        pnlPaletteRepresentation = new PanelPaletteRepresentation();
        pnlPaletteRepresentation.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlPaletteRepresentation.setPreferredSize(HelperCreator.createDimension(PALETTE_BOX));

        //Add Components
        Dimension dimFiller = HelperCreator.createDimension(FILLER_DIMENSIONS);
        add(pnlMenu);
        add(new Box.Filler(dimFiller, dimFiller, dimFiller));
        add(pnlTileRepresentation);
        add(new Box.Filler(dimFiller, dimFiller, dimFiller));
        add(pnlPaletteRepresentation);
    }

}
