package xyz.yvtq8k3n.pokemon_tile_creator.model.sorting;

import xyz.yvtq8k3n.pokemon_tile_creator.model.ColorUnit;

public enum ColorUnitBlockFrequencySorting implements ColorUnitComparator {
    CRITERIA;

    @Override
    public int compare(ColorUnit o1, ColorUnit o2) {
        return 0;
    }

    @Override
    public String toString() {
        //Not implemented yet
        //Only Count if exist in each block of 8
        return "Occurrence";
    }
}
