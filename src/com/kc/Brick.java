package com.kc;

import java.awt.Graphics2D;
import java.awt.Image;

import lib.GameObject;

public class Brick extends GameObject {
    Image image;

    public Brick(float x, float y, float width, float height) {
        super(x, y, width, height);
        image = AssetManager.getInstance().brick;
    }

    @Override
    public void init() {
    }

    @Override
    public void update(double tpf) {
        y += 70 * tpf;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
    }
}
