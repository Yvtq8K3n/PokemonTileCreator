package xyz.yvtq8k3n.pokemon_tile_creator.view;

public interface SelectableBehaviour extends CustomBehaviour{

    void mouseDraggedSelectedAction(int x, int y);

    void mousePressedSelectedAction(int x, int y);

    void mouseExitSelectedAction(int x, int y);

}
