package model.brick;

import model.enemy.Enemy;

import java.awt.image.BufferedImage;

public class WhitePipe extends Brick {
    public WhitePipe(double x, double y, BufferedImage style){
        super(x, y, style);
        setBreakable(false);
        setEmpty(true);
        setDimension(96, 96);
    }
}
