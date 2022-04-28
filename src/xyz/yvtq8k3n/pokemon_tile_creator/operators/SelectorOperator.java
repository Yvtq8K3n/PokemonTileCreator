package xyz.yvtq8k3n.pokemon_tile_creator.operators;

import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.SelectableBehaviour;

import java.awt.event.MouseEvent;

public class SelectorOperator extends Operator {

    @Override
    public void mousePressed(MouseEvent e) {
        SelectableBehaviour s = (SelectableBehaviour)e.getSource();
        s.mousePressedSelectedAction(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        SelectableBehaviour s = (SelectableBehaviour)e.getSource();
        s.mouseDraggedSelectedAction(e.getX(), e.getY());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        SelectableBehaviour s = (SelectableBehaviour)e.getSource();
        s.mouseExitSelectedAction(e.getX(), e.getY());
    }
}
