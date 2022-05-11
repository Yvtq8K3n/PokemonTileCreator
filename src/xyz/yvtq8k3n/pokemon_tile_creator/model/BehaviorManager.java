package xyz.yvtq8k3n.pokemon_tile_creator.model;

import lombok.Data;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.AreaSelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.MultiSelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.SelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.SingleSelectableBehaviour;

import java.util.ArrayList;

@Data
public class BehaviorManager {
    //Selectable Behaviours Components
    private ArrayList<SelectableBehaviour> selections;
    private ArrayList<SingleSelectableBehaviour> singleSelections;
    private ArrayList<AreaSelectableBehaviour> areaSelections;
    private ArrayList<MultiSelectableBehaviour> multiSelections;

    public BehaviorManager() {
        //Create Selectors
        selections = new ArrayList<>();
        singleSelections = new ArrayList<>();
        areaSelections = new ArrayList<>();
        multiSelections = new ArrayList<>();
    }

    public void addSelection(SelectableBehaviour selection){
        if (!selections.contains(selection)) selections.add(selection);
    }

    public void addSingleSelectableBehaviour(SingleSelectableBehaviour c){
        singleSelections.add(c);
    }

    public void addAreaSelectableBehaviour(AreaSelectableBehaviour c){
        areaSelections.add(c);
    }

    public void addMultiSelectableBehaviour(MultiSelectableBehaviour c){
       multiSelections.add(c);
    }

    public void notifyOperatorChange(Operator current){
        for (SelectableBehaviour s:singleSelections) {
            s.setOperator(current);
        }
    }
}
