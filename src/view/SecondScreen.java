package view;

public enum SecondScreen {

        NEW_GAME(0),
        LAST_GAME(1),
        SAVED_GAME(2);

        private final int lineNumber;
    SecondScreen(int lineNumber){ this.lineNumber = lineNumber; }

        public SecondScreen getSelection(int number){
            if(number == 0)
                return NEW_GAME;
            else if(number == 1)
                return LAST_GAME;
            else if(number == 2)
                return SAVED_GAME;
            else return null;
        }

        public SecondScreen select(boolean toUp){
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


