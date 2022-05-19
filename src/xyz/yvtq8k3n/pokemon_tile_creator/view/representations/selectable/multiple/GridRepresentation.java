package xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable.multiple;

import xyz.yvtq8k3n.pokemon_tile_creator.view.components.GridViewer;
import xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable.MultiSelectableRepresentation;

public abstract class GridRepresentation extends MultiSelectableRepresentation {
    protected GridViewer gridViewer;

    public GridRepresentation() {
        super();
        gridViewer = new GridViewer();
        setGridBounds();
    }

    abstract void setGridBounds();

    public void changeGrid() {
        gridViewer.changeGridIndex();
        repaint();
    }

}
