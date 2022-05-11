package xyz.yvtq8k3n.pokemon_tile_creator.model.operators;

import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.MultiSelectableBehaviour;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class MultiSelectorOperator extends Operator{
    @Override
    public void mousePressed(MouseEvent e) {
        MultiSelectableBehaviour s = (MultiSelectableBehaviour)e.getSource();
        if (SwingUtilities.isLeftMouseButton(e)) s.addMultiSelectorPoint(e.getX(), e.getY());
        if (SwingUtilities.isRightMouseButton(e)) s.removeMultiSelectorPoint(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MultiSelectableBehaviour s = (MultiSelectableBehaviour)e.getSource();
        if (SwingUtilities.isLeftMouseButton(e)) s.addMultiSelectorPoint(e.getX(), e.getY());
        if (SwingUtilities.isRightMouseButton(e)) s.removeMultiSelectorPoint(e.getX(), e.getY());
    }
}
