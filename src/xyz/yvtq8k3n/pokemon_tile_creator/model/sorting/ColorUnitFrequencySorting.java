package xyz.yvtq8k3n.pokemon_tile_creator.model.sorting;

import xyz.yvtq8k3n.pokemon_tile_creator.model.ColorUnit;

public enum ColorUnitFrequencySorting implements ColorUnitComparator {
    CRITERIA;

    @Override
    public int compare(ColorUnit o1, ColorUnit o2) {
        return Long.compare(o2.getOccurrences(), o1.getOccurrences());
    }

    @Override
    public String toString() {
        return "Occurrence";
    }
}
