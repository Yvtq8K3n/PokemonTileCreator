package xyz.yvtq8k3n.pokemon_tile_creator.model.sorting;

import xyz.yvtq8k3n.pokemon_tile_creator.model.ColorUnit;

import java.awt.*;

import static xyz.yvtq8k3n.pokemon_tile_creator.ColorHelper.convertRGBToHSV;

public enum ColorUnitHueSorting implements ColorUnitComparator {
    CRITERIA;

    @Override
    public int compare(ColorUnit o1, ColorUnit o2) {
        //Convert obj1 to HSV format
        double[] HSVobj1 = convertRGBToHSV(o1);
        double obj1Hue = HSVobj1[0];
        double obj1saturation = HSVobj1[1];
        double obj1Value = HSVobj1[2];

        //Convert obj2 to HSV format
        double[] HSVobj2 = convertRGBToHSV(o2);
        double obj2Hue = HSVobj2[0];
        double obj2saturation = HSVobj2[1];
        double obj2value = HSVobj2[2];


        //Check which color has higher hue value
        if (obj2Hue < obj1Hue) return -1;
        else if (obj2Hue > obj1Hue) return 1;

        //Check which color has more saturation
        if (obj2saturation < obj1saturation) return -1;
        if (obj2saturation > obj1saturation) return 1;

        //Check which color has higher color value
        return Double.compare(obj2value, obj1Value);
    }

    @Override
    public String toString() {
        return "Hue";
    }
}
