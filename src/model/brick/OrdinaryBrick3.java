package model.brick;

import manager.GameEngine;
import model.prize.Coin;
import model.prize.Prize;
import view.Animation;
import view.ImageLoader;

import java.awt.image.BufferedImage;

public class OrdinaryBrick3 extends OrdinaryBrick{




    private Animation animation;
    private boolean breaking;
    private int frames;

    private int ordinaryHit;

    private Coin coin;

    public OrdinaryBrick3 (double x, double y, BufferedImage style, Coin coin){
        super(x, y, style);


            if (ordinaryHit <=5) {
                setBreakable(false);
                setEmpty(false);
                breaking = false;
            } else if (ordinaryHit > 5) {
                setBreakable(true);
                setEmpty(true);
                setAnimation();
                breaking = true;
                frames = animation.getLeftFrames().length;
            }



        this.coin = coin ;



    }

    private void setAnimation(){
        ImageLoader imageLoader = new ImageLoader();
        BufferedImage[] leftFrames = imageLoader.getBrickFrames();

        animation = new Animation(leftFrames, leftFrames);
    }

    @Override
    public Coin reveal(GameEngine engine){



        if(coin != null){
            coin.reveal();
        }

        setEmpty(true);
        if(ordinaryHit<5){ordinaryHit++;}

        Coin toReturn = this.coin;
        this.coin = null;
        return toReturn;


    }


    public int getFrames(){
        return frames;
    }

    @Override
    public int getOrdinaryHit(){
        return ordinaryHit;
    }

    public void animate(){
        if(breaking){
            setStyle(animation.animate(3, true));
            frames--;
        }
    }


    @Override
    public Prize getPrize(){
        return coin;
    }



}
