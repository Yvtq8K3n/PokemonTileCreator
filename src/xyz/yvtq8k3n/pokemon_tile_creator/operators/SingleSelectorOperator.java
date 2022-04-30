package xyz.yvtq8k3n.pokemon_tile_creator.operators;

import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.SelectableBehaviour;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class SingleSelectorOperator extends Operator {

    @Override
    public void mousePressed(MouseEvent e) {
        SelectableBehaviour s = (SelectableBehaviour)e.getSource();
        if (SwingUtilities.isLeftMouseButton(e)) s.startSelector(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        SelectableBehaviour s = (SelectableBehaviour)e.getSource();
        if (SwingUtilities.isLeftMouseButton(e)) s.moveSelector(e.getX(), e.getY());
    }
}
