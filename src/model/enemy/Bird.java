package model.enemy;

import model.Map;
import view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bird extends Enemy{

    private BufferedImage rightImage;


    public Bird(double x, double y, BufferedImage style) {
        super(x, y, style);
        setVelX(3);
    }

    @Override
    public void draw(Graphics g){
        if(getVelX() > 0){
            g.drawImage(rightImage, (int)getX(), (int)getY(), null);

        }
        else
            super.draw(g);

    }


    public void setRightImage(BufferedImage rightImage) {
        this.rightImage = rightImage;
    }


    @Override
    public void updateLocation() {

        setFalling(false);
        setX(getX() - 3);


      //  super.updateLocation();

    }

}
