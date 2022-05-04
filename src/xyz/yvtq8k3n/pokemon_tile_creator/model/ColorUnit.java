package xyz.yvtq8k3n.pokemon_tile_creator.model;

import java.awt.*;

public class ColorUnit extends Color {
    long occurrences;

    public ColorUnit(Color color, long occurrences) {
        super(color.getRGB());
        this.occurrences = occurrences;
    }

    public long getOccurrences() {
        return occurrences;
    }
}