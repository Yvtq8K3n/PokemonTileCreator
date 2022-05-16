package xyz.yvtq8k3n.pokemon_tile_creator;

import com.formdev.flatlaf.FlatIntelliJLaf;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.LoadController;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.OperatorController;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.PaletteController;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.TileController;
import xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel;
import xyz.yvtq8k3n.pokemon_tile_creator.model.BehaviorManager;
import xyz.yvtq8k3n.pokemon_tile_creator.view.ApplicationView;

import javax.swing.*;

import static xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel.OPERATOR_SINGLE;

public class Main {
    private static int DEFAULT_OPERASTOR =  OPERATOR_SINGLE ;

    public static void main(String[] args) {
        try {
            //Attempting to get System Look and Feel
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApplicationModel model = new ApplicationModel();
        BehaviorManager behaviorManager = new BehaviorManager();
        OperatorController.initController(model, behaviorManager);

        //Init Controllers
        ApplicationView view = new ApplicationView();
        LoadController.initController(model, view);
        PaletteController.initController(model, view);
        TileController.initController(model, view);


        //Set Operator
        OperatorController.setSelectionOperator(OPERATOR_SINGLE);
    }
}
