package xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.Operator;

public interface SelectableBehaviour {
    void startSelection(int x, int y);
    void dragSelection(int x, int y);
    void removeSelection(int x, int y);
    void setOperator(Operator current);
}
