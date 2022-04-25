package xyz.yvtq8k3n.pokemon_tile_creator;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;
import xyz.yvtq8k3n.pokemon_tile_creator.model.ApplicationModel;
import xyz.yvtq8k3n.pokemon_tile_creator.view.ApplicationView;

public class Main {

    public static void main(String[] args) {
        ApplicationModel model = new ApplicationModel();
        ApplicationView view = new ApplicationView();

        MainController.launchApplication(model, view);
        MainController.updateView();
    }
}
