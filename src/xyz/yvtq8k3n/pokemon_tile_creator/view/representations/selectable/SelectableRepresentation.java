package xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.OperatorController;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representations.Representation;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.Selector;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.SingleSelector;

import java.awt.*;
import java.awt.event.MouseEvent;

import static xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel.OPERATOR_SINGLE;

public abstract class SelectableRepresentation extends Representation implements SelectableBehaviour {
    protected Operator operatorInUse;
    protected Selector selectorInUse;

    public SelectableRepresentation() {
        super();
        this.selectorInUse = new SingleSelector();
        this.operatorInUse = OperatorController.getOperator(OPERATOR_SINGLE);
    }

    //Actions
    @Override
    public void startSelection(int x, int y){
        selectorInUse.startSelection(x, y);
        repaint();
    }

    @Override
    public void dragSelection(int x, int y){
        selectorInUse.dragSelection(x, y);
        repaint();
    }

    @Override
    public void removeSelection(int x, int y){
        selectorInUse.removeSelection(x, y);
        repaint();
    }

    @Override
    public void setOperator(Operator current) {
    }


    //Mouse Events
    @Override
    public void mousePressed(MouseEvent e) {
        operatorInUse.mousePressed(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        operatorInUse.mouseDragged(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        operatorInUse.mouseReleased(e);
    }
}
