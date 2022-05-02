package xyz.yvtq8k3n.pokemon_tile_creator.view;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;
import xyz.yvtq8k3n.pokemon_tile_creator.view.panel.ImagePanel;
import xyz.yvtq8k3n.pokemon_tile_creator.view.panel.ColorsPanel;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representation.BlockRepresentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ApplicationView extends JFrame{
    private static final int[] OPTIONS_DIMENSIONS = {32, 32};
    private static final int[] MENU_DIMENSION = {128, 186};

    final static String BUTTONPANEL = "Card with JButtons";
    final static String TEXTPANEL = "Card with JTextField";

    //Top
    public JToolBar operationsMenu;
    public ButtonGroup btnGroupOperators;
    public JToggleButton btnSelectOperator;
    public JToggleButton btnAreaSelectOperator;
    public JToggleButton btnMultiSelectOperator;

    public ButtonGroup btnGroupInterfaces;
    public JToggleButton btnActionConvert;
    public JToggleButton btnActionPalette;
    public JToggleButton btnInterfaceC;

    //Center
    public JPanel pnlDisplayBodyContext;
    public ImagePanel imgDisplayOriginal;

    public JPanel actionPanel;
    public CardLayout actionCardLayout;
    public ImagePanel imgDisplayConverted;
    public ColorsPanel colorsPanelOriginal;

    //Right
    public JPanel pnlRightMenu;
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
        initMenuComponents();
        initBodyComponents();
        initRightComponents();

        //MAIN PANEL
        JPanel mainJPanel = new JPanel();
        mainJPanel.setLayout(new BorderLayout());
        mainJPanel.setBorder(new EmptyBorder(10, 5, 10, 5));
        mainJPanel.add(operationsMenu, BorderLayout.PAGE_START);
        mainJPanel.add(pnlDisplayBodyContext, BorderLayout.CENTER);
        mainJPanel.add(pnlRightMenu, BorderLayout.LINE_END);
        add(mainJPanel);
    }

    public void initMenuComponents(){
        //TOP
        operationsMenu = new JToolBar();
        initMenuOperatorsComponents();
        operationsMenu.addSeparator(new Dimension(5,OPTIONS_DIMENSIONS[1]));
        initMenuActionComponents();

        operationsMenu.setLayout(new FlowLayout(FlowLayout.LEADING));
        operationsMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        operationsMenu.setFloatable(false);
        operationsMenu.setRollover(false);
    }

    public void initMenuOperatorsComponents(){
        btnSelectOperator = new JToggleButton();
        btnSelectOperator.setSelected(true);
        btnSelectOperator.setFocusPainted(false);
        btnSelectOperator.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/select.svg", 24, 24));
        btnSelectOperator.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnSelectOperator.setToolTipText("Activate/Disable selection mode");
        operationsMenu.add(btnSelectOperator);

        btnAreaSelectOperator = new JToggleButton();
        btnAreaSelectOperator.setFocusPainted(false);
        btnAreaSelectOperator.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/area_select.svg", 24, 24));
        btnAreaSelectOperator.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnAreaSelectOperator.setToolTipText("Activate/Disable selection mode");
        operationsMenu.add(btnAreaSelectOperator);

        btnMultiSelectOperator = new JToggleButton();
        btnMultiSelectOperator.setFocusPainted(false);
        btnMultiSelectOperator.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/multi_select.svg", 24, 24));
        btnMultiSelectOperator.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnMultiSelectOperator.setToolTipText("Activate/Disable selection mode");
        operationsMenu.add(btnMultiSelectOperator);

        btnGroupOperators = new ButtonGroup();
        btnGroupOperators.add(btnSelectOperator);
        btnGroupOperators.add(btnAreaSelectOperator);
        btnGroupOperators.add(btnMultiSelectOperator);
    }

    public void initMenuActionComponents(){
        btnActionConvert = new JToggleButton();
        btnActionConvert.setSelected(true);
        btnActionConvert.setFocusPainted(false);
        btnActionConvert.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/extrude.svg", 24, 24));
        btnActionConvert.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnActionConvert.setToolTipText("Activate/Disable selection mode");
        operationsMenu.add(btnActionConvert);

        btnActionPalette = new JToggleButton();
        btnActionPalette.setFocusPainted(false);
        btnActionPalette.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/distribute.svg", 24, 24));
        btnActionPalette.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnActionPalette.setToolTipText("Activate/Disable selection mode");
        operationsMenu.add(btnActionPalette);

        btnInterfaceC = new JToggleButton();
        btnInterfaceC.setFocusPainted(false);
        btnInterfaceC.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/multi_select.svg", 24, 24));
        btnInterfaceC.setPreferredSize(TileHelper.createDimension(OPTIONS_DIMENSIONS));
        btnInterfaceC.setToolTipText("Activate/Disable selection mode");
        operationsMenu.add(btnInterfaceC);

        btnGroupInterfaces = new ButtonGroup();
        btnGroupInterfaces.add(btnActionConvert);
        btnGroupInterfaces.add(btnActionPalette);
        btnGroupInterfaces.add(btnInterfaceC);
    }

    public void initBodyComponents(){
        //CENTER
        pnlDisplayBodyContext = new JPanel();
        imgDisplayOriginal = new ImagePanel();
        imgDisplayOriginal.lblTitle.setText("Old:");


        actionPanel = new JPanel();
        actionCardLayout = new CardLayout();

        //ActionPanels
        imgDisplayConverted = new ImagePanel();
        imgDisplayConverted.lblTitle.setText("New:");
        colorsPanelOriginal = new ColorsPanel();

        actionPanel.setLayout(actionCardLayout);
        actionPanel.add(imgDisplayConverted, BUTTONPANEL);
        actionPanel.add(colorsPanelOriginal, TEXTPANEL);
        actionCardLayout.show(actionPanel, BUTTONPANEL);


        pnlDisplayBodyContext.setLayout(new FlowLayout());
        pnlDisplayBodyContext.add(imgDisplayOriginal);
        pnlDisplayBodyContext.add(actionPanel);
    }

    public void initRightComponents(){
        pnlRightMenu = new JPanel();

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
    }


    private void addEventListeners() {
        //Menu
        btnSelectOperator.addActionListener(e -> MainController.setOperatorSelection());
        btnAreaSelectOperator.addActionListener(e -> MainController.setOperatorAreaSelection());
        btnMultiSelectOperator.addActionListener(e -> MainController.setOperatorMultiSelection());
        btnActionConvert.addActionListener(e -> actionCardLayout.show(actionPanel, BUTTONPANEL));
        btnActionPalette.addActionListener(e -> actionCardLayout.show(actionPanel, TEXTPANEL));

        //Right Menu
        btnPalette.addActionListener(e -> MainController.loadPalette());
        btnImage.addActionListener(e -> MainController.loadImage());
        btnExport.addActionListener(e -> MainController.exportTileset());
    }
}
