package xyz.yvtq8k3n.pokemon_tile_creator.view.components;

import xyz.yvtq8k3n.pokemon_tile_creator.TileHelper;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.PaletteController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class ColorScrollbar extends JPanel {
    private static final int[] PARENT = {112, 80};
    ScrollComponent scrollRed;
    ScrollComponent scrollGreen;
    ScrollComponent scrollBlue;

    public ColorScrollbar() {
        initComponents();
    }

    private void initComponents() {
        JPanel pnlGrid = new JPanel();
        scrollRed = new ScrollComponent(Color.RED);
        scrollGreen = new ScrollComponent( Color.GREEN);
        scrollBlue =  new ScrollComponent(Color.BLUE);

        pnlGrid.setLayout(new GridLayout(3, 1, 0,4));
        pnlGrid.add(scrollRed);
        pnlGrid.add(scrollGreen);
        pnlGrid.add(scrollBlue);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(TileHelper.createDimension(PARENT));
        add(pnlGrid);

    }

    public void setSelectedColor(Color color) {
        scrollRed.setValue(color.getRed());
        scrollGreen.setValue(color.getGreen());
        scrollBlue.setValue(color.getBlue());
    }

    public class ScrollComponent extends JComponent implements AdjustmentListener {
        private static final int[] LBL_SIZE = {25, 28};
        public static final int MAX_VALUE = 248;
        public static final int MIN_VALUE = 0;

        JScrollBar jScrollBar;
        JLabel lblColorName;

        public ScrollComponent(Color foreground) {
            initComponents();
            lblColorName.setText(String.valueOf(MAX_VALUE));
            lblColorName.setForeground(foreground);
        }

        private void initComponents() {
            lblColorName = new JLabel();
            lblColorName.setFont(new Font("SansSerif", Font.BOLD, 12));
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
            add(pnlScroll, BorderLayout.CENTER);
            add(lblColorName, BorderLayout.LINE_END);
        }

        public void setValue(int value) {
            value = value / 8 * 8;
            jScrollBar.setValue(value);
            lblColorName.setText(String.valueOf(value));
        }

        public int getValue() {
            return jScrollBar.getValue();
        }

        @Override
        public void adjustmentValueChanged(AdjustmentEvent e) {
            if (e.getValueIsAdjusting()){
                PaletteController.setColorBlockSlot2(new Color(
                        scrollRed.getValue(),
                        scrollGreen.getValue(),
                        scrollBlue.getValue()
                ));
            }
        }
    }
}
