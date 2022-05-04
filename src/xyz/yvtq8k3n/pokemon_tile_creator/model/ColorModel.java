package xyz.yvtq8k3n.pokemon_tile_creator.model;

import lombok.Data;
import xyz.yvtq8k3n.pokemon_tile_creator.model.sorting.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class ColorModel {
    public static final ColorUnitComparator[] SORTING_METHODS = {
            ColorsUnitNone.CRITERIA,
            ColorsUnitStepSorting.CRITERIA,
            ColorUnitLuminositySorting.CRITERIA,
            ColorUnitFrequencySorting.CRITERIA
    };

    private Tileset tileset;
    private List<Color> allColors;
    private Color[][] colorsMap;
    private Map<Color, Long> colorsGroup;
    private Color[] allDistinctColors;
    private ColorUnit[] sortedColors;
    public int sortingIndex;

    protected ColorModel() {
        this.sortingIndex = 0;
    }

    public ColorModel(Tileset tileset) {
        this();
        this.tileset = tileset;
        this.allColors = retrieveAllColors();  //Contains all colors
        this.colorsMap = calculateColorsMap(); //Used to map colors directly on the tileset
        this.colorsGroup = retrieveColorsByGroup(); //Group colors to count occurrences
        this.allDistinctColors = retrieveAllDistinctColors();  //Contains only first occurrence of each color
        this.sortedColors = generateColorUnits();
    }

    //Loads all colors present in buffered into a 1D-Array
    private List<Color> retrieveAllColors() {
        BufferedImage image = tileset.getImage();
        List<Color> colors = new ArrayList<>();

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                colors.add(new Color(image.getRGB(i, j)));
            }
        }
        System.out.println("TotalColors: "+colors.size());
        return colors;
    }

    //Loads all colors into a 2-D array respecting their location on the image
    private Color[][] calculateColorsMap() {
        BufferedImage image = tileset.getImage();
        Iterator<Color> itColors = allColors.listIterator();

        Color[][] colorsMap = new Color[image.getWidth()][image.getHeight()];
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                colorsMap[i][j] = itColors.next();
                if (!itColors.hasNext()) break;
            }
        }
        System.out.println("Colors Map: "+colorsMap.length);
        return colorsMap;
    }

    //Group all colors by their respective color
    private Map<Color, Long> retrieveColorsByGroup() {
       return allColors.stream().collect(
               Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private Color[] retrieveAllDistinctColors() {
        List<Color> colorsFilter = new ArrayList<>();

        for (Color pixelColor : allColors) {
            if (!colorsFilter.contains(pixelColor)) colorsFilter.add(pixelColor);
        }

        return colorsFilter.toArray(new Color[colorsFilter.size()]);
    }

    private ColorUnit[] generateColorUnits() {
        List<ColorUnit> colorsUnits = new ArrayList<>();

        for (Color distinctColor : allDistinctColors) {
            long occurrences = colorsGroup.get(distinctColor);
            colorsUnits.add(new ColorUnit(distinctColor, occurrences));
        }

        return colorsUnits.toArray(new ColorUnit[colorsUnits.size()]);
    }

    public void changeSortingMethod() {
        this.sortingIndex++;
        if(sortingIndex>=SORTING_METHODS.length) this.sortingIndex = 0;
    }

    public Color[] getSortedColors() {
        ColorUnit[] colorsUnits = Arrays.copyOf(this.sortedColors, sortedColors.length);
        Arrays.sort(colorsUnits, SORTING_METHODS[sortingIndex]);
        return colorsUnits;
    }

    public String getSortingMethod(){
        return SORTING_METHODS[sortingIndex].toString();
    }

}
