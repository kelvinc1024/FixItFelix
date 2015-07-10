package com.kc;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.CannotRealizeException;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;


public class AssetManager {
    private static AssetManager assetManager;

    Player bgmPlayer;

    Image frame_medium;
    Image logo_big;
    Image instruction_brick;
    Image instruction_pie;
    Image wir_logo;
    Image brick;
    Image warga;
    Image pot;
    Vector<Image> vBtnLarge = new Vector<Image>();
    Vector<Image> vBird = new Vector<Image>();
    Vector<Image> vArrow = new Vector<Image>();
    Vector<Image> vFelix = new Vector<Image>();
    Vector<Image> vRalph = new Vector<Image>();
    Vector<Image> vBuilding = new Vector<Image>();
    Vector<Image> vWindowA = new Vector<Image>();
    Vector<Image> vWindowB = new Vector<Image>();
    Vector<Image> vAbout = new Vector<Image>();

    private AssetManager() {

        try {
            BufferedImage bf;
            logo_big = ImageIO.read(new File("assets/{spr}logo_big.png"));
            frame_medium = ImageIO.read(new File("assets/{spr}frame_medium.png"));
            instruction_brick = ImageIO.read(new File("assets/{spr}instructions_brick.png"));
            instruction_pie = ImageIO.read(new File("assets/{spr}instructions_pie.png"));
            wir_logo = ImageIO.read(new File("assets/{spr}WIR_logo.png"));
            brick = ImageIO.read(new File("assets/{spr}brick.png"));
            warga = ImageIO.read(new File("assets/60052.png"));
            pot = ImageIO.read(new File("assets/{spr}flower_pot.png"));

            bf = ImageIO.read(new File("assets/{btn}red_large.png"));
            for (int j = 0; j < 2; j++) {
                vBtnLarge.add(bf.getSubimage(j * bf.getWidth() / 2, 0, bf.getWidth() / 2, bf.getHeight()));
            }
            vBird.add(ImageIO.read(new File("assets/{spr}bird_left_down.png")));
            vBird.add(ImageIO.read(new File("assets/{spr}bird_left_up.png")));
            vBird.add(ImageIO.read(new File("assets/{spr}bird_right_down.png")));
            vBird.add(ImageIO.read(new File("assets/{spr}bird_right_up.png")));

            vArrow.add(ImageIO.read(new File("assets/{spr}ctrl_left.png")));
            vArrow.add(ImageIO.read(new File("assets/{spr}ctrl_right.png")));
            vArrow.add(ImageIO.read(new File("assets/{spr}ctrl_up.png")));
            vArrow.add(ImageIO.read(new File("assets/{spr}ctrl_down.png")));


            vFelix.add(ImageIO.read(new File("assets/{spr}felix_hammer_down.png")));
            vFelix.add(ImageIO.read(new File("assets/{spr}felix_hammer_up.png")));

            vRalph.add(ImageIO.read(new File("assets/{spr}ralph_arms_up.png")));
            vRalph.add(ImageIO.read(new File("assets/{spr}ralph_yell.png")));
            vRalph.add(ImageIO.read(new File("assets/{spr}ralph_idle.png")));
            vRalph.add(ImageIO.read(new File("assets/{spr}ralph_attack_left.png")));
            vRalph.add(ImageIO.read(new File("assets/{spr}ralph_attack_right.png")));
            vRalph.add(ImageIO.read(new File("assets/{spr}ralph_climb1.png")));
            vRalph.add(ImageIO.read(new File("assets/{spr}ralph_climb2.png")));
            vRalph.add(ImageIO.read(new File("assets/{spr}ralph_walk_left1.png")));
            vRalph.add(ImageIO.read(new File("assets/{spr}ralph_walk_left2.png")));
            vRalph.add(ImageIO.read(new File("assets/{spr}ralph_walk_right1.png")));
            vRalph.add(ImageIO.read(new File("assets/{spr}ralph_walk_right2.png")));

            vBuilding.add(ImageIO.read(new File("assets/{spr}building_brick.png")));
            vBuilding.add(ImageIO.read(new File("assets/{spr}building_cement_bottom.png")));
            vBuilding.add(ImageIO.read(new File("assets/{spr}building_cement_top.png")));
            vBuilding.add(ImageIO.read(new File("assets/{spr}building_ledge.png")));

            vWindowA.add(ImageIO.read(new File("assets/{spr}windowA_bottom_open.png")));
            vWindowA.add(ImageIO.read(new File("assets/{spr}windowA_broken_both.png")));
            vWindowA.add(ImageIO.read(new File("assets/{spr}windowA_broken_top.png")));
            vWindowA.add(ImageIO.read(new File("assets/{spr}windowA_repaired.png")));
            vWindowA.add(ImageIO.read(new File("assets/{spr}windowA_broken_bottom.png")));
            vWindowA.add(ImageIO.read(new File("assets/{spr}windowA_closed.png")));

            vWindowB.add(ImageIO.read(new File("assets/{spr}windowB_bottom_open.png")));
            vWindowB.add(ImageIO.read(new File("assets/{spr}windowB_broken_both.png")));
            vWindowB.add(ImageIO.read(new File("assets/{spr}windowB_broken_top.png")));
            vWindowB.add(ImageIO.read(new File("assets/{spr}windowB_repaired.png")));
            vWindowB.add(ImageIO.read(new File("assets/{spr}windowB_broken_bottom.png")));
            vWindowB.add(ImageIO.read(new File("assets/{spr}windowA_closed.png")));
        } catch (Exception e) {
            System.out.println("Asset Not Found");
        }

    }

    public static AssetManager getInstance() {
        if (assetManager == null)
            assetManager = new AssetManager();
        return assetManager;
    }

    //Player bgmPlayer;
    public void playBGM(File bgm) {
        try {
            bgmPlayer = Manager.createRealizedPlayer(bgm.toURI().toURL());
            bgmPlayer.start();
            ControllerListener cl = new ControllerListener() {

				@Override
				public void controllerUpdate(ControllerEvent arg0) {
					System.out.println("asdfasdfadfasfd");
					bgmPlayer.setMediaTime(new Time(0));
				}

            };

            bgmPlayer.addControllerListener(cl);

        } catch (IOException ex) {
        	System.out.println("asdf");
            Logger.getLogger(AssetManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoPlayerException ex) {
        	System.out.println("adsf");
            Logger.getLogger(AssetManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotRealizeException ex) {
        	System.out.println("asdf");
            Logger.getLogger(AssetManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Line.Info lineInfo = new Line.Info(Clip.class);
            Line l = AudioSystem.getLine(lineInfo);
            Clip c = (Clip) l;
            AudioInputStream ais = AudioSystem.getAudioInputStream(bgm);
            c.open(ais);
            c.start();
            c.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
