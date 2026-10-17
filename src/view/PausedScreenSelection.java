package view;

public enum PausedScreenSelection {


    SOUND(0),
    EXIT(1),
    CONTINUE(2);

    private final int lineNumber;
    PausedScreenSelection(int lineNumber){ this.lineNumber = lineNumber; }

    public PausedScreenSelection getSelection(int number){
        if(number == 0)
            return SOUND;
        else if(number == 1)
            return EXIT;
        else if(number == 2)
            return CONTINUE;
        else return null;
    }

    public PausedScreenSelection select(boolean toUp){
        int selection;

        if(lineNumber > -1 && lineNumber < 3){
            selection = lineNumber - (toUp ? 1 : -1);
            if(selection == -1)
                selection = 2;
            else if(selection == 3)
                selection = 0;
            return getSelection(selection);
        }

        return null;
    }

    public int getLineNumber() {
        return lineNumber;
    }

}
