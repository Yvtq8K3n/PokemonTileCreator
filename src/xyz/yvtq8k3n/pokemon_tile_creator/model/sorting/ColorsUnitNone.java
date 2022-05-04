package xyz.yvtq8k3n.pokemon_tile_creator.model.sorting;

import xyz.yvtq8k3n.pokemon_tile_creator.model.ColorUnit;

import java.awt.*;

public enum ColorsUnitNone implements ColorUnitComparator {
    CRITERIA;

    @Override
    public int compare(ColorUnit o1, ColorUnit o2) {
        return 0;
    }

    @Override
    public String toString() {
        return "None";
    }
}
