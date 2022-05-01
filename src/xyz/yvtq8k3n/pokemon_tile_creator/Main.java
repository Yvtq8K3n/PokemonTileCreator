package xyz.yvtq8k3n.pokemon_tile_creator;

import com.formdev.flatlaf.FlatIntelliJLaf;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;
import xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel;
import xyz.yvtq8k3n.pokemon_tile_creator.view.ApplicationView;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try {
            //Attempting to get System Look and Feel
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ApplicationModel model = new ApplicationModel();
        ApplicationView view = new ApplicationView();

        MainController.launchApplication(model, view);
        MainController.updateView();
    }
}
