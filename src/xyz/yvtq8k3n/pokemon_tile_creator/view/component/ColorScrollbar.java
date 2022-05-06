package xyz.yvtq8k3n.pokemon_tile_creator.view.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class ColorScrollbar extends JPanel {
    ScrollComponent scrollRed;
    ScrollComponent scrollGreen;
    ScrollComponent scrollBlue;

    public ColorScrollbar() {
        initComponents();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void initComponents() {
        scrollRed = new ScrollComponent(Color.RED);
        scrollGreen = new ScrollComponent(Color.GREEN);
        scrollBlue =  new ScrollComponent(Color.BLUE);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(scrollRed);
        add(scrollGreen);
        add(scrollBlue);
    }


    public class ScrollComponent extends JComponent implements AdjustmentListener {
        public static final int MAX_VALUE = 248;
        public static final int MIN_VALUE = 0;

        JScrollBar jScrollBar;
        JTextField textField;

        public ScrollComponent(Color foreground) {
            initComponents();
            setMaximumSize(new Dimension(200, 22));
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setVgap(0);
            setLayout(flowLayout);
            textField.setForeground(foreground);
            textField.setFont(new Font("Arial", Font.BOLD, 14));
            textField.setColumns(2);
        }

        public ScrollComponent(Color foreground, int value) {
            this(foreground);
            setValue(value);
        }


        private void initComponents() {
            textField = new JTextField(String.valueOf(MAX_VALUE));
            jScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
            jScrollBar.setPreferredSize(new Dimension(140, 20));
            jScrollBar.setMaximum(MAX_VALUE+10);//Due to extend is required to add 10px
            jScrollBar.addAdjustmentListener(this);
            add(jScrollBar);
            add(textField);
        }

        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            int value = e.getValue() / 8 * 8;
            textField.setText(String.valueOf(value));
        }

        public void setValue(int value) {
            jScrollBar.setValue(value);
            textField.setText(String.valueOf(value));
        }
    }
}
