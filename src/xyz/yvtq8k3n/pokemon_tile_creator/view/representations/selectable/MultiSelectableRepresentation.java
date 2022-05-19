package xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.OperatorController;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.AreaSelector;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.MultiSelector;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.Selector;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.SingleSelector;

public abstract class MultiSelectableRepresentation extends SelectableRepresentation {
    protected Selector[] selectors;

    public MultiSelectableRepresentation() {
        super();
        this.selectors = new Selector[]{
            new SingleSelector(),
            new AreaSelector(),
            new MultiSelector()
        };
        selectorInUse = selectors[0];
        OperatorController.addSelectableBehaviour(this);
    }

    //Changes selector everytime operator changes
    @Override
    public void setOperator(Operator current) {
        this.operatorInUse = current;
        int index = OperatorController.getOperatorIndex(current);
        setSelectorInUse(selectors[index]);
        repaint();
    }

    public void setSelectorInUse(Selector selector){
        this.selectorInUse.resetSelection();
        this.selectorInUse = selector;
    }
}
