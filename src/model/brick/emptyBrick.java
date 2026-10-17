package model.brick;

import java.awt.image.BufferedImage;

public class emptyBrick extends Brick {

    public emptyBrick(double x, double y, BufferedImage style){
        super(x, y, style);
        setBreakable(false);
        setEmpty(true);
    }

}
