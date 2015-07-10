package com.kc;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import lib.GameObject;

public class RedDot extends GameObject {

    float radius = 50;
    Random rand = new Random();

    public RedDot(float x, float y) {
        super(x, y, 0, 0);
        this.radius = rand.nextInt(150) + 50;
    }


    @Override
    public void update(double tpf) {
        radius += 100 * tpf;
        if (radius > 200) {
            radius = 50;
            x = rand.nextInt(800);
            y = rand.nextInt(600);
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(new Color(0.3f, 0.3f, 0.3f, ((200f - radius) / 150f)));
        g2d.fillOval((int) (x - radius / 2), (int) (y - radius / 2), (int) radius, (int) radius);
    }
}
