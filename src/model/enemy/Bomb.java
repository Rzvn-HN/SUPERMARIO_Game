package model.enemy;

import model.Map;
import view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bomb extends Enemy{

    private BufferedImage downImage;



   // bomb = bomb.getSubimage( 0, 0, 32, 32);

    public Bomb(double x, double y, BufferedImage style) {
        super(x, y, style);
        setVelY(1);




    }

    @Override
    public void draw(Graphics g){
        if(getVelX() > 0){
            g.drawImage(downImage, (int)getX(), (int)getY(), null);
        }
        else
            super.draw(g);
    }

    public void setRightImage(BufferedImage downImage) {
        this.downImage = downImage;
    }


}
