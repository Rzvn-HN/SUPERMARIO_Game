package view;

public enum CheckPointScreen {

    YES(0),
    NO(1);

    private final int lineNumber;
    CheckPointScreen(int lineNumber){ this.lineNumber = lineNumber; }

    public CheckPointScreen getSelection(int number){
        if(number == 0)
            return YES;
        else if(number == 1)
            return NO;

        else return null;
    }

    public CheckPointScreen select(boolean toUp){
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
