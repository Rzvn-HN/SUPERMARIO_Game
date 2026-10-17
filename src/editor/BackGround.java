package editor;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.Serializable;

class Background implements Serializable {
    private static final long serialVersionUID = -2401822583090769966L;
    public static Dimension screen = new Dimension(640, 480);
    protected double x;
    protected double y;
    private int width;
    private int height;
    private final Rectangle clip;
    private static Background backgr;

    public static Background getDefaultBackground() {
        if (backgr == null) {
           // backgr = new Background();
        }

        return backgr;
    }

    public Background(int w, int h) {
        this.x = this.y = 0.0;
        this.width = w;
        this.height = h;
        this.clip = new Rectangle(0, 0, screen.width, screen.height);
    }

    public Background() {
        this(screen.width, screen.height);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setSize(int w, int h) {
        this.width = w;
        this.height = h;
        this.setLocation(this.x, this.y);
    }

    public void setLocation(double xb, double yb) {
        if (xb > (double)(this.width - this.clip.width)) {
            xb = (double)(this.width - this.clip.width);
        }

        if (yb > (double)(this.height - this.clip.height)) {
            yb = (double)(this.height - this.clip.height);
        }

        if (xb < 0.0) {
            xb = 0.0;
        }

        if (yb < 0.0) {
            yb = 0.0;
        }

        this.x = xb;
        this.y = yb;
    }

    public void move(double dx, double dy) {
        this.setLocation(this.x + dx, this.y + dy);
    }

    public void setToCenter(int x, int y, int w, int h) {
        this.setLocation((double)(x + w / 2 - this.clip.width / 2), (double)(y + h / 2 - this.clip.height / 2));
    }

    public void setToCenter(Sprite centered) {
        this.setToCenter((int)centered.getX(), (int)centered.getY(), centered.getWidth(), centered.getHeight());
    }

    public void setClip(int x, int y, int width, int height) {
        this.clip.setBounds(x, y, width, height);
    }

    public void setClip(Rectangle r) {
        this.clip.setBounds(r);
    }

    public Rectangle getClip() {
        return this.clip;
    }

    public void update(long elapsedTime) {
    }

    public void render(Graphics2D g) {
        this.render(g, (int)this.x, (int)this.y, this.clip.x, this.clip.y, this.width < this.clip.width ? this.width : this.clip.width, this.height < this.clip.height ? this.height : this.clip.height);
    }

    public void render(Graphics2D g, int xbg, int ybg, int x, int y, int w, int h) {
    }
}

