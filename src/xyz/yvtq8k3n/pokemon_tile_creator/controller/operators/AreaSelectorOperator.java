package xyz.yvtq8k3n.pokemon_tile_creator.controller.operators;

import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.AreaSelectableBehaviour;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class AreaSelectorOperator extends Operator{
    @Override
    public void mousePressed(MouseEvent e) {
        AreaSelectableBehaviour s = (AreaSelectableBehaviour)e.getSource();
        if (SwingUtilities.isLeftMouseButton(e)) s.startAreaSelector(e.getX(), e.getY());
        if (SwingUtilities.isRightMouseButton(e)) s.reset();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        AreaSelectableBehaviour s = (AreaSelectableBehaviour)e.getSource();
        if (SwingUtilities.isLeftMouseButton(e)) s.resizeAreaSelector(e.getX(), e.getY());
        if (SwingUtilities.isRightMouseButton(e)) s.reset();
    }
}
