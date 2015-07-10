/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import lib.GameObject;

/**
 * @author kc13-0
 */
public abstract class KCButton extends GameObject implements MouseListener, MouseMotionListener {

    private Image onRelease;
    private Image onHover;
    private boolean isHover = false;
    public SimpleApplication simpleApplication;

    public KCButton(SimpleApplication simpleApplication, Image onRelease, Image onHover, float x, float y, float width, float height) {
        super(x, y, width, height);
        this.onHover = onHover;
        this.onRelease = onRelease;
        this.simpleApplication = simpleApplication;
    }

    @Override
    public void init() {
    }

    @Override
    public void update(double tpf) {
    }

    @Override
    public void render(Graphics2D g2d) {
        if (isHover)
            g2d.drawImage(onHover, (int) x, (int) y, (int) width, (int) height, null);
        else
            g2d.drawImage(onRelease, (int) x, (int) y, (int) width, (int) height, null);


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
        if (getRect().contains(e.getPoint())) {
            isHover = true;
        } else {
            isHover = false;
        }
    }

}
