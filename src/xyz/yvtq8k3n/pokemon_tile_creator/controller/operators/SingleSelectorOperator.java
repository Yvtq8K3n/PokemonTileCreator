package xyz.yvtq8k3n.pokemon_tile_creator.controller.operators;

import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.SingleSelectableBehaviour;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class SingleSelectorOperator extends Operator {

    @Override
    public void mousePressed(MouseEvent e) {
        SingleSelectableBehaviour s = (SingleSelectableBehaviour)e.getSource();
        if (SwingUtilities.isLeftMouseButton(e)) s.startSingleSelector(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        SingleSelectableBehaviour s = (SingleSelectableBehaviour)e.getSource();
        if (SwingUtilities.isLeftMouseButton(e)) s.moveSingleSelector(e.getX(), e.getY());
    }
}
