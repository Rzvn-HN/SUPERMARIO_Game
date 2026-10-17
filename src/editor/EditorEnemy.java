package editor;

import java.util.ArrayList;

public class EditorEnemy {

    private int enemyX;
    private int enemyY;
    private EnemyType enemyType;

    public int getEnemyX() {
        return enemyX;
    }

    public void setEnemyX(int enemyX) {
        this.enemyX = enemyX;
    }

    public int getEnemyY() {
        return enemyY;
    }

    public void setEnemyY(int enemyY) {
        this.enemyY = enemyY;
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(EnemyType enemyType) {
        this.enemyType = enemyType;
    }


    EditorEnemy(){}


    @Override
    public String toString() {
        return "EnemyObject{" +
                "x='" + enemyX +
                "y='" + enemyY +
                "type='" + enemyType +
                '}';
    }
}
