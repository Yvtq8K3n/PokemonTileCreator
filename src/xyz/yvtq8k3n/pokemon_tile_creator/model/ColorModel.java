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
    private Color[] allDistinctColors;
    private Map<Color, List<Point>> colorsMap;
    private ColorUnit[] sortedColors;
    public int sortingIndex;

    protected ColorModel() {
        this.sortingIndex = 0;
    }

    public ColorModel(Tileset tileset) {
        this();
        this.tileset = tileset;
        this.allColors = retrieveAllColors();  //Contains all colors
        this.allDistinctColors = retrieveAllDistinctColors();  //Contains only first occurrence of each color
        this.colorsMap = calculateColorsMap(); //Group colors to count occurrences
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

    //Group all colors by their respective color
    private Map<Color, List<Point>> calculateColorsMap() {
        Map<Color, List<Point>> colorsGroup = new HashMap();
        Iterator<Color> itColors = allColors.listIterator();

        BufferedImage image = tileset.getImage();
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = itColors.next();
                if(!colorsGroup.containsKey(color)){
                    colorsGroup.put(color,new ArrayList<>());
                }
                List<Point> list = colorsGroup.get(color);
                list.add(new Point(i, j));
            }
        }
        return colorsGroup;
    }

    private Color[] retrieveAllDistinctColors() {
        List<Color> colorsFilter = new ArrayList<>();

        for (Color pixelColor : allColors) {
            if (!colorsFilter.contains(pixelColor)){
                colorsFilter.add(pixelColor);
            }
        }

        System.out.println("AllDifferentColors:"+ colorsFilter.size());
        return colorsFilter.toArray(new Color[colorsFilter.size()]);
    }

    private ColorUnit[] generateColorUnits() {
        List<ColorUnit> colorsUnits = new ArrayList<>();

        for (Color distinctColor : allDistinctColors) {
            List<Point> colorPoints = colorsMap.get(distinctColor);
            long occurrences = colorPoints.size();
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

    public List<Point> getColorLocations(Color color){
        return colorsMap.get(color);
    }
}
