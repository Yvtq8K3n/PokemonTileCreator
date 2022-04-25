package xyz.yvtq8k3n.pokemon_tile_creator.view;

import lombok.experimental.Helper;
import xyz.yvtq8k3n.pokemon_tile_creator.HelperCreator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class ApplicationView extends JFrame{
    private static final int[] MENU_DIMENSION = {128, 128};
    private static final int[] DISPLAY_DIMENSION = {128, 10};

    //Center
    ImageDisplay imgDisplayOriginal;
    ImageDisplay imgDisplayConverted;

    //Left
    BlockDisplay blockDisplay;
    JButton btnPalette;
    JButton btnImage;
    JButton btnExport;

    public ApplicationView() {
        initComponents();
        try {
            //Attempting to get System Look and Feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException | UnsupportedLookAndFeelException e ) {
            e.printStackTrace();
        }
        setTitle("Pokemon Tile Creator");
        setResizable(false);
        setVisible(true);
        pack();
    }
    public void initComponents(){
        //CENTER
        JPanel pnlDisplayImages = new JPanel();

        imgDisplayOriginal = new ImageDisplay();
        imgDisplayOriginal.getLblTitle().setText("Original:");
        imgDisplayConverted = new ImageDisplay();
        imgDisplayConverted.getLblTitle().setText("Converted:");

        pnlDisplayImages.setLayout(new FlowLayout());
        pnlDisplayImages.add(imgDisplayOriginal);
        pnlDisplayImages.add(imgDisplayConverted);

        //LEFT MENU
        JPanel pnlRightMenu = new JPanel();

        JPanel pnlBlockDisplay = new JPanel();
        blockDisplay = new BlockDisplay();
        pnlBlockDisplay.setPreferredSize(HelperCreator.createDimension(DISPLAY_DIMENSION));
        pnlBlockDisplay.add(blockDisplay);

        JPanel pnlButtons = new JPanel();
        btnPalette = new JButton("PALETTE");
        btnImage = new JButton("IMAGE");
        btnExport = new JButton("EXPORT");
        pnlButtons.setLayout(new GridLayout(3, 1, 15, 15));
        pnlButtons.setPreferredSize(HelperCreator.createDimension(MENU_DIMENSION));
        //pnlButtons.setBorder(BorderFactory.createLineBorder(Color.RED));
        pnlButtons.add(btnPalette);
        pnlButtons.add(btnImage);
        pnlButtons.add(btnExport);

        pnlRightMenu.setLayout(new BoxLayout(pnlRightMenu, BoxLayout.PAGE_AXIS));
        //pnlRightMenu.setBorder(BorderFactory.createLineBorder(Color.RED));
        pnlRightMenu.add(pnlBlockDisplay);
        pnlRightMenu.add(pnlButtons);
        pnlRightMenu.add(Box.createVerticalGlue());

        //MAIN PANEL
        JPanel mainJPanel = new JPanel();
        mainJPanel.setLayout(new BorderLayout());
        mainJPanel.setBorder(new EmptyBorder(10, 5, 10, 5));
        mainJPanel.add(pnlDisplayImages, BorderLayout.CENTER);
        mainJPanel.add(pnlRightMenu, BorderLayout.LINE_END);
        add(mainJPanel);
    }
}
