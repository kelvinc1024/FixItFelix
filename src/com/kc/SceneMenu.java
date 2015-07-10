package com.kc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Vector;

import lib.Scene;
import lib.SimpleApplication;

public class SceneMenu extends Scene {

    int activeMenu = 0;
    int totalMenu = 3;
    String[] menu = new String[]{"New Game", "How To Play", "Resolution : ", "Exit"};
    Vector<Point> resolution = new Vector<Point>();
    int activeResolution = 2;

    Vector<RedDot> vRedDot = new Vector<RedDot>();
    Random rand = new Random();

    public SceneMenu(SimpleApplication simpleApplication) {
        super(simpleApplication);
        resolution.add(new Point(320, 240));
        resolution.add(new Point(640, 480));
        resolution.add(new Point(800, 600));
        resolution.add(new Point(1024, 768));
        for (int i = 0; i < 10; i++)
            vRedDot.add(new RedDot(rand.nextInt(800), rand.nextInt(600)));
    }

    @Override
    public void init() {
        SimpleApplication.faceEffect = 0.08f;
    }

    @Override
    public void update(double tpf) {
        for (RedDot r : vRedDot)
            r.update(tpf);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 800, 600);

        for (RedDot r : vRedDot)
            r.render(g2d);


        int width = AssetManager.getInstance().logo_big.getWidth(null);
        int height = AssetManager.getInstance().logo_big.getHeight(null);
        g2d.drawImage(AssetManager.getInstance().logo_big, (800 - width) / 2, 100, width, height, null);


        g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));


        for (int i = 0; i < menu.length; i++) {
            if (activeMenu != i) g2d.setColor(Color.WHITE);
            else g2d.setColor(Color.YELLOW);
            String s = menu[i];
            if (i == 2) s += SimpleGame.FRAME_WIDTH + "X" + SimpleGame.FRAME_HEIGHT;
            int stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
            g2d.drawString(s, (800 - stringLen) / 2, 350 + i * 50);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (activeMenu == 0) {
                SceneGame.score = 0;
                SceneGame.totalFloor = 10;
                simpleApplication.changeScene(new SceneGame(simpleApplication));
            }
            if (activeMenu == 1) {
                simpleApplication.changeScene(new SceneInfo(simpleApplication));
            }
            if (activeMenu == 3) {
                System.exit(0);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            simpleApplication.changeScene(new SceneTitle(simpleApplication));
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            activeMenu++;
            activeMenu %= menu.length;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            activeMenu--;
            if (activeMenu < 0) activeMenu = menu.length - 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (activeMenu == 2) {
                activeResolution--;
                if (activeResolution < 0) activeResolution = resolution.size() - 1;
                SimpleGame.jf.setSize(resolution.get(activeResolution).x, resolution.get(activeResolution).y);
                SimpleGame.jf.setLocationRelativeTo(null);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (activeMenu == 2) {
                activeResolution++;
                activeResolution %= resolution.size();
                SimpleGame.jf.setSize(resolution.get(activeResolution).x, resolution.get(activeResolution).y);
                SimpleGame.jf.setLocationRelativeTo(null);
            }
        }
    }
}
