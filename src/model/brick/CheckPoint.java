package model.brick;

import java.awt.image.BufferedImage;

public class CheckPoint extends Brick {

    public CheckPoint(double x, double y, BufferedImage style){
        super(x, y, style);
        setBreakable(false);
        setEmpty(true);
    }

}
