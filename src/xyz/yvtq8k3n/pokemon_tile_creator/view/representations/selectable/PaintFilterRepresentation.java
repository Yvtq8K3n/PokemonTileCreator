package xyz.yvtq8k3n.pokemon_tile_creator.view.representations.selectable;

import xyz.yvtq8k3n.pokemon_tile_creator.controller.OperatorController;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.TileController;
import xyz.yvtq8k3n.pokemon_tile_creator.controller.operators.Operator;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.AreaSelector;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.MultiSelector;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.Selector;
import xyz.yvtq8k3n.pokemon_tile_creator.view.selection.SingleSelector;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public abstract class PaintFilterRepresentation extends SelectableRepresentation implements SelectableBehaviour {
    protected static Color FILTER_BACKGROUND = Color.BLACK;
    protected static Color GRID_COLOR = Color.RED;
    private static final int[] GRID_SIZE = {16,8,0};

    protected Operator currentOperator;
    protected Selector[] selectors;
    protected Color colorFilter;
    private List<Point> colorFilterLocation;
    protected int gridIndex;
    protected int gridSize;

    public PaintFilterRepresentation() {
        super();
        this.gridIndex = GRID_SIZE.length-1;
        this.selectors = new Selector[]{
            new SingleSelector(),
            new AreaSelector(),
            new MultiSelector()
        };
        selectorInUse = selectors[0];
        OperatorController.addSelectableBehaviour(this);
    }

    protected void drawSelector(Graphics g) {
        drawGridComponent(g);
        if (hasRepresentation() && selectorInUse != null){
            drawPaintFilter(g, selectorInUse);
            drawGridComponent(g);
            selectorInUse.drawComponent(g);
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
        this.colorFilterLocation = TileController.getColor(this.colorFilter);
        repaint();
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

    //#Called quite a few times
    @Override
    public void setOperator(Operator current) {
        int index = OperatorController.getOperatorIndex(current);
        this.selectorInUse.resetSelection();
        this.selectorInUse = selectors[index];
        this.currentOperator = current;
        repaint();
    }


    @Override
    public void mousePressed(MouseEvent e) {
        currentOperator.mousePressed(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currentOperator.mouseDragged(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        currentOperator.mouseReleased(e);
    }
}
