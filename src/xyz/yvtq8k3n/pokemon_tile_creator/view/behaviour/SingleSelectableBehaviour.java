package xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour;

public interface SingleSelectableBehaviour extends SelectableBehaviour {

    void startSingleSelector(int x, int y);

    void moveSingleSelector(int x, int y);
}
