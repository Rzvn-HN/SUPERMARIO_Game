package view;


public enum StartScreenSelection {
    GAME(0),
    CHAT_HISTORY(1),
    CHAT(2),
    STORE(3);

    private final int lineNumber;
    StartScreenSelection(int lineNumber){ this.lineNumber = lineNumber; }

    public StartScreenSelection getSelection(int number){
        if(number == 0)
            return GAME;
        else if(number == 1)
            return CHAT_HISTORY;
        else if(number == 2)
            return CHAT;
        else if(number == 3)
            return STORE;
        else return null;
    }

    public StartScreenSelection select(boolean toUp){
        int selection;

        if(lineNumber > -1 && lineNumber < 4){
            selection = lineNumber - (toUp ? 1 : -1);
            if(selection == -1)
                selection = 2;
            else if(selection == 4)
                selection = 0;
            return getSelection(selection);
        }

        return null;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
