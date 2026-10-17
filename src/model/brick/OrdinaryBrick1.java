package model.brick;

import manager.GameEngine;
import manager.MapManager;
import model.prize.Coin;
import view.Animation;
import view.ImageLoader;

import java.awt.image.BufferedImage;

public class OrdinaryBrick1 extends OrdinaryBrick {

    private Animation animation;
    private boolean breaking;
    private int frames;

    private int ordinaryHit=0;

    private Coin coin;

    public OrdinaryBrick1(double x, double y, BufferedImage style, Coin coin) {
        super(x, y, style);

            setBreakable(true);
            setEmpty(true);



        this.coin = coin;

        setAnimation();
        breaking = true;
        frames = animation.getLeftFrames().length;
    }

    private void setAnimation() {
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage[] leftFrames = imageLoader.getBrickFrames();

        animation = new Animation(leftFrames, leftFrames);
    }

    @Override
    public Coin reveal(GameEngine engine) {
        MapManager manager = engine.getMapManager();

        breaking = true;
        manager.addRevealedBrick(this);

        double newX = getX() - 27, newY = getY() - 27;
        setLocation(newX, newY);


        return null;
    }

    @Override
    public int getFrames() {
        return frames;
    }


    @Override
    public int getOrdinaryHit(){
        return ordinaryHit;
    }


    @Override
    public void animate() {
        if (breaking) {
            setStyle(animation.animate(3, true));
            frames--;
        }
    }


}







