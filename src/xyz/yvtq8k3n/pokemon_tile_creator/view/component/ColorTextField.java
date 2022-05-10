package xyz.yvtq8k3n.pokemon_tile_creator.view.component;

import lombok.experimental.Helper;
import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ColorTextField extends JPanel {
    private static final int[] COLOR_FIELD = {40, 92};
    JTextField txtColorRed;
    JTextField txtColorGreen;
    JTextField txtColorBlue;

    public ColorTextField() {
        initComponents();
        setPreferredSize(TileHelper.createDimension(COLOR_FIELD));
        addEventListeners();
    }

    private void addEventListeners() {
        txtColorRed.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                textField.setText(text.toUpperCase());
            }
        });
        txtColorGreen.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                textField.setText(text.toUpperCase());
            }
        });
        txtColorBlue.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                textField.setText(text.toUpperCase());
            }
        });
    }

    private void initComponents() {
        txtColorRed = new JTextField();
        txtColorRed.setForeground(Color.RED);
        txtColorRed.setText("248");
        txtColorRed.setColumns(2);

        txtColorGreen = new JTextField();
        txtColorGreen.setForeground(Color.GREEN);
        txtColorGreen.setText("248");
        txtColorGreen.setColumns(2);

        txtColorBlue = new JTextField();
        txtColorBlue.setForeground(Color.BLUE);
        txtColorBlue.setText("248");
        txtColorBlue.setColumns(2);

        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(txtColorRed);
        add(txtColorGreen);
        add(txtColorBlue);
    }

    public void setSelectedColor(Color color) {
        txtColorRed.setText(String.valueOf(color.getRed()));
        txtColorGreen.setText(String.valueOf(color.getGreen()));
        txtColorBlue.setText(String.valueOf(color.getBlue()));
    }

    public void setChangingColor(Color color) {
        txtColorRed.setText(String.valueOf(color.getRed()));
        txtColorGreen.setText(String.valueOf(color.getGreen()));
        txtColorBlue.setText(String.valueOf(color.getBlue()));
    }
}
