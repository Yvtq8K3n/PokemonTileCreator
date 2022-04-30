package xyz.yvtq8k3n.pokemon_tile_creator.view;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;
import xyz.yvtq8k3n.pokemon_tile_creator.view.panel.BlockRepresentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ApplicationView extends JFrame{
    private static final int[] MENU_DIMENSION = {128, 186};

    //Top
    public OperationsMenu operationsMenu;

    //Center
    public ImageDisplay imgDisplayOriginal;
    public ImageDisplay imgDisplayConverted;

    //Left
    public BlockRepresentation blockRepresentation;
    public JButton btnPalette;
    public JButton btnImage;
    public JButton btnExport;

    public ApplicationView() {
        initComponents();
        addEventListeners();

        setTitle("Pokemon Tile Creator");
        setResizable(false);
        setAlwaysOnTop(true);
        setVisible(true);
        pack();
    }

    public void initComponents(){
        //TOP
        operationsMenu = new OperationsMenu();

        //CENTER
        JPanel pnlDisplayImages = new JPanel();

        imgDisplayOriginal = new ImageDisplay();
        imgDisplayOriginal.lblTitle.setText("Old:");
        imgDisplayConverted = new ImageDisplay();
        imgDisplayConverted.lblTitle.setText("New:");

        pnlDisplayImages.setLayout(new FlowLayout());
        pnlDisplayImages.add(imgDisplayOriginal);
        pnlDisplayImages.add(imgDisplayConverted);

        //LEFT MENU
        JPanel pnlRightMenu = new JPanel();

        JPanel pnlBlockDisplay = new JPanel();
        blockRepresentation = new BlockRepresentation();
        pnlBlockDisplay.add(blockRepresentation);

        JPanel pnlButtons = new JPanel();
        btnPalette = new JButton("PALETTE");
        btnImage = new JButton("IMAGE");
        btnExport = new JButton("EXPORT");
        pnlButtons.setLayout(new GridLayout(3, 1, 15, 15));
        pnlButtons.setPreferredSize(TileHelper.createDimension(MENU_DIMENSION));
        pnlButtons.add(btnPalette);
        pnlButtons.add(btnImage);
        pnlButtons.add(btnExport);

        pnlRightMenu.setLayout(new BorderLayout());
        pnlRightMenu.add(pnlBlockDisplay,BorderLayout.PAGE_START);
        pnlRightMenu.add(pnlButtons,BorderLayout.CENTER);


        //MAIN PANEL
        JPanel mainJPanel = new JPanel();
        mainJPanel.setLayout(new BorderLayout());
        mainJPanel.setBorder(new EmptyBorder(10, 5, 10, 5));
        mainJPanel.add(operationsMenu, BorderLayout.PAGE_START);
        mainJPanel.add(pnlDisplayImages, BorderLayout.CENTER);
        mainJPanel.add(pnlRightMenu, BorderLayout.LINE_END);
        add(mainJPanel);
    }

    private void addEventListeners() {
        btnPalette.addActionListener(e -> MainController.loadPalette());
        btnImage.addActionListener(e -> MainController.loadImage());
        btnExport.addActionListener(e -> MainController.exportTileset());
    }
}
