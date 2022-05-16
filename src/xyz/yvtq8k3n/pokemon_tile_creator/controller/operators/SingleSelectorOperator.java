package xyz.yvtq8k3n.pokemon_tile_creator.controller.operators;

import xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable.SelectableBehaviour;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class SingleSelectorOperator extends Operator {
    @Override
    public void mousePressed(MouseEvent e) {
        SelectableBehaviour s = (SelectableBehaviour)e.getSource();
        if (SwingUtilities.isLeftMouseButton(e)) s.startSelection(e.getX(), e.getY());
        if (SwingUtilities.isRightMouseButton(e)) s.removeSelection(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        SelectableBehaviour s = (SelectableBehaviour)e.getSource();
        if (SwingUtilities.isLeftMouseButton(e)) s.dragSelection(e.getX(), e.getY());
        if (SwingUtilities.isRightMouseButton(e)) s.removeSelection(e.getX(), e.getY());
    }
}
