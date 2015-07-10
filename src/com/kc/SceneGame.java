package com.kc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Vector;

import lib.Scene;
import lib.SimpleApplication;

public class SceneGame extends Scene {

    Felix felix;
    Ralph ralph;
    Vector<Floor> vFloors;
    Vector<Brick> vBrick = new Vector<Brick>();
    float translate = 0;
    float targetTranslate = 0;

    public static Vector<Point> gridPoint;
    public static int totalFloor = 10;
    public static GameState currGameState;
    public static int score = 0;

    public SceneGame(SimpleApplication simpleApplication) {
        super(simpleApplication);

        gridPoint = new Vector<Point>();
        gridPoint.add(new Point(250, 0));
        gridPoint.add(new Point(310, 0));
        gridPoint.add(new Point(375, 0));
        gridPoint.add(new Point(440, 0));
        gridPoint.add(new Point(500, 0));

        vFloors = new Vector<Floor>();
        for (int i = 0; i < totalFloor; i++) {
            if (i == 0)
                vFloors.add(new Floor((800 - 404) / 2, 490 - (56 * 2) * i, 202 * 2, 56 * 2, AssetManager.getInstance().vBuilding.get(1), null, 0));
            else if ((i) % 5 <= 1)
                vFloors.add(new Floor((800 - 404) / 2, 490 - (56 * 2) * i, 202 * 2, 56 * 2, AssetManager.getInstance().vBuilding.get(2), null, 0));
            else {
                if ((i + 1) % 5 != 0)
                    vFloors.add(new Floor((800 - 404) / 2, 490 - (56 * 2) * i, 202 * 2, 56 * 2, AssetManager.getInstance().vBuilding.get(0), null, 0));
                else
                    vFloors.add(new Floor((800 - 404) / 2, 490 - (56 * 2) * i, 202 * 2, 56 * 2, AssetManager.getInstance().vBuilding.get(0), null, 1));
            }
        }

        felix = new Felix(simpleApplication, 350, 500, 62, 82, vFloors);
        ralph = new Ralph(0, 0, 66 * 1.2f, 72 * 1.2f, vBrick, vFloors);
    }

    @Override
    public void init() {
        currGameState = GameState.IntroCinematicState;
        SimpleApplication.faceEffect = 0.08f;
        for (Floor f : vFloors) {
            f.init();
        }
        felix.init();
        ralph.init();
        for (Brick b : vBrick) {
            b.init();
        }
    }

    @Override
    public void update(double tpf) {
        if (currGameState == GameState.IntroCinematicState && ralph.ralphState == RalphState.Play) {
            currGameState = GameState.PlayState;
            SimpleApplication.faceEffect = 1;
        }
        for (Floor f : vFloors) {
            f.update(tpf);
        }
        ralph.update(tpf);
        felix.update(tpf);
        for (Brick b : vBrick) {
            b.update(tpf);
        }

        for (int i = 0; i < vBrick.size(); i++) {
            if (vBrick.get(i).getRect().intersects(felix.getRect())) {

                simpleApplication.changeScene(new SceneGameOver(simpleApplication));
            }
        }

        targetTranslate = (felix.yPos / 5) * (56 * 2) * 5;
        if (Math.abs(targetTranslate - translate) > 2) {
            currGameState = GameState.TransitionState;
            SimpleApplication.faceEffect = 0.08f;
            if (targetTranslate > translate)
                translate += 300 * tpf;
            else if (targetTranslate < translate)
                translate -= 300 * tpf;
        } else if (currGameState == GameState.TransitionState) {
            currGameState = GameState.PlayState;
            SimpleApplication.faceEffect = 1f;
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 800, 600);
        g2d.translate(0, translate);
        for (Floor f : vFloors) {
            f.render(g2d);
        }
        g2d.drawImage(AssetManager.getInstance().vBuilding.get(3), (800 - 404) / 2, 462 - (56 * 2) * (totalFloor - 1), 202 * 2, 28, null);
        for (int i = 0; i < 10; i++)
            if (i < 2 || i > 6)
                g2d.drawImage(AssetManager.getInstance().pot, 86 * i, 570, 86, 30, null);
        ralph.render(g2d);
        felix.render(g2d);
        //g2d.setColor(Color.red);
        //g2d.fillRect((int)felix.x, (int)felix.y, (int)felix.width, (int)felix.height);
        for (Brick b : vBrick) {
            b.render(g2d);
            //g2d.setColor(Color.red);
            //g2d.fillRect((int)b.x, (int)b.y, (int)b.width, (int)b.height);
        }
        g2d.translate(0, -translate);
        g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Score : " + score, 50, 100);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        felix.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            simpleApplication.changeScene(new SceneMenu(simpleApplication));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        felix.keyReleased(e);
    }
}
