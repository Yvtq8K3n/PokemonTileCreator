package xyz.yvtq8k3n.pokemon_tile_creator.model;

import lombok.Data;
import xyz.yvtq8k3n.pokemon_tile_creator.model.operators.AreaSelectorOperator;
import xyz.yvtq8k3n.pokemon_tile_creator.model.operators.MultiSelectorOperator;
import xyz.yvtq8k3n.pokemon_tile_creator.model.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.model.operators.SingleSelectorOperator;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.AreaSelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.MultiSelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.SelectableBehaviour;

import java.io.*;
import java.util.ArrayList;

@Data
public class ApplicationModel {
    enum OperatorState{
        SINGLE(0),
        AREA(1),
        MULTI(2);

        private int value;

        OperatorState(int value){
            this.value = value;
        }
    }

    //Create Tilesets
    Tileset originalTileset;
    Tileset generatedTileset;

    //Create Operators
    Operator[] operators;
    Operator selectedOperator;

    //Selectable Behaviours Components
    private static ArrayList<SelectableBehaviour> selectableBehaviours;
    private static ArrayList<AreaSelectableBehaviour> areaSelectableBehaviours;
    private static ArrayList<MultiSelectableBehaviour> multiSelectableBehaviours;

    public ApplicationModel() {
        //this.originalTileset = new Tileset();
        //this.generatedTileset = new Tileset();

        //Create Operators
        operators = new Operator[]{
                new SingleSelectorOperator(),
                new AreaSelectorOperator(),
                new MultiSelectorOperator()
        };
        selectedOperator = operators[0];

        //Create Selectors
        selectableBehaviours = new ArrayList<>();
        areaSelectableBehaviours = new ArrayList<>();
        multiSelectableBehaviours = new ArrayList<>();
    }


    public boolean isOperator(OperatorState operatorState){
        int index = operatorState.value;
        return selectedOperator == operators[index];
    }

    public ColorModel getOriginalColorModel(){
        return originalTileset.getColorModel();
    }

    public ColorModel getConvertedColorModel(){
        return originalTileset.getColorModel();
    }

    public boolean hasGeneratedImage(){
        return generatedTileset.hasImage();
    }

}
