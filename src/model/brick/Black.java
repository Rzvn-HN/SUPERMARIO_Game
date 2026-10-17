package model.brick;

import manager.GameEngine;
import model.prize.Prize;

import java.awt.image.BufferedImage;

public class Black extends Brick{

    public Black(double x, double y, BufferedImage style){
        super(x, y, style);
        setBreakable(false);
        setEmpty(true);
    }


}
