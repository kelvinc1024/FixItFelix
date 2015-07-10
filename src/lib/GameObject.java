package lib;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;

import lib.IGameBehavior;

public abstract class GameObject implements IGameBehavior, KeyListener, MouseListener, MouseMotionListener {
    public float x, y;
    public float width, height;

    public GameObject(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle getRect() {
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }

    @Override
    public void init() {
    }

    @Override
    public void update(double tpf) {
    }

    @Override
    public void render(Graphics2D g2d) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

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
