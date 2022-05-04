package xyz.yvtq8k3n.pokemon_tile_creator.model.sorting;

import xyz.yvtq8k3n.pokemon_tile_creator.model.ColorUnit;

import java.awt.*;

import static xyz.yvtq8k3n.pokemon_tile_creator.ColorHelper.calculateLuminosity;

public enum ColorUnitLuminositySorting implements ColorUnitComparator {
    CRITERIA;

    @Override
    public int compare(ColorUnit o1, ColorUnit o2) {
        double obj1Luminosity = calculateLuminosity(o1);

        double obj2Luminosity = calculateLuminosity(o2);

        if (obj2Luminosity < obj1Luminosity) return -1;
        else if (obj2Luminosity > obj1Luminosity) return 1;
        return 0;
    }

    @Override
    public String toString() {
        return "Luminosity";
    }
}
