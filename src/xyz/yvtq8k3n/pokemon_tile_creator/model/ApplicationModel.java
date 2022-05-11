package xyz.yvtq8k3n.pokemon_tile_creator.model;

import lombok.Data;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.AreaSelectorOperator;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.MultiSelectorOperator;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.SingleSelectorOperator;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.SelectableBehaviour;

@Data
public class ApplicationModel {
    public final static int OPERATOR_SINGLE = 0;
    public final static int OPERATOR_AREA = 1;
    public final static int OPERATOR_MULTI = 2;

    //Create Operator
    Operator[] operators;
    Operator currentOperator;

    //Create Tilesets
    Tileset originalTileset;
    Tileset generatedTileset;

    //Create Behaviour manager to handle selection interactions
    BehaviorManager behaviorManager;

    public ApplicationModel() {
        //Create Operators
        operators = new Operator[]{
                new SingleSelectorOperator(),
                new AreaSelectorOperator(),
                new MultiSelectorOperator()
        };

        //Create Behaviour Manager
        behaviorManager = new BehaviorManager();
    }

    public boolean isCurrentOperator(int operatorState){
        return currentOperator == operators[operatorState];
    }

    public void setCurrentOperator(int operatorState){
        currentOperator = operators[operatorState];
        behaviorManager.notifyOperatorChange(currentOperator);
    }
}
