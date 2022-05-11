package xyz.yvtq8k3n.pokemon_tile_creator.controller;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel;
import xyz.yvtq8k3n.pokemon_tile_creator.model.BehaviorManager;
import xyz.yvtq8k3n.pokemon_tile_creator.view.ApplicationView;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.AreaSelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.MultiSelectableBehaviour;
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

    public static void setOperatorSingleSelection() {
        Operator currentOperator = model.getCurrentOperator();
        if(currentOperator == null) return;

        if(!model.isCurrentOperator(OPERATOR_SINGLE)){
            System.out.println("Setting selector: Single Selector");
            model.setCurrentOperator(OPERATOR_SINGLE);
            reset();
        }
    }

    public static void setOperatorAreaSelection() {
        Operator currentOperator = model.getCurrentOperator();
        if(currentOperator == null) return;

        if(!model.isCurrentOperator(OPERATOR_AREA)){
            System.out.println("Setting selector: Area Selector");
            model.setCurrentOperator(OPERATOR_AREA);
            reset();
        }
    }

    public static void setOperatorMultiSelection() {
        Operator currentOperator = model.getCurrentOperator();
        if(currentOperator == null) return;

        if(!model.isCurrentOperator(OPERATOR_MULTI)){
            System.out.println("Setting selector: Multi Selector");
            model.setCurrentOperator(OPERATOR_MULTI);
            reset();
        }
    }

    public static void addSingleSelectableBehaviour(SingleSelectableBehaviour c){
        BehaviorManager behaviorManager = model.getBehaviorManager();
        if (behaviorManager == null) return;

        behaviorManager.addSelection(c);
        behaviorManager.addSingleSelectableBehaviour(c);
    }

    public static void addAreaSelectableBehaviour(AreaSelectableBehaviour c){
        BehaviorManager behaviorManager = model.getBehaviorManager();
        if (behaviorManager == null) return;

        behaviorManager.addSelection(c);
        behaviorManager.addAreaSelectableBehaviour(c);
    }

    public static void addMultiSelectableBehaviour(MultiSelectableBehaviour c){
        BehaviorManager behaviorManager = model.getBehaviorManager();
        if (behaviorManager == null) return;

        behaviorManager.addSelection(c);
        behaviorManager.addMultiSelectableBehaviour(c);
    }

    public static void reset() {
        System.out.println("THE WORLD IS ON FIRE");
        /*for (SingleSelectableBehaviour c: singleSelectableBehaviours) {
            c.reset();
        }*/
    }
}
