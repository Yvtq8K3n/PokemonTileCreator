package xyz.yvtq8k3n.pokemon_tile_creator.controller;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel;
import xyz.yvtq8k3n.pokemon_tile_creator.model.BehaviorManager;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable.SelectableBehaviour;

public enum OperatorController {
    OPERATOR_CONTROLLER;

    static ApplicationModel model;
    static BehaviorManager behaviorManager;

    public static void initController(ApplicationModel model, BehaviorManager behaviorManager) {
        OperatorController.model = model;
        OperatorController.behaviorManager = behaviorManager;
    }

    public static void setSelectionOperator(int operatorState) {
        model.setCurrentOperator(operatorState);
        behaviorManager.notifyOperatorChange(model.getCurrentOperator());
        System.out.println("Setting selector: "+model.getCurrentOperator().getClass().getName());
    }

    public static int getOperatorIndex(Operator operator) {
        Operator[] operators = model.getOperators();
        if(operators == null) throw new IllegalArgumentException("No valid operators found");

        return model.getOperatorIndex(operator);
    }


    public static void addSelectableBehaviour(SelectableBehaviour c){
        behaviorManager.addSelectable(c);
    }

    public static Operator getOperator(int operatorState) {
        return model.getOperator(operatorState);
    }
}
