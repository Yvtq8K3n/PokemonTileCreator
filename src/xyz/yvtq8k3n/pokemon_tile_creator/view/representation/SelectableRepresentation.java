package xyz.yvtq8k3n.pokemon_tile_creator.view.representation;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.MainController;
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
    private static final int[] GRID_SIZE = {2,1,0};
    int gridIndex;

    protected SingleSelector singleSelector;
    protected AreaSelector areaSelector;
    protected MultiSelector multiSelector;
    protected Color colorFilter;

    public SelectableRepresentation() {
        super();
        this.gridIndex = GRID_SIZE.length-1;
        this.singleSelector = new SingleSelector();
        this.areaSelector = new AreaSelector();
        this.multiSelector = new MultiSelector();

        //Add event listeners
        MainController.addSelectableBehaviour(this);
        MainController.addAreaSelectableBehaviour(this);
        MainController.addMultiSelectableBehaviour(this);
    }

    abstract boolean hasRepresentation();

    protected void paintSelectors(Graphics g) {
        if (hasRepresentation()){
            if(singleSelector.isActive()){
                singleSelector.drawComponent(g);
                drawPaintFilter(g, singleSelector);
            }

            if(areaSelector.isActive()){
                areaSelector.drawComponent(g);
                drawPaintFilter(g, areaSelector);
            }

            if (multiSelector.isActive()){
                multiSelector.drawComponent(g);
                drawPaintFilter(g, multiSelector);
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


    @Override
    public void startSelector(int x, int y){
        moveSelector(x, y);
    }

    @Override
    public void moveSelector(int x, int y){
        if (!hasRepresentation()) return;
        //Set selector location
        Point bound = applyBoundsConstraint(x, y);
        singleSelector.setInitialLocation((int)bound.getX(), (int)bound.getY());
        repaint();
    }

    @Override
    public void startAreaSelector(int x, int y) {
        Point bound = applyBoundsConstraint(x, y);
        areaSelector.setInitialLocation((int)bound.getX(), (int)bound.getY());
        resizeAreaSelector(x, y);
    }

    @Override
    public void resizeAreaSelector(int x, int y) {
        if (!hasRepresentation()) return;
        Point bound = applyBoundsConstraint(x, y);
        areaSelector.resizeSelector((int)bound.getX(), (int)bound.getY());
        repaint();
    }

    @Override
    public void addMultiSelectorPoint(int x, int y) {
        if (!hasRepresentation()) return;
        Point bound = applyBoundsConstraint(x, y);
        multiSelector.addSelectionEntry((int)bound.getX(), (int)bound.getY());
        repaint();
    }

    @Override
    public void removeMultiSelectorPoint(int x, int y) {
        Point bound = applyBoundsConstraint(x, y);
        multiSelector.removeSelectionEntry((int)bound.getX(), (int)bound.getY());
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
        MainController.selectedOperator.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MainController.selectedOperator.mouseReleased(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MainController.selectedOperator.mouseDragged(e);
    }
}
