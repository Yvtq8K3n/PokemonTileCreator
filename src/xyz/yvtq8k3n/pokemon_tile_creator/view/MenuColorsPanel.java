package xyz.yvtq8k3n.pokemon_tile_creator.view;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.view.components.ColorScrollbar;
import xyz.yvtq8k3n.pokemon_tile_creator.view.components.ColorTextField;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representations.ColorBlock;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MenuColorsPanel extends ActionPanel{

    //ColorsPanel
    public JPanel pnlColor;
    public JPanel pnlPalette;

    public JPanel pnlColorMenu; //ColorMenu
    public JButton btnSave;
    public JButton btnRevert;
    public JButton btnAddToList;

    public JPanel pnlColorPicker; //ColorPicker
    public ColorBlock colorBlock;
    public ColorTextField colorTextField;

    public JPanel pnlColorScrollbar; //ColorScrollBar
    public ColorScrollbar colorScrollbar;

    //Palette
    public JPanel pnlPaletteMenu; //MenuPalette
    public JButton btnViewPalette;
    public JButton btnClustering;

    public JPanel pnlPaletteList; //ListPalette
    public JList lstPalette;

    public JPanel pnlPaletteApply; //ApplyPalette
    public JLabel lblColors;
    public JTextField txtTotalColor;
    public JButton btnApplyPalette;


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
        pnlColor.setLayout(new BoxLayout(pnlColor, BoxLayout.PAGE_AXIS));
        pnlColor.add(pnlColorMenu);
        pnlColor.add(pnlColorPicker);
        pnlColor.add(pnlColorScrollbar);

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
        add(pnlColor, BorderLayout.PAGE_START);
        add(pnlPalette, BorderLayout.CENTER);
    }

    private void createColorPicker() {
        pnlColorPicker = new JPanel();

        JPanel pnlCenter = new JPanel();
        colorBlock = new ColorBlock();
        pnlCenter.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 8));
        pnlCenter.add(Box.createHorizontalGlue());
        pnlCenter.add(colorBlock);
        pnlCenter.add(Box.createHorizontalGlue());

        JPanel leftPanel = new JPanel();
        colorTextField = new ColorTextField();
        leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        leftPanel.add(colorTextField);

        pnlColorPicker.setLayout(new BorderLayout());
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
        lstPalette = new JList(new String[]{ "Monday","Tuesday","Wednesday","Monday","Tuesday","Wednesday",
                "Thursday"});

        pnlPaletteList.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlPaletteList.add(lstPalette);
    }

    private void createColorMenu() {
        pnlColorMenu = new JPanel();
        pnlColorMenu.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(5, 5, 0, 5),
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

    public void setColorBlockSlot1(Color color) {
        colorBlock.setColorSlot1(color);
        colorTextField.setSelectedColor(color);
        colorScrollbar.setSelectedColor(color);
        repaint();
    }

    public void setColorBlockSlot2(Color color) {
        colorBlock.setColorSlot2(color);
        colorTextField.setSelectedColor(color);
        colorScrollbar.setSelectedColor(color);
        repaint();
    }
}
