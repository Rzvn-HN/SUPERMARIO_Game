package editor;

import java.util.ArrayList;

public class Level {
    private ArrayList<Section> sectionNumbers = new ArrayList<>();

    public ArrayList<Section> getSectionNumbers() {
        return sectionNumbers;
    }

    public void setSectionNumbers(ArrayList<Section> sectionNumbers) {
        this.sectionNumbers = sectionNumbers;
    }


    Level(){}


    @Override
    public String toString() {
        return "LevelObject{" +
                "sections='" + sectionNumbers +
                '}';
    }
}
