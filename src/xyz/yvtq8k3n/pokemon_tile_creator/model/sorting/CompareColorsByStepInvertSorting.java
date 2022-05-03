package xyz.yvtq8k3n.pokemon_tile_creator.model.sorting;

import java.awt.*;

import static xyz.yvtq8k3n.pokemon_tile_creator.ColorHelper.calculateLuminosity;
import static xyz.yvtq8k3n.pokemon_tile_creator.ColorHelper.convertRGBToHSV;

public enum CompareColorsByStepInvertSorting implements ColorComparator {
    CRITERIA;

    private static final int REPETITIONS = 8;

    @Override
    public int compare(Color o1, Color o2) {
        //Convert obj1 to HSV format

        //To dampen the impact that sorting on the first component has, we can reduce the colour space from a float value between 0 to 1, to an integer from 0 to 7.

        double[] HSVobj1 = convertRGBToHSV(o1);
        int obj1Hue = (int) (HSVobj1[0] * REPETITIONS);
        int obj1Luminosity = (int) (calculateLuminosity(o1) * REPETITIONS);
        int obj1Value = (int) (HSVobj1[2] * REPETITIONS);

        if (obj1Hue % 2 == 1){
            obj1Value = REPETITIONS - obj1Value;
            obj1Luminosity = REPETITIONS - obj1Luminosity;
        }

        //Convert obj2 to HSV format
        double[] HSVobj2 = convertRGBToHSV(o2);
        int obj2Hue = (int) (HSVobj2[0] * REPETITIONS);
        int obj2Luminosity = (int) (calculateLuminosity(o2) * REPETITIONS);
        int obj2Value = (int) (HSVobj2[2] * REPETITIONS);

        if (obj2Hue % 2 == 1) {
            obj2Value = REPETITIONS - obj2Value;
            obj2Luminosity = REPETITIONS - obj2Luminosity;
        }

        //Check which color has higher hue value
        if (obj2Hue < obj1Hue) return -1;
        if (obj2Hue > obj1Hue) return 1;

        //Check which color has more saturation
        if (obj2Luminosity < obj1Luminosity) return -1;
        if (obj2Luminosity > obj1Luminosity) return 1;

        //Check which color has higher color value
        return Integer.compare(obj2Value, obj1Value);
    }
}
