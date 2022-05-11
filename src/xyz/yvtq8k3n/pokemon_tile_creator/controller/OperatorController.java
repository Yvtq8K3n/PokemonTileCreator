package xyz.yvtq8k3n.pokemon_tile_creator.controller;

import xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel;
import xyz.yvtq8k3n.pokemon_tile_creator.model.operators.AreaSelectorOperator;
import xyz.yvtq8k3n.pokemon_tile_creator.model.operators.MultiSelectorOperator;
import xyz.yvtq8k3n.pokemon_tile_creator.model.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.model.operators.SingleSelectorOperator;
import xyz.yvtq8k3n.pokemon_tile_creator.view.ApplicationView;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.AreaSelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.MultiSelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.SelectableBehaviour;

import java.awt.*;
import java.util.ArrayList;

public enum OperatorController {
    MAIN_CONTROLLER;

    static ApplicationModel model;
    static ApplicationView view;

    static Operator[] operators;
    public static Operator selectedOperator;

    private static ArrayList<SelectableBehaviour> selectableBehaviours;
    private static ArrayList<AreaSelectableBehaviour> areaSelectableBehaviours;
    private static ArrayList<MultiSelectableBehaviour> multiSelectableBehaviours;

    public static void launchApplication(ApplicationModel model, ApplicationView view) {
        //Create Operator
        OperatorController.operators = new Operator[]{
                new SingleSelectorOperator(),
                new AreaSelectorOperator(),
                new MultiSelectorOperator()
        };
        selectedOperator = operators[0];
        OperatorController.model = model;
        OperatorController.view = view;
    }

    public static void setImageColorFilter(Color color) {
        view.originalTilesetPanel.tileRepresentation.setColorFilter(color);
        if (selectedOperator == operators[0]){
            view.loadPanel.blockRepresentation.setColorFilter(color);
        }
    }


    public static void updateView() {
        view.repaint();
    }


    public static void setOperatorSelection() {
        if (!selectedOperator.equals(operators[0])){
            System.out.println("Setting selector: Single Selector");
            selectedOperator = operators[0];
            reset();
        }
    }

    public static void setOperatorAreaSelection() {
         if (!selectedOperator.equals(operators[1])){
            System.out.println("Setting selector: Area Selector");
            selectedOperator = operators[1];
            reset();
         }
    }

    public static void setOperatorMultiSelection() {
        if (!selectedOperator.equals(operators[2])){
            System.out.println("Setting selector: Area Selector");
            selectedOperator = operators[2];
            reset();
        }
    }

    public static void addSelectableBehaviour(SelectableBehaviour c){
        if (selectableBehaviours == null){
            selectableBehaviours = new ArrayList<>();
        }
        OperatorController.selectableBehaviours.add(c);
    }

    public static void addAreaSelectableBehaviour(AreaSelectableBehaviour c){
        if (areaSelectableBehaviours == null){
            areaSelectableBehaviours = new ArrayList<>();
        }
        OperatorController.areaSelectableBehaviours.add(c);
    }

    public static void addMultiSelectableBehaviour(MultiSelectableBehaviour c){
        if (multiSelectableBehaviours == null){
            multiSelectableBehaviours = new ArrayList<>();
        }
        OperatorController.multiSelectableBehaviours.add(c);
    }

    public static void reset() {
        for (SelectableBehaviour c: selectableBehaviours) {
            c.reset();
        }
    }
}
