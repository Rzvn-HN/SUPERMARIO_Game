package model.hero;

import model.GameObject;

import java.awt.image.BufferedImage;

public class Hammer extends GameObject {

    public Hammer(double x, double y, BufferedImage style, boolean toRight) {
        super(x, y, style);
        setDimension(1, 1);
        setFalling(false);
        setJumping(false);
        setVelX(3);

        if(!toRight)
            setVelX(3);
    }

}
