package xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour;

public interface SelectableBehaviour extends CustomBehaviour {

    void startSelector(int x, int y);

    void moveSelector(int x, int y);

    void exitSelectedAction(int x, int y);
}
