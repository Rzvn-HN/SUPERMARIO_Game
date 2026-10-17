package model.enemy;


import java.awt.*;
import java.awt.image.BufferedImage;

public class CannibalFlower extends Enemy{

    private BufferedImage upImage;

    public CannibalFlower(double x, double y, BufferedImage style) {
        super(x, y, style);
        setVelY(1);
    }

    @Override
    public void draw(Graphics g){
        if(getVelY() > 0){
            g.drawImage(upImage, (int)getX(), (int)getY(), null);
        }
        else
            super.draw(g);
    }

    public void setUpImage(BufferedImage upImage) {
        this.upImage = upImage;
    }

    @Override
    public void updateLocation() {

        setFalling(false);
        setY(getY() + 1);

            //super.updateLocation();

    }
}
