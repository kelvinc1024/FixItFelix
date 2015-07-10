package lib;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public abstract class SimpleApplication extends JPanel implements IGameBehavior {
    private boolean isRunning = true;
    private boolean isFirst = true;

    public final static int CANVAS_WIDTH = 800;
    public final static int CANVAS_HEIGTH = 600;
    public static float faceEffect = 0.08f;

    Thread th = new Thread(new Runnable() {
        @Override
        public void run() {
            long lastTime = System.nanoTime();
            long currTime = System.nanoTime();
            double tpf = 0;

            while (isRunning) {
                if (isFirst) {
                    isFirst = false;
                    init();
                }
                lastTime = System.nanoTime();
                update(tpf);
                BufferedImage b = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGTH, BufferedImage.TYPE_INT_ARGB);
                render((Graphics2D) b.getGraphics());
                repaint();
                r = b.getSubimage(0, 0, CANVAS_WIDTH, CANVAS_HEIGTH);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                }
                currTime = System.nanoTime();
                tpf = (currTime - lastTime) / 1000000000f;
                if (isChangeScene) {
                    mChangeScene(futureScene);
                }
            }
        }
    });

    BufferedImage r = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, faceEffect));
        g2d.drawImage(r, 0, 0, getWidth(), getHeight(), this);
        /*
        int w = 800, h = 600;
        int length = ((w + 7) * h) / 8;
        byte[] data;
        data = new byte[length];
        DataBuffer db = new DataBufferByte(data, length);
        WritableRaster wr = Raster.createPackedRaster(db, w, h, 1, null);
        ColorModel cm = new IndexColorModel(1, 2, new byte[] { (byte) 0, (byte) 255 }, new byte[] {
            (byte) 0, (byte) 255 }, new byte[] { (byte) 0, (byte) 255 });
        BufferedImage image = new BufferedImage(cm, wr, false, null);
        Random random = new Random();
        g.drawImage(image, 0, 0, this);
        */
        //render((Graphics2D)g);
    }

    public void start() {
        th.start();
    }

    public void stop() {
        boolean isRetry = true;
        isRunning = false;
        while (isRetry) {
            try {
                th.join();
                isRetry = false;
            } catch (Exception e) {
            }
        }
    }

    public Scene activeScene;
    private boolean isChangeScene;
    private Scene futureScene;

    private void mChangeScene(Scene scene) {
        activeScene = futureScene;
        isFirst = true;
        isChangeScene = false;
    }

    public void changeScene(Scene scene) {
        isChangeScene = true;
        futureScene = scene;
    }
}

