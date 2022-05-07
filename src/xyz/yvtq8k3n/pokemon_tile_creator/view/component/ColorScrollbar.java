package xyz.yvtq8k3n.pokemon_tile_creator.view.component;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class ColorScrollbar extends JPanel {
    private static final int[] PARENT = {112, 96};
    ScrollComponent scrollRed;
    ScrollComponent scrollGreen;
    ScrollComponent scrollBlue;

    public ColorScrollbar() {
        initComponents();
    }

    private void initComponents() {
        JPanel pnlGrid = new JPanel();
        scrollRed = new ScrollComponent("R: ", Color.RED);
        scrollGreen = new ScrollComponent("G: ", Color.GREEN);
        scrollBlue =  new ScrollComponent("B: ", Color.BLUE);

        pnlGrid.setLayout(new GridLayout(3, 1, 0,4));
        pnlGrid.add(scrollRed);
        pnlGrid.add(scrollGreen);
        pnlGrid.add(scrollBlue);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(TileHelper.createDimension(PARENT));
        add(pnlGrid);

    }

    public class ScrollComponent extends JComponent implements AdjustmentListener {
        private static final int[] LBL_SIZE = {25, 28};
        public static final int MAX_VALUE = 248;
        public static final int MIN_VALUE = 0;

        JLabel lblColorName;
        JScrollBar jScrollBar;

        public ScrollComponent(String colorName, Color foreground) {
            initComponents();
            lblColorName.setText(colorName);
            lblColorName.setForeground(foreground);
        }

        private void initComponents() {
            lblColorName = new JLabel();
            lblColorName.setFont(new Font("Arial", Font.BOLD, 16));
            lblColorName.setPreferredSize(TileHelper.createDimension(LBL_SIZE));
            lblColorName.setHorizontalAlignment(JLabel.RIGHT);
            lblColorName.setBorder(new EmptyBorder(0,-1,0,0));

            JPanel pnlScroll = new JPanel();
            jScrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
            jScrollBar.setMaximum(MAX_VALUE+10);//Due to extend is required to add 10px
            jScrollBar.addAdjustmentListener(this);
            pnlScroll.setLayout(new GridLayout(1, 1));
            pnlScroll.add(jScrollBar);

            setLayout(new BorderLayout());
            add(lblColorName, BorderLayout.LINE_START);
            add(pnlScroll, BorderLayout.CENTER);
        }

        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            int value = e.getValue() / 8 * 8;
            //textField.setText(String.valueOf(value));
        }

        public void setValue(int value) {
            //jScrollBar.setValue(value);
            //textField.setText(String.valueOf(value));
        }
    }
}
