package xyz.yvtq8k3n.pokemon_tile_creator.operators;

import xyz.yvtq8k3n.pokemon_tile_creator.view.CustomBehaviour;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import static xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController.MAIN_CONTROLLER;

public class SelectorOperator extends Operator {

    @Override
    public void mousePressed(MouseEvent e) {
        CustomBehaviour c = (CustomBehaviour)e.getSource();
        c.customAction(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        CustomBehaviour c = (CustomBehaviour)e.getSource();
        c.customAction(e.getX(), e.getY());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        MAIN_CONTROLLER.reset();
    }
}
