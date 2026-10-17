package model.enemy;

import model.GameObject;
import model.Map;

import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class Enemy extends GameObject{

    public Enemy(double x, double y, BufferedImage style) {
        super(x, y, style);
        setFalling(false);
        setJumping(false);
    }


}
