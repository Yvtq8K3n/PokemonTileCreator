package xyz.yvtq8k3n.pokemon_tile_creator.operators;

import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.MultiSelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.SelectableBehaviour;

import java.awt.event.MouseEvent;

public class MultiSelectorOperator extends Operator{
    @Override
    public void mousePressed(MouseEvent e) {
        MultiSelectableBehaviour s = (MultiSelectableBehaviour)e.getSource();
        s.startMultiSelector(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MultiSelectableBehaviour s = (MultiSelectableBehaviour)e.getSource();
        s.resizeMultiSelector(e.getX(), e.getY());
    }
}
