package com.kc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import lib.Scene;
import lib.SimpleApplication;

public class SceneInfo extends Scene {

    public SceneInfo(SimpleApplication simpleApplication) {
        super(simpleApplication);
    }

    @Override
    public void init() {
        SimpleApplication.faceEffect = 0.08f;
    }

    @Override
    public void update(double tpf) {

    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 800, 600);

        int width = AssetManager.getInstance().frame_medium.getWidth(null);
        int height = AssetManager.getInstance().frame_medium.getHeight(null);
        g2d.drawImage(AssetManager.getInstance().frame_medium, (800 - width) / 2, (600 - height) / 2, null);

        g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        g2d.setColor(Color.CYAN);
        String s = "INSTRUCTION";
        int stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        g2d.drawString(s, (800 - stringLen) / 2, 80);

        g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        g2d.setColor(Color.WHITE);
        s = "FIX ALL THE BROKEN WINDOWS ON EACH FLOOR TO";
        stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        g2d.drawString(s, (800 - stringLen) / 2, 160);
        s = "ADVANCE TO THE NEXT LEVEL";
        stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        g2d.drawString(s, (800 - stringLen) / 2, 200);

        s = "AVOID";
        stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        g2d.drawString(s, (800 - stringLen) / 2, 240);

        s = "MOVE";
        stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        g2d.drawString(s, (800 - stringLen) / 2, 330);

        s = "FIX";
        stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        g2d.drawString(s, (800 - stringLen) / 2, 410);


        Image t = AssetManager.getInstance().instruction_brick;
        g2d.drawImage(t, 390, 260, t.getWidth(null) / 2, t.getHeight(null) / 2, null);

        for (int i = 0; i < AssetManager.getInstance().vArrow.size(); i++) {
            t = AssetManager.getInstance().vArrow.get(i);
            g2d.drawImage(t, 320 + t.getWidth(null) / 2 * i, 340, t.getWidth(null) / 2, t.getHeight(null) / 2, null);
        }

        t = AssetManager.getInstance().vBtnLarge.get(0);
        g2d.drawImage(t, 352, 420, t.getWidth(null) / 5, t.getHeight(null) / 5, null);
        s = "space";
        stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        g2d.drawString(s, (800 - stringLen) / 2, 450);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            simpleApplication.changeScene(new SceneMenu(simpleApplication));
        }
    }
}
