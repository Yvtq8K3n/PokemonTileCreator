package xyz.yvtq8k3n.pokemon_tile_creator.view.components;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.PaletteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ColorTextField extends JPanel {
    private static int STEP = 8;
    private static int MAX_VALUE = 31;
    private static int MIN_VALUE = 0;
    JTextField txtColorRed;
    JTextField txtColorGreen;
    JTextField txtColorBlue;

    public ColorTextField() {
        initComponents();
        addEventListeners();
    }

    private void initComponents() {
        txtColorRed = new JTextField();
        txtColorRed.setForeground(Color.RED);
        txtColorRed.setText(String.valueOf(MAX_VALUE));
        txtColorRed.setColumns(2);

        txtColorGreen = new JTextField();
        txtColorGreen.setForeground(Color.GREEN);
        txtColorGreen.setText(String.valueOf(MAX_VALUE));
        txtColorGreen.setColumns(2);

        txtColorBlue = new JTextField();
        txtColorBlue.setForeground(Color.BLUE);
        txtColorBlue.setText(String.valueOf(MAX_VALUE));
        txtColorBlue.setColumns(2);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(txtColorRed);
        add(txtColorGreen);
        add(txtColorBlue);
    }

    private void addEventListeners() {
        txtColorRed.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                JTextField textField = (JTextField) e.getSource();
                int colorValue = Integer.valueOf(textField.getText());
                colorValue = boundsConstraint(colorValue);
                System.out.println(colorValue);
                PaletteController.setColorBlockSlot2(new Color(
                        colorValue * STEP,
                        Integer.valueOf(txtColorGreen.getText()) * STEP,
                        Integer.valueOf(txtColorBlue.getText()) * STEP
                ));
            }
        });
        txtColorGreen.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                JTextField textField = (JTextField) e.getSource();
                int colorValue = Integer.valueOf(textField.getText());
                colorValue = boundsConstraint(colorValue);
                PaletteController.setColorBlockSlot2(new Color(
                        Integer.valueOf(txtColorRed.getText()) * STEP,
                        colorValue * STEP,
                        Integer.valueOf(txtColorBlue.getText()) * STEP
                ));
            }
        });
        txtColorBlue.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                JTextField textField = (JTextField) e.getSource();
                int colorValue = Integer.valueOf(textField.getText());
                colorValue = boundsConstraint(colorValue);
                PaletteController.setColorBlockSlot2(new Color(
                        Integer.valueOf(txtColorRed.getText()) * STEP,
                        Integer.valueOf(txtColorGreen.getText()) * STEP,
                        colorValue * STEP
                ));
            }
        });
    }


    public void setSelectedColor(Color color) {
        txtColorRed.setText(String.valueOf(color.getRed() / STEP));
        txtColorGreen.setText(String.valueOf(color.getGreen() / STEP));
        txtColorBlue.setText(String.valueOf(color.getBlue() / STEP));
    }

    private int boundsConstraint(int value){
        value = Math.min(value, MAX_VALUE);
        value = Math.max(value, MIN_VALUE);
        return value;
    }
}
