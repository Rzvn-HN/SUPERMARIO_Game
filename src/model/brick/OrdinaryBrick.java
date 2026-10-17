package model.brick;

import view.Animation;

import java.awt.image.BufferedImage;

public  class OrdinaryBrick extends Brick {

    private Animation animation;
    private boolean breaking;
    private int frames;
    private int ordinaryHit;

    public OrdinaryBrick(double x, double y, BufferedImage style){
        super(x, y, style);


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

    }

}
