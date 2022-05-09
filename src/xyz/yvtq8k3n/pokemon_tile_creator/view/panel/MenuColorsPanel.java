package xyz.yvtq8k3n.pokemon_tile_creator.view.panel;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.view.component.ColorScrollbar;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representation.ColorRepresentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MenuColorsPanel extends ActionPanel{

    //ColorsPanel
    JPanel pnlColor;
    JPanel pnlPalette;

    JPanel pnlColorMenu; //ColorMenu
    JButton btnSave;
    JButton btnRevert;
    JButton btnAddToList;

    JPanel pnlColorPicker; //ColorPicker
    ColorRepresentation colorRepresentation;
    JTextField txtColorRed;
    JTextField txtColorBlue;
    JTextField txtColorGreen;

    JPanel pnlColorScrollbar; //ColorScrollBar
    ColorScrollbar colorScrollbar;

    //Palette
    JPanel pnlPaletteMenu; //MenuPalette
    JButton btnViewPalette;
    JButton btnClustering;

    JPanel pnlPaletteList; //ListPalette
    JList lstPalette;

    JPanel pnlPaletteApply; //ApplyPalette
    JLabel lblColors;
    JTextField txtTotalColor;
    JButton btnApplyPalette;


    public MenuColorsPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(128, 200));
        initComponents();
        addEventListeners();
    }

    private void initComponents() {
        //Create Color components
        pnlColor = new JPanel();
        pnlColor.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(0, 0, 1, 0),
                new LineBorder(Color.BLACK))
        );
        createColorMenu();
        createColorPicker();
        createColorScrollbar();
        pnlColor.setLayout(new BorderLayout());
        pnlColor.add(pnlColorMenu, BorderLayout.PAGE_START);
        pnlColor.add(pnlColorPicker, BorderLayout.CENTER);
        pnlColor.add(pnlColorScrollbar, BorderLayout.PAGE_END);

        //Create Palette components
        pnlPalette = new JPanel();
        pnlPalette.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(1, 0, 0, 0),
                new LineBorder(Color.BLACK))
        );
        createPaletteMenu();
        createPaletteList();
        createPaletteApply();
        pnlPalette.setLayout(new BorderLayout());
        pnlPalette.add(pnlPaletteMenu, BorderLayout.PAGE_START);
        pnlPalette.add(pnlPaletteList, BorderLayout.CENTER);
        pnlPalette.add(pnlPaletteApply, BorderLayout.PAGE_END);

        //Add Components
        add(pnlColor, BorderLayout.CENTER);
        add(pnlPalette, BorderLayout.PAGE_END);
    }

    private void createColorPicker() {
        pnlColorPicker = new JPanel();

        JPanel pnlCenter = new JPanel();
        colorRepresentation = new ColorRepresentation();
        pnlCenter.setLayout(new FlowLayout(FlowLayout.CENTER));
        Dimension dimFillerA = TileHelper.createDimension(new int[]{10,2});
        pnlCenter.add(new Box.Filler(dimFillerA, dimFillerA, dimFillerA));
        pnlCenter.add(colorRepresentation);

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(50, 20));
        txtColorRed = new JTextField();
        txtColorRed.setForeground(Color.RED);
        txtColorRed.setText("248");
        txtColorRed.setColumns(2);
        txtColorBlue = new JTextField();
        txtColorBlue.setForeground(Color.BLUE);
        txtColorBlue.setText("248");
        txtColorBlue.setColumns(2);
        txtColorGreen = new JTextField();
        txtColorGreen.setForeground(Color.GREEN);
        txtColorGreen.setText("248");
        txtColorGreen.setColumns(2);
        leftPanel.setLayout(new FlowLayout());
        leftPanel.add(txtColorRed);
        leftPanel.add(txtColorBlue);
        leftPanel.add(txtColorGreen);

        pnlColorPicker.setLayout(new BorderLayout(0, 0));
        Dimension dimFiller = TileHelper.createDimension(FILLER_DIMENSIONS);
        add(new Box.Filler(dimFiller, dimFiller, dimFiller), BorderLayout.PAGE_START);
        pnlColorPicker.add(pnlCenter, BorderLayout.CENTER);
        pnlColorPicker.add(leftPanel, BorderLayout.LINE_END);
    }

    private void createColorScrollbar(){
        pnlColorScrollbar = new JPanel();

        colorScrollbar = new ColorScrollbar();

        pnlColorScrollbar.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlColorScrollbar.add(colorScrollbar);
    }

    private void createPaletteApply() {
        pnlPaletteApply = new JPanel();
        pnlPaletteApply.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(5, 5, 5, 5),
                new LineBorder(Color.BLACK))
        );

        JPanel pnlLeftMenu = new JPanel();
        lblColors = new JLabel();
        lblColors.setText("Total:");
        txtTotalColor = new JTextField();
        txtTotalColor.setColumns(2);
        txtTotalColor.setText("16");
        txtTotalColor.setEnabled(false);
        pnlLeftMenu.setLayout(new CustomFlow(FlowLayout.LEFT));
        pnlLeftMenu.add(lblColors);
        pnlLeftMenu.add(txtTotalColor);

        JPanel pnlRightMenu = new JPanel();
        btnApplyPalette = new JButton();
        btnApplyPalette.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/grid.svg", 16, 16));
        btnApplyPalette.setToolTipText("Toggle for color sorting");
        pnlRightMenu.setLayout(new CustomFlow(FlowLayout.RIGHT));
        pnlRightMenu.add(btnApplyPalette);

        //Add Components
        pnlPaletteApply.setLayout(new BorderLayout());
        pnlPaletteApply.add(pnlLeftMenu, BorderLayout.LINE_START);
        pnlPaletteApply.add(pnlRightMenu, BorderLayout.LINE_END);
    }

    private void createPaletteList() {
        pnlPaletteList = new JPanel();
        lstPalette = new JList(new String[]{ "Monday","Tuesday","Wednesday",
                "Thursday"});

        pnlPaletteList.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlPaletteList.add(lstPalette);
    }

    private void createColorMenu() {
        pnlColorMenu = new JPanel();
        pnlColorMenu.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(5, 5, 5, 5),
                new LineBorder(Color.BLACK))
        );

        JPanel pnlLeftMenu = new JPanel();
        btnSave = new JButton();
        btnSave.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/grid.svg", 16, 16));
        btnSave.setToolTipText("Toggle for color sorting");
        btnRevert = new JButton();
        btnRevert.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/grid.svg", 16, 16));
        btnRevert.setToolTipText("Toggle for color sorting");
        pnlLeftMenu.setLayout(new CustomFlow(FlowLayout.LEFT));
        pnlLeftMenu.add(btnSave);
        pnlLeftMenu.add(btnRevert);

        JPanel pnlRightMenu = new JPanel();
        btnAddToList = new JButton();
        btnAddToList.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/grid.svg", 16, 16));
        btnAddToList.setToolTipText("Toggle for color sorting");
        pnlRightMenu.setLayout(new CustomFlow(FlowLayout.RIGHT));
        pnlRightMenu.add(btnAddToList);

        //Add Components
        pnlColorMenu.setLayout(new BorderLayout());
        pnlColorMenu.add(pnlLeftMenu, BorderLayout.LINE_START);
        pnlColorMenu.add(pnlRightMenu, BorderLayout.LINE_END);
    }

    private void createPaletteMenu() {
        pnlPaletteMenu = new JPanel();
        pnlPaletteMenu.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(5, 5, 5, 5),
                new LineBorder(Color.BLACK))
        );

        JPanel pnlLeftMenu = new JPanel();
        btnViewPalette = new JButton();
        btnViewPalette.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/grid.svg", 16, 16));
        btnViewPalette.setToolTipText("Toggle for color sorting");
        pnlLeftMenu.setLayout(new CustomFlow(FlowLayout.LEFT));
        pnlLeftMenu.add(btnViewPalette);

        JPanel pnlRightMenu = new JPanel();
        btnClustering = new JButton();
        btnClustering.setIcon(new FlatSVGIcon("xyz/yvtq8k3n/pokemon_tile_creator/resources/grid.svg", 16, 16));
        btnClustering.setToolTipText("Toggle for color sorting");
        pnlRightMenu.setLayout(new CustomFlow(FlowLayout.RIGHT));
        pnlRightMenu.add(btnClustering);

        //Add Components
        pnlPaletteMenu.setLayout(new GridLayout(1, 2));
        pnlPaletteMenu.add(pnlLeftMenu);
        pnlPaletteMenu.add(pnlRightMenu);
    }

    private void addEventListeners() {
    }
}
