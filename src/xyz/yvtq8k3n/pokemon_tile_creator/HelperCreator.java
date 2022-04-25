package xyz.yvtq8k3n.pokemon_tile_creator;

import java.awt.*;

public class HelperCreator {
    public static Dimension createDimension(int[] dimension){
        Dimension dim = new Dimension(dimension[0], dimension[1]);
        return dim;
    }
}

