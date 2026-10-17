package model.prize;

import manager.GameEngine;
import model.hero.Mario;
import model.hero.MarioForm;
import view.Animation;
import view.ImageLoader;

import java.awt.image.BufferedImage;

public class Star extends BoostItem{

    public Star(double x, double y, BufferedImage style) {
        super(x, y, style);
        setPoint(200);
    }

//TODO
    @Override
    public int getCoin(){
        return 0;
    }

    @Override
    public void onTouch(Mario mario, GameEngine engine) {
        mario.acquirePoints(getPoint());
        mario.setRemainingLives(mario.getRemainingLives() + 1);
        engine.playOneUp();


        mario.acquirePoints(getPoint());

        ImageLoader imageLoader = new ImageLoader();

        if(!mario.getMarioForm().isSuper() && !mario.getMarioForm().isFire()){
            BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SUPER);
            BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.SUPER);

            Animation animation = new Animation(leftFrames, rightFrames);

            MarioForm newForm = new MarioForm(animation, true, false,mario.isHammer());
            mario.setMarioForm(newForm);
            mario.setDimension(48, 96);

            engine.playSuperMushroom();
        }

        else if(mario.getMarioForm().isSuper() || mario.getMarioForm().isFire() ){
            BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.FIRE);
            BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.FIRE);

            Animation animation = new Animation(leftFrames, rightFrames);

            MarioForm newForm = new MarioForm(animation, false, true,mario.isHammer());
            mario.setMarioForm(newForm);
            mario.setDimension(48, 96);

            engine.playFireFlower();
        }
    }



}
