package com.kc;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        new SimpleGame();
        AssetManager.getInstance().playBGM(new File("assets/musik_kece.wav"));
    }
}
