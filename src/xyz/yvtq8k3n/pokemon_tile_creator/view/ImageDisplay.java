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
    private static final int[] PALETTE_BOX = {25, 25};

    @Getter @Setter private JLabel lblTitle;
    @Getter @Setter private JButton btnSelect;
    @Getter @Setter private JButton btnGrid;
    @Getter @Setter private JPanel pnlImage;
    @Getter @Setter private JPanel pnlPalette;

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

        pnlImage = new JPanel();
        pnlImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlImage.setPreferredSize(HelperCreator.createDimension(IMG_DIMENSIONS));

        pnlPalette = new JPanel();
        pnlPalette.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlPalette.setPreferredSize(new Dimension(PALETTE_BOX[0] * 8,  PALETTE_BOX[1] * 2));

        //Add Components
        Dimension dimFiller = HelperCreator.createDimension(FILLER_DIMENSIONS);
        add(pnlMenu);
        add(new Box.Filler(dimFiller, dimFiller, dimFiller));
        add(pnlImage);
        add(new Box.Filler(dimFiller, dimFiller, dimFiller));
        add(pnlPalette);
    }

}
