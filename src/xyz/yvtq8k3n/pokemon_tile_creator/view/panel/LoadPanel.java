package xyz.yvtq8k3n.pokemon_tile_creator.view.panel;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representation.BlockRepresentation;

import javax.swing.*;
import java.awt.*;

public class LoadPanel extends ActionPanel{

    public JPanel pnlBlockDisplay;
    public BlockRepresentation blockRepresentation;

    public JPanel pnlButtons;
    public JButton btnPalette;
    public JButton btnImage;
    public JButton btnExport;

    public LoadPanel() {
        setLayout(new BorderLayout());
        initComponents();
        addEventListeners();
    }

    private void initComponents() {
        pnlBlockDisplay = new JPanel();

        blockRepresentation = new BlockRepresentation();
        pnlBlockDisplay.add(blockRepresentation);

        pnlButtons = new JPanel();
        pnlButtons.setPreferredSize(TileHelper.createDimension(MENU_DIMENSION));

        btnPalette = new JButton("PALETTE");
        btnImage = new JButton("IMAGE");
        btnExport = new JButton("EXPORT");

        pnlButtons.setLayout(new GridLayout(3, 1, 15, 15));
        pnlButtons.add(btnPalette);
        pnlButtons.add(btnImage);
        pnlButtons.add(btnExport);

        //Add Components
        add(pnlBlockDisplay,BorderLayout.PAGE_START);
        add(pnlButtons,BorderLayout.CENTER);
    }

    private void addEventListeners() {
        //Right Menu
        btnPalette.addActionListener(e -> MainController.loadPalette());
        btnImage.addActionListener(e -> MainController.loadImage());
        btnExport.addActionListener(e -> MainController.exportTileset());
    }
}
