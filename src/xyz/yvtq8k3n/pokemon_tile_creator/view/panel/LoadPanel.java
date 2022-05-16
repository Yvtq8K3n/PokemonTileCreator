package xyz.yvtq8k3n.pokemon_tile_creator.view.panel;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.LoadController;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.PaletteController;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representations.ColorBlock;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representations.ImageBlock;

import javax.swing.*;
import java.awt.*;

public class LoadPanel extends ActionPanel{

    public JPanel pnlBlockDisplay;
    public ImageBlock imageBlock;

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

        imageBlock = new ImageBlock();
        pnlBlockDisplay.add(imageBlock);

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
        btnPalette.addActionListener(e -> {
            LoadController.loadPalette();
            LoadController.createGenerateTileset();
        });
        btnImage.addActionListener(e -> {
            LoadController.loadImageFromFile();
            LoadController.createGenerateTileset();
            PaletteController.loadSortedColors();
        });
        btnExport.addActionListener(e -> LoadController.exportTileset());
    }
}
