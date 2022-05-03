package xyz.yvtq8k3n.pokemon_tile_creator.model.sorting;

import java.awt.*;

public enum CompareColorsNone implements ColorComparator{
    CRITERIA;

    @Override
    public int compare(Color o1, Color o2) {
        return 0;
    }
}
