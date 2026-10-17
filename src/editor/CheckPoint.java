package editor;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.awt.Point;

class CheckPoints extends Sprite implements BasicCheckPoints {
    public int NextLevel;
    public Point NextLocation;

    public CheckPoints(double Mario_X_Location_when_he_is_dead, double Mario_Y_Location_when_he_is_dead, int Level_Number, Point MarioStartingLocation) {
        super(Mario_X_Location_when_he_is_dead, Mario_Y_Location_when_he_is_dead);
        this.NextLevel = Level_Number;
        this.NextLocation = MarioStartingLocation;
        this.setID(23);
    }

    public void update(long elapsedTime) {
        super.update(elapsedTime);
    }

    public int GetNextLevel() {
        return this.NextLevel;
    }

    public Point GetNextLocation() {
        return this.NextLocation;
    }
}

