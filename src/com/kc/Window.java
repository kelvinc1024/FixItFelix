package com.kc;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Vector;

import lib.GameObject;

public class Window extends GameObject {

    int status;
    Vector<Image> vImage = new Vector<Image>();

    public Window(float x, float y, float width, float height, int status, Vector<Image> windowImage) {
        super(x, y, width, height);
        this.status = status;
        vImage = windowImage;
    }

    @Override
    public void init() {
    }

    @Override
    public void update(double tpf) {
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.drawImage(vImage.get(status), (int) x, (int) y, (int) width, (int) height, null);
    }
}
