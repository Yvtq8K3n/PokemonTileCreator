package xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.Operator;

public interface SelectableBehaviour {
    void setOperator(Operator current);

    void reset();
}
