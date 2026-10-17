package editor;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.Serializable;

public class Sprite implements Serializable {
    private static final long serialVersionUID = -4499098097309229784L;
    private Background background;
    private double x;
    private double y;
    private double horizontalSpeed;
    private double verticalSpeed;
    private double oldX;
    private double oldY;
    private static double screenX;
    private static double screenY;
    private transient BufferedImage image;
    protected int width;
    protected int height;
   // protected CollisionShape defaultCollisionShape;
    private int id;
    private Object dataID;
    private int layer;
    private boolean active;
    private boolean immutable;

    public Sprite(BufferedImage image, double x, double y) {
        //this.defaultCollisionShape = null;
        this.active = true;
        this.x = this.oldX = x;
        this.y = this.oldY = y;
        if (image != null) {
            this.image = image;
            this.width = image.getWidth();
            this.height = image.getHeight();
        }

        this.background = Background.getDefaultBackground();
    }

    public Sprite(BufferedImage image) {
        this(image, 0.0, 0.0);
    }

    public Sprite(double x, double y) {
        this((BufferedImage)null, x, y);
    }

    public Sprite() {
        this(0.0, 0.0);
    }

    public void setBackground(Background backgr) {
        this.background = backgr;
        if (this.background == null) {
            this.background = Background.getDefaultBackground();
        }

    }

    public Background getBackground() {
        return this.background;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        this.width = this.height = 0;
        if (image != null) {
            this.width = image.getWidth();
            this.height = image.getHeight();
        }

    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    /*
    public  getDefaultCollisionShape() {
        if (this.defaultCollisionShape == null) {
            this.defaultCollisionShape = new CollisionRect();
        }

        this.defaultCollisionShape.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        return this.defaultCollisionShape;
    }*/

    public boolean moveTo(long elapsedTime, double xs, double ys, double speed) {
        if (this.x == xs && this.y == ys) {
            return true;
        } else {
            double angle = 90.0 + Math.toDegrees(Math.atan2(ys - this.y, xs - this.x));
            double radians = Math.toRadians(angle);
            double vx = Math.sin(radians) * speed * (double)elapsedTime;
            double vy = -Math.cos(radians) * speed * (double)elapsedTime;
            boolean arriveX = false;
            boolean arriveY = false;
            if (vx != 0.0) {
                if (vx > 0.0) {
                    if (this.x + vx >= xs) {
                        vx = xs - this.x;
                        arriveX = true;
                    }
                } else if (this.x + vx <= xs) {
                    vx = xs - this.x;
                    arriveX = true;
                }
            } else if (this.x == xs) {
                arriveX = true;
            }

            if (vy != 0.0) {
                if (vy > 0.0) {
                    if (this.y + vy >= ys) {
                        vy = ys - this.y;
                        arriveY = true;
                    }
                } else if (this.y + vy <= ys) {
                    vy = ys - this.y;
                    arriveY = true;
                }
            } else if (this.y == ys) {
                arriveY = true;
            }

            this.move(vx, vy);
            return arriveX && arriveY;
        }
    }

    public void setLocation(double xs, double ys) {
        this.oldX = this.x = xs;
        this.oldY = this.y = ys;
    }

    public void move(double dx, double dy) {
        if (dx != 0.0 || dy != 0.0) {
            this.oldX = this.x;
            this.x += dx;
            this.oldY = this.y;
            this.y += dy;
        }

    }

    public void moveX(double dx) {
        if (dx != 0.0) {
            this.oldX = this.x;
            this.x += dx;
        }

    }

    public void moveY(double dy) {
        if (dy != 0.0) {
            this.oldY = this.y;
            this.y += dy;
        }

    }

    public void setX(double xs) {
        this.oldX = this.x = xs;
    }

    public void setY(double ys) {
        this.oldY = this.y = ys;
    }

    public void forceX(double xs) {
        this.x = xs;
    }

    public void forceY(double ys) {
        this.y = ys;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getOldX() {
        return this.oldX;
    }

    public double getOldY() {
        return this.oldY;
    }

    public void setSpeed(double vx, double vy) {
        this.horizontalSpeed = vx;
        this.verticalSpeed = vy;
    }

    public void setHorizontalSpeed(double vx) {
        this.horizontalSpeed = vx;
    }

    public void setVerticalSpeed(double vy) {
        this.verticalSpeed = vy;
    }

    public void setMovement(double speed, double angleDir) {
        double radians = Math.toRadians(angleDir);
        this.setSpeed(Math.sin(radians) * speed, -Math.cos(radians) * speed);
    }

    public void addHorizontalSpeed(long elapsedTime, double accel, double maxSpeed) {
        if (accel != 0.0 && elapsedTime != 0L) {
            this.horizontalSpeed += accel * (double)elapsedTime;
            if (accel < 0.0) {
                if (this.horizontalSpeed < maxSpeed) {
                    this.horizontalSpeed = maxSpeed;
                }
            } else if (this.horizontalSpeed > maxSpeed) {
                this.horizontalSpeed = maxSpeed;
            }

        }
    }

    public void addVerticalSpeed(long elapsedTime, double accel, double maxSpeed) {
        if (accel != 0.0 && elapsedTime != 0L) {
            this.verticalSpeed += accel * (double)elapsedTime;
            if (accel < 0.0) {
                if (this.verticalSpeed < maxSpeed) {
                    this.verticalSpeed = maxSpeed;
                }
            } else if (this.verticalSpeed > maxSpeed) {
                this.verticalSpeed = maxSpeed;
            }

        }
    }

    public double getHorizontalSpeed() {
        return this.horizontalSpeed;
    }

    public double getVerticalSpeed() {
        return this.verticalSpeed;
    }

    public double getScreenX() {
        return this.x - this.background.getX() + (double)this.background.getClip().x;
    }

    public double getScreenY() {
        return this.y - this.background.getY() + (double)this.background.getClip().y;
    }

    public double getCenterX() {
        return this.x + (double)(this.width / 2);
    }

    public double getCenterY() {
        return this.y + (double)(this.height / 2);
    }

    public boolean isOnScreen(int leftOffset, int topOffset, int rightOffset, int bottomOffset) {
        screenX = this.x - this.background.getX();
        screenY = this.y - this.background.getY();
        return screenX + (double)this.width > (double)(-leftOffset) && screenY + (double)this.height > (double)(-topOffset) && screenX < (double)(this.background.getClip().width + rightOffset) && screenY < (double)(this.background.getClip().height + bottomOffset);
    }

    public boolean isOnScreen() {
        return this.isOnScreen(0, 0, 0, 0);
    }

    public void update(long elapsedTime) {
        this.updateMovement(elapsedTime);
    }

    protected void updateMovement(long elapsedTime) {
        this.move(this.horizontalSpeed * (double)elapsedTime, this.verticalSpeed * (double)elapsedTime);
    }

    public void render(Graphics2D g) {
        screenX = this.x - this.background.getX();
        screenY = this.y - this.background.getY();
        if (!(screenX + (double)this.width <= 0.0) && !(screenY + (double)this.height <= 0.0) && !(screenX > (double)this.background.getClip().width) && !(screenY > (double)this.background.getClip().height)) {
            screenX += (double)this.background.getClip().x;
            screenY += (double)this.background.getClip().y;
            this.render(g, (int)screenX, (int)screenY);
        }
    }

    public void render(Graphics2D g, int x, int y) {
        g.drawImage(this.image, x, y, (ImageObserver)null);
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public Object getDataID() {
        return this.dataID;
    }

    public void setDataID(Object dataID) {
        this.dataID = dataID;
    }

    public int getLayer() {
        return this.layer;
    }

    public void setLayer(int i) {
        this.layer = i;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean b) {
        this.active = b;
    }

    public boolean isImmutable() {
        return this.immutable;
    }

    public void setImmutable(boolean b) {
        this.immutable = true;
    }

    public double getDistance(Sprite other) {
        return Math.sqrt(Math.pow(this.getCenterX() - other.getCenterX(), 2.0) + Math.pow(this.getCenterY() - other.getCenterY(), 2.0));
    }
}
