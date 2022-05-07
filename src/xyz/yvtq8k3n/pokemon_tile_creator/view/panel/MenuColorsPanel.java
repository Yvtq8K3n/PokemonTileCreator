package xyz.yvtq8k3n.pokemon_tile_creator.view.panel;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.view.component.ColorScrollbar;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representation.ColorRepresentation;

import javax.swing.*;
import java.awt.*;

public class MenuColorsPanel extends ActionPanel{

    JPanel pnlColorPicker;
    ColorRepresentation colorRepresentation;
    JTextField txtColorRed;
    JTextField txtColorBlue;
    JTextField txtColorGreen;
    ColorScrollbar colorScrollbar;

    public MenuColorsPanel() {
        initComponents();
        addEventListeners();
    }

    private void initComponents() {
        //pnlColorPickerPanel

        pnlColorPicker = new JPanel();

        JPanel pnlColorDisplay = new JPanel();

        JPanel pnlColorRepresent = new JPanel();
        colorRepresentation = new ColorRepresentation();
        pnlColorRepresent.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlColorRepresent.add(colorRepresentation);
        JPanel pnlColorTxtFields = new JPanel();
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
        pnlColorTxtFields.setLayout(new BoxLayout(pnlColorTxtFields, BoxLayout.PAGE_AXIS));
        pnlColorTxtFields.add(txtColorRed);
        pnlColorTxtFields.add(txtColorBlue);
        pnlColorTxtFields.add(txtColorGreen);
        pnlColorDisplay.setLayout(new BorderLayout());
        pnlColorDisplay.add(pnlColorRepresent, BorderLayout.CENTER);
        pnlColorDisplay.add(pnlColorTxtFields, BorderLayout.LINE_END);

        colorScrollbar = new ColorScrollbar();

        //Add Components
        pnlColorPicker.setLayout(new FlowLayout());
        pnlColorPicker.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlColorPicker.add(pnlColorDisplay);
        pnlColorPicker.add(colorScrollbar);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(128, 200));
        add(pnlColorPicker);
        Dimension dimFiller = TileHelper.createDimension(new int[]{128, 200});
        add(new Box.Filler(dimFiller, dimFiller, dimFiller));
    }

    private void addEventListeners() {
    }
}
