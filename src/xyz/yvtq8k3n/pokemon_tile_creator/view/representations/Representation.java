package xyz.yvtq8k3n.pokemon_tile_creator.view.representations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Representation extends JPanel implements MouseMotionListener, MouseListener {
    protected final static int BLOCK = 16;

    public Representation() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (hasRepresentation()) {
            drawRepresentation(g);
        }
    }

    protected abstract void drawRepresentation(Graphics g);

    public abstract boolean hasRepresentation();

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
