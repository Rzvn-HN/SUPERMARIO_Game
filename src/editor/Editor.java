package editor;

import start.GameState;

import java.util.ArrayList;

public class Editor {

    private int marioState;
    private int livesNumbers;

    private String PathJsonFile;
    private ArrayList<Level> levelNumbers = new ArrayList<>();


    Editor(){}

    public int getMarioState() {
        return marioState;
    }

    public void setMarioState(int marioState) {
        this.marioState = marioState;
    }

    public int getLivesNumbers() {
        return livesNumbers;
    }

    public void setLivesNumbers(int livesNumbers) {
        this.livesNumbers = livesNumbers;
    }

    public String getPathJsonFile() {
        return PathJsonFile;
    }

    public void setPathJsonFile(String pathJsonFile) {
        PathJsonFile = pathJsonFile;
    }

    public ArrayList<Level> getLevelNumbers() {
        return levelNumbers;
    }

    public void setLevelNumbers(ArrayList<Level> levelNumbers) {
        this.levelNumbers = levelNumbers;
    }



    @Override
    public String toString() {
        return "File{" +
                "levels='" + levelNumbers + '\'' +
                ", heart='" + livesNumbers + '\'' +
                ", marioState=" + marioState + '\''+
                ", PathJsonFile='" + PathJsonFile + '\'' +
                '}';
    }
}
