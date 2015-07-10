package com.kc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Vector;

import lib.Scene;
import lib.SimpleApplication;

public class SceneTitle extends Scene {

    boolean spaceAnimation = false;
    float countDown = .5f;
    //power font
    boolean zoom = true;
    float spaceCount = 0;
    Vector<RedDot> vRedDot = new Vector<RedDot>();
    Random rand = new Random();

    public SceneTitle(SimpleApplication simpleApplication) {
        super(simpleApplication);
        for (int i = 0; i < 10; i++)
            vRedDot.add(new RedDot(rand.nextInt(800), rand.nextInt(600)));
    }

    @Override
    public void init() {
        SimpleApplication.faceEffect = 0.08f;
    }

    @Override
    public void update(double tpf) {
        countDown -= tpf;
        if (countDown <= 0) {
            countDown = .5f;
            spaceAnimation = !spaceAnimation;
        }

        if (zoom) spaceCount += 2 * tpf;
        if (!zoom) spaceCount -= 2 * tpf;

        if (spaceCount > 8) zoom = false;
        if (spaceCount < 2) zoom = true;

        for (RedDot r : vRedDot)
            r.update(tpf);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 800, 600);

        for (RedDot r : vRedDot)
            r.render(g2d);
        /*
		int width = AssetManager.getInstance().logo_big.getWidth(null);
		int height = AssetManager.getInstance().logo_big.getHeight(null);
		g2d.drawImage(AssetManager.getInstance().logo_big, (800-width)/2, 100, width, height, null);
		*/
        g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        if (spaceAnimation) g2d.setColor(Color.YELLOW);
        else g2d.setColor(Color.WHITE);
        String s = "PRESS SPACE BAR";
        int stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        g2d.drawString(s, (800 - stringLen) / 2, 360);

        int width = AssetManager.getInstance().logo_big.getWidth(null);
        int height = AssetManager.getInstance().logo_big.getHeight(null);
        g2d.drawImage(AssetManager.getInstance().logo_big, (800 - width) / 2, 100, width, height, null);


        g2d.setColor(Color.RED);
        s = "BY BLUEJACK KC13-0";
        stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        g2d.drawString(s, (800 - stringLen) / 2, 520);
        g2d.setColor(Color.RED);
        s = "P";
        for (int i = 0; i < spaceCount; i++) s += " ";
        s += "O";
        for (int i = 0; i < spaceCount; i++) s += " ";
        s += "W";
        for (int i = 0; i < spaceCount; i++) s += " ";
        s += "E";
        for (int i = 0; i < spaceCount; i++) s += " ";
        s += "R";
        stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        g2d.drawString(s, (800 - stringLen) / 2, 550);
        s = "PROVE OUR WILL EMERGE OUR SPIRIT";
        stringLen = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        g2d.drawString(s, (800 - stringLen) / 2, 580);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            simpleApplication.changeScene(new SceneMenu(simpleApplication));
        }
    }
}
