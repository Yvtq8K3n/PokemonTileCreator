package xyz.yvtq8k3n.pokemon_tile_creator.operators;

import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.AreaSelectableBehaviour;

import java.awt.event.MouseEvent;

public class AreaSelectorOperator extends Operator{
    @Override
    public void mousePressed(MouseEvent e) {
        AreaSelectableBehaviour s = (AreaSelectableBehaviour)e.getSource();
        s.startAreaSelector(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        AreaSelectableBehaviour s = (AreaSelectableBehaviour)e.getSource();
        s.resizeAreaSelector(e.getX(), e.getY());
    }
}
