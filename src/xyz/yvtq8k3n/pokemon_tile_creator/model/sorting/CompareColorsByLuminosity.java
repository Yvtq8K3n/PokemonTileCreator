package xyz.yvtq8k3n.pokemon_tile_creator.model.sorting;

import java.awt.*;

import static xyz.yvtq8k3n.pokemon_tile_creator.ColorHelper.calculateLuminosity;

public enum CompareColorsByLuminosity implements ColorComparator{
    CRITERIA;

    @Override
    public int compare(Color o1, Color o2) {
        double obj1Luminosity = calculateLuminosity(o1);

        double obj2Luminosity = calculateLuminosity(o2);

        if (obj2Luminosity < obj1Luminosity) return -1;
        else if (obj2Luminosity > obj1Luminosity) return 1;
        return 0;
    }
}
