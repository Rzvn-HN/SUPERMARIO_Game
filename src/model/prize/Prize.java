package model.prize;

import manager.GameEngine;
import manager.MapManager;
import model.hero.Mario;

import java.awt.*;

public interface Prize {

    int getPoint();

    int getCoin();

    void reveal();

    Rectangle getBounds();

    void onTouch(Mario mario, GameEngine engine);

}
