package xyz.yvtq8k3n.pokemon_tile_creator.view;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.OperatorController;
import xyz.yvtq8k3n.pokemon_tile_creator.view.panel.TilesetPanel;
import xyz.yvtq8k3n.pokemon_tile_creator.view.panel.ColorsPanel;
import xyz.yvtq8k3n.pokemon_tile_creator.view.panel.LoadPanel;
import xyz.yvtq8k3n.pokemon_tile_creator.view.panel.MenuColorsPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel.*;

public class ApplicationView extends JFrame{
    private static final int[] OPTIONS_DIMENSIONS = {32, 32};

    final static String CONVERT_PANEL = "Card for ActionConvert";
    final static String COLORS_PANEL = "Card for ActionColors";

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
    public TilesetPanel originalTilesetPanel;

    public JPanel actionPanel;
    public CardLayout actionCardLayout;
    public TilesetPanel generatedTilesetPanel;
    public ColorsPanel colorsPanelOriginal;

    //Right
    public JPanel pnlRightMenu;
    public CardLayout menuCardLayout;
    public LoadPanel loadPanel;
    public MenuColorsPanel menuColorsPanel;

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
        originalTilesetPanel = new TilesetPanel();

        //ActionPanel
        actionPanel = new JPanel();
        actionCardLayout = new CardLayout();
        generatedTilesetPanel = new TilesetPanel();
        colorsPanelOriginal = new ColorsPanel();

        actionPanel.setLayout(actionCardLayout);
        actionPanel.add(generatedTilesetPanel, CONVERT_PANEL);
        actionPanel.add(colorsPanelOriginal, COLORS_PANEL);
        actionCardLayout.show(actionPanel, CONVERT_PANEL);

        pnlDisplayBodyContext.setLayout(new FlowLayout());
        pnlDisplayBodyContext.add(originalTilesetPanel);
        pnlDisplayBodyContext.add(actionPanel);
    }

    public void initRightComponents(){
        pnlRightMenu = new JPanel();
        menuCardLayout = new CardLayout();

        //Create Right Menu Panels
        loadPanel = new LoadPanel();
        menuColorsPanel = new MenuColorsPanel();

        pnlRightMenu.setLayout(menuCardLayout);
        pnlRightMenu.add(loadPanel, CONVERT_PANEL);
        pnlRightMenu.add(menuColorsPanel, COLORS_PANEL);
        menuCardLayout.show(pnlRightMenu, CONVERT_PANEL);
    }


    private void addEventListeners() {
        //Menu
        btnSelectOperator.addActionListener(e -> OperatorController.setSelectionOperator(OPERATOR_SINGLE));
        btnAreaSelectOperator.addActionListener(e -> OperatorController.setSelectionOperator(OPERATOR_AREA));
        btnMultiSelectOperator.addActionListener(e -> OperatorController.setSelectionOperator(OPERATOR_MULTI));
        btnActionConvert.addActionListener(e -> {
            actionCardLayout.show(actionPanel, CONVERT_PANEL);
            menuCardLayout.show(pnlRightMenu, CONVERT_PANEL);
        });
        btnActionPalette.addActionListener(e -> {
            actionCardLayout.show(actionPanel, COLORS_PANEL);
            menuCardLayout.show(pnlRightMenu, COLORS_PANEL);
        });
    }


}
