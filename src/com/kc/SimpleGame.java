package com.kc;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import lib.SimpleApplication;

public class SimpleGame extends SimpleApplication implements MouseListener,KeyListener,MouseMotionListener{
    public static JFrame jf;
    public static int FRAME_WIDTH = 0;
    public static int FRAME_HEIGHT = 0;
  //power font
  	private boolean zoom = true;
  	private float spaceCount = 0;
    
    public SimpleGame() {
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        jf = new JFrame();
        jf.setSize(800, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setIconImage(AssetManager.getInstance().wir_logo);
        jf.setTitle("FIX IT FELIX BY BLUEJACK KC13-0");
        //activeScene = new SceneTitle(this);
        activeScene = new SceneTitle(this);
        jf.add(this);
        setSize(800, 600);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        start();
        setFocusable(true);
    }

    @Override
    public void init() {
        activeScene.init();
    }

    @Override
    public void update(double tpf) {
    	FRAME_WIDTH = jf.getWidth();
    	FRAME_HEIGHT = jf.getHeight();
        activeScene.update(tpf);
        if(zoom)spaceCount += 50*tpf;
        else spaceCount -= 50*tpf;
        if(spaceCount>200)zoom = false;
        if(spaceCount<0)zoom = true;
        String s = "";for(int j=0;j<spaceCount;j++)s+=" ";
        s+= "FIX IT FELIX BY BLUEJACK KC13-0";
        jf.setTitle(s);
    }

    @Override
    public void render(Graphics2D g2d) {
        activeScene.render(g2d);
    }
    
    public void close(){
        stop();
        jf.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        activeScene.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        activeScene.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        activeScene.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        activeScene.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        activeScene.mouseExited(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        activeScene.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        activeScene.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        activeScene.keyReleased(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        activeScene.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        activeScene.mouseMoved(e);
    }
}
	