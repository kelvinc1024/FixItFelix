package com.kc;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;
import java.util.Vector;

import lib.GameObject;

public class Floor extends GameObject {

    Image mainBody;
    Vector<Window> vWindow;
    Random rand = new Random();

    public Floor(float x, float y, float width, float height, Image mainBody, Vector<Image> vWindows, int status) {
        super(x, y, width, height);
        this.mainBody = mainBody;
        vWindow = new Vector<Window>();
        for (int j = 0; j < 5; j++) {
            int xn = SceneGame.gridPoint.get(j).x;
            int yn = (int) y;
            if (status == 0) {
                int stat = rand.nextInt(4) + 1;
                if (stat == 3) stat = 5;
                vWindow.add(new Window(xn, yn, 50, 86, stat, AssetManager.getInstance().vWindowA));
            } else
                vWindow.add(new Window(xn, yn, 50, 86, WindowState.CLOSED, AssetManager.getInstance().vWindowA));
        }
    }

    @Override
    public void init() {
        for (Window w : vWindow) {
            w.init();
        }
    }

    @Override
    public void update(double tpf) {
        for (Window w : vWindow) {
            w.update(tpf);
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.drawImage(mainBody, (int) x, (int) y, (int) width, (int) height, null);
        for (Window w : vWindow) {
            w.render(g2d);
        }
    }
}
