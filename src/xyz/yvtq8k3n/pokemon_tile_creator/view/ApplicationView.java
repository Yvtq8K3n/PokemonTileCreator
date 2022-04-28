package xyz.yvtq8k3n.pokemon_tile_creator.view;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import lombok.experimental.Helper;
import xyz.yvtq8k3n.pokemon_tile_creator.HelperCreator;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

import static xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController.MAIN_CONTROLLER;

public class ApplicationView extends JFrame{
    private static final int[] MENU_DIMENSION = {128, 186};

    //Top
    public OperationsMenu operationsMenu;

    //Center
    public ImageDisplay imgDisplayOriginal;
    public ImageDisplay imgDisplayConverted;

    //Left
    public BlockDisplay blockDisplay;
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

    private void addEventListeners() {
        btnPalette.addActionListener(e -> MainController.loadPalette());
        btnImage.addActionListener(e -> MainController.loadImage());
        btnExport.addActionListener(e -> MainController.exportTileset());
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
        blockDisplay = new BlockDisplay();
        pnlBlockDisplay.add(blockDisplay);

        JPanel pnlButtons = new JPanel();
        btnPalette = new JButton("PALETTE");
        btnImage = new JButton("IMAGE");
        btnExport = new JButton("EXPORT");
        pnlButtons.setLayout(new GridLayout(3, 1, 15, 15));
        pnlButtons.setPreferredSize(HelperCreator.createDimension(MENU_DIMENSION));
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
}
