package lib;

import java.awt.Graphics2D;

public interface IGameBehavior {
    public void init();

    public void update(double tpf);

    public void render(Graphics2D g2d);
}