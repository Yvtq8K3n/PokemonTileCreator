package xyz.yvtq8k3n.pokemon_tile_creator.model;

import lombok.Data;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable.SelectableBehaviour;

import java.util.ArrayList;

@Data
public class BehaviorManager {
    //Selectable Behaviours Components
    private ArrayList<SelectableBehaviour> selectables;

    public BehaviorManager() {
        //Create Selectors
        selectables = new ArrayList<>();
    }


    public void notifyOperatorChange(Operator current){
        for (SelectableBehaviour s: selectables) {
            s.setOperator(current);
        }
    }

    public void addSelectable(SelectableBehaviour c) {
        selectables.add(c);
    }
}
