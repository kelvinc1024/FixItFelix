package com.kc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import lib.Scene;
import lib.SimpleApplication;

public class SceneGameOver extends Scene {
    public SceneGameOver(SimpleApplication simpleApplication) {
        super(simpleApplication);
    }

    @Override
    public void init() {
        SimpleApplication.faceEffect = 0.2f;
    }

    @Override
    public void update(double tpf) {

    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 800, 600);

        g2d.drawImage(AssetManager.getInstance().warga, 0, 0, 800, 600, null);

        g2d.fillRect(100, 100, 600, 400);

        g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        g2d.setColor(Color.WHITE);
        String s = "GAME OVER";
        int stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        g2d.drawString(s, (800 - stringLen) / 2, 300);

        g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        g2d.setColor(Color.WHITE);
        s = "SCORE : " + SceneGame.score;
        stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        g2d.drawString(s, (800 - stringLen) / 2, 330);

        g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        g2d.setColor(Color.WHITE);
        s = "PRESS ESCAPE TO BACK TO MENU";
        stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        g2d.drawString(s, (800 - stringLen) / 2, 360);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            SceneGame.totalFloor += 5;
            simpleApplication.changeScene(new SceneMenu(simpleApplication));
        }
    }
}
