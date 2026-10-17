package model.hero;

import view.Animation;
import view.ImageLoader;

import java.awt.image.BufferedImage;

public class MarioForm {

    public static final int SMALL = 0, SUPER = 1, FIRE = 2;

    private Animation animation;
    private boolean isSuper, isFire ;

    public boolean isHammer=true;
    private BufferedImage fireballStyle , hammerStyle;

    public MarioForm(Animation animation, boolean isSuper, boolean isFire, boolean isHammer){
        this.animation = animation;
        this.isSuper= isSuper;
        this.isFire = isFire;
        this.isHammer = isHammer;

        ImageLoader imageLoader = new ImageLoader();
        BufferedImage fireball = imageLoader.loadImage("/sprite.png");
        BufferedImage hammer = imageLoader.loadImage("/Hammer.png");

        fireballStyle = imageLoader.getSubImage(fireball, 3, 4, 24, 24);
        hammerStyle = hammer.getSubimage( 0, 0, 96, 96);
    }

    public BufferedImage getCurrentStyle(boolean toRight, boolean movingInX, boolean movingInY,boolean down){

        BufferedImage style;

        if(movingInY && toRight){
            style = animation.getRightFrames()[0];
        }
        else if(movingInY){
            style = animation.getLeftFrames()[0];
        }
        else if(down){
            style = animation.getLeftFrames()[5];

        }
        else if(movingInX){
            style = animation.animate(5, toRight);
        }
        else {
            if(toRight){
                style = animation.getRightFrames()[1];
            }
            else
                style = animation.getLeftFrames()[1];
        }

        return style;
    }
    int is = -1;
    public MarioForm onTouchEnemy(ImageLoader imageLoader) {

        if (isFire()==true){is=1;}
        else {is=0;}

        BufferedImage[] leftFrames = imageLoader.getLeftFrames(is);
        BufferedImage[] rightFrames= imageLoader.getRightFrames(is);

        Animation newAnimation = new Animation(leftFrames, rightFrames);

        return new MarioForm(newAnimation, false,  false,isHammer());
    }



    public Fireball fire(boolean toRight, double x, double y) {
        if(isFire){
            return new Fireball(x, y + 48, fireballStyle, toRight);
        }
        return null;
    }



    public Hammer hammer(boolean toRight, double x, double y) {
        if(isHammer){
            return new Hammer(x, y , hammerStyle, toRight);
        }
        return null;
    }

    public boolean isSuper() {
        return isSuper;
    }


    public void setSuper(boolean aSuper) {
        isSuper = aSuper;
    }

    public boolean isFire() {
        return isFire;
    }

    public boolean isHammer() {
        return isHammer;
    }

    public void setHammer(boolean aHammer) {
        isHammer = aHammer;
    }

}
