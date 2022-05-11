package xyz.yvtq8k3n.pokemon_tile_creator.view.representation;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.OperatorController;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.AreaSelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.MultiSelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.behaviour.SelectableBehaviour;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.AreaSelector;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.MultiSelector;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.Selector;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.SingleSelector;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class SelectableRepresentation extends Representation implements SelectableBehaviour, AreaSelectableBehaviour, MultiSelectableBehaviour {
    protected static Color FILTER_BACKGROUND = Color.BLACK;
    protected static Color GRID_COLOR = Color.RED;
    private static final int[] GRID_SIZE = {16,8,0};

    protected SingleSelector singleSelector;
    protected AreaSelector areaSelector;
    protected MultiSelector multiSelector;
    protected Color colorFilter;
    protected int gridIndex;
    protected int gridSize;

    public SelectableRepresentation() {
        super();
        this.gridIndex = GRID_SIZE.length-1;
        this.singleSelector = new SingleSelector();
        this.areaSelector = new AreaSelector();
        this.multiSelector = new MultiSelector();

        //Add event listeners
        OperatorController.addSelectableBehaviour(this);
        OperatorController.addAreaSelectableBehaviour(this);
        OperatorController.addMultiSelectableBehaviour(this);
    }

    abstract boolean hasRepresentation();

    protected void paintSelectors(Graphics g) {
        drawGridComponent(g);
        if (hasRepresentation()){
            if(singleSelector.isActive()){
                drawPaintFilter(g, singleSelector);
                drawGridComponent(g);
                singleSelector.drawComponent(g);
            }

            if(areaSelector.isActive()){
                drawPaintFilter(g, areaSelector);
                drawGridComponent(g);
                areaSelector.drawComponent(g);
            }

            if (multiSelector.isActive()){
                drawPaintFilter(g, multiSelector);
                drawGridComponent(g);
                multiSelector.drawComponent(g);
            }
        }
    }


    protected void drawGridComponent(Graphics g){
        if (hasGridSize()) {
            g.setColor(GRID_COLOR);
            for (int i = 0; i < getWidth(); i += this.gridSize) {
                for (int j = 0; j < getHeight(); j += this.gridSize) {
                    g.drawRect(i, j, this.gridSize, this.gridSize);
                }
            }

        }
    }

    protected abstract void drawPaintFilter(Graphics g, Selector selector);

    public boolean hasColorFilter() {return colorFilter!=null;}

    public void setColorFilter(Color color) {
        this.colorFilter = color;
        repaint();
    }

    protected Point applyBoundsConstraint(int x, int y){
        //Replace x(0, max) if it's out of viewport
        x = Math.min(x, getWidth() - BLOCK);
        x = Math.max(x, 0);

        //Replace y(0, max) if it's out of viewport
        y = Math.min(y, getHeight() - BLOCK);
        y = Math.max(y, 0);
        return new Point(x, y);
    }

    public boolean hasGridSize(){
        return this.gridSize > 0;
    }

    public void changeGridIndex() {
        this.gridIndex++;
        if(gridIndex>=GRID_SIZE.length) this.gridIndex = 0;
        gridSize = GRID_SIZE[gridIndex];
        repaint();
    }

    @Override
    public void startSingleSelector(int x, int y){
        moveSingleSelector(x, y);
    }

    @Override
    public void moveSingleSelector(int x, int y){
        if (!hasRepresentation()) return;
        //Set selector location
        Point bound = applyBoundsConstraint(x, y);
        singleSelector.setInitialLocation(bound.x, bound.y);
        repaint();
    }

    @Override
    public void startAreaSelector(int x, int y) {
        Point bound = applyBoundsConstraint(x, y);
        areaSelector.setInitialLocation(bound.x, bound.y);
        resizeAreaSelector(x, y);
    }

    @Override
    public void resizeAreaSelector(int x, int y) {
        if (!hasRepresentation()) return;
        Point bound = applyBoundsConstraint(x, y);
        areaSelector.resizeSelector(bound.x, bound.y);
        repaint();
    }

    @Override
    public void addMultiSelectorPoint(int x, int y) {
        if (!hasRepresentation()) return;
        Point bound = applyBoundsConstraint(x, y);
        multiSelector.addSelectionEntry(bound.x, bound.y);
        repaint();
    }

    @Override
    public void removeMultiSelectorPoint(int x, int y) {
        Point bound = applyBoundsConstraint(x, y);
        multiSelector.removeSelectionEntry(bound.x, bound.y);
        repaint();
    }

    @Override
    public void reset(){
        singleSelector.setState(Selector.INACTIVE);
        areaSelector.setState(Selector.INACTIVE);
        multiSelector.setState(Selector.INACTIVE);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        OperatorController.selectedOperator.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        OperatorController.selectedOperator.mouseReleased(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        OperatorController.selectedOperator.mouseDragged(e);
    }
}
