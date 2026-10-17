package editor;

import java.util.ArrayList;

public class Section {

    private int sectionLength;
    private int sectionTime;
    private ArrayList<EditorEnemy> enemies = new ArrayList<>();
    private ArrayList<EditorPipe> pipes = new ArrayList<>();
    private ArrayList<EditorBrick> bricks = new ArrayList<>();

    public int getSectionLength() {
        return sectionLength;
    }

    public void setSectionLength(int sectionLength) {
        this.sectionLength = sectionLength;
    }

    public int getSectionTime() {
        return sectionTime;
    }

    public void setSectionTime(int sectionTime) {
        this.sectionTime = sectionTime;
    }

    public ArrayList<EditorEnemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<EditorEnemy> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<EditorPipe> getPipes() {
        return pipes;
    }

    public void setPipes(ArrayList<EditorPipe> pipes) {
        this.pipes = pipes;
    }

    public ArrayList<EditorBrick> getBricks() {
        return bricks;
    }

    public void setBricks(ArrayList<EditorBrick> bricks) {
        this.bricks = bricks;
    }

    Section(){}


    @Override
    public String toString() {
        return "SectionObject{" +
                "length='" + sectionLength +
                ", time='" + sectionTime  +
                ", blocks=" + bricks +
                ", pipes=" + pipes +
                ", enemies=" + enemies +
                '}';
    }


}
