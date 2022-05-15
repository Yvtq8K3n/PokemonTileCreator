package xyz.yvtq8k3n.pokemon_tile_creator.controller;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel;
import xyz.yvtq8k3n.pokemon_tile_creator.model.BehaviorManager;
import xyz.yvtq8k3n.pokemon_tile_creator.view.ApplicationView;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.AreaSelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.MultiSelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.SelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.SingleSelectableBehaviour;

import static xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel.*;

public enum OperatorController {
    OPERATOR_CONTROLLER;

    static ApplicationModel model;
    static ApplicationView view;

    public static void initController(ApplicationModel model) {
        OperatorController.model = model;
    }

    public static void setView(ApplicationView view){
        OperatorController.view = view;

        //When view is initialized set Operator
        model.setCurrentOperator(OPERATOR_SINGLE);
    }

    public static void setSelectionOperator(int operatorState) {
        Operator currentOperator = model.getCurrentOperator();
        if(currentOperator == null) throw new IllegalArgumentException("Invalid operator selected");

        model.setCurrentOperator(operatorState);
        System.out.println("Setting selector: "+model.getCurrentOperator().getClass().getName());
        reset();
    }

    public static int getOperatorIndex(Operator operator) {
        Operator[] operators = model.getOperators();
        if(operators == null) throw new IllegalArgumentException("No valid operators found");

        return model.getOperatorIndex(operator);
    }


    public static void addSelectableBehaviour(SelectableBehaviour c){
        BehaviorManager behaviorManager = model.getBehaviorManager();
        if (behaviorManager == null) return;

        behaviorManager.addSelectable(c);
    }

    public static void addSingleSelectableBehaviour(SingleSelectableBehaviour c){
        BehaviorManager behaviorManager = model.getBehaviorManager();
        if (behaviorManager == null) return;

        behaviorManager.addSelectable(c);
        behaviorManager.addSingleSelectableBehaviour(c);
    }

    public static void addAreaSelectableBehaviour(AreaSelectableBehaviour c){
        BehaviorManager behaviorManager = model.getBehaviorManager();
        if (behaviorManager == null) return;

        behaviorManager.addSelectable(c);
        behaviorManager.addAreaSelectableBehaviour(c);
    }

    public static void addMultiSelectableBehaviour(MultiSelectableBehaviour c){
        BehaviorManager behaviorManager = model.getBehaviorManager();
        if (behaviorManager == null) return;

        behaviorManager.addSelectable(c);
        behaviorManager.addMultiSelectableBehaviour(c);
    }

    public static void reset() {
        System.out.println("THE WORLD IS ON FIRE");
        /*for (SingleSelectableBehaviour c: singleSelectableBehaviours) {
            c.reset();
        }*/
    }
}
