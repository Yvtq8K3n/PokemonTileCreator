package xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour;

public interface MultiSelectableBehaviour extends SelectableBehaviour {

    void addMultiSelectorPoint(int x, int y);

    void removeMultiSelectorPoint(int x, int y);
}
