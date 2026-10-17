package view;


    public enum WhichGameSelection {
        SCORE_GAME(0),
        ROOM(1);

        private final int lineNumber;
        WhichGameSelection(int lineNumber){ this.lineNumber = lineNumber; }

        public view.WhichGameSelection getSelection(int number){
            if(number == 0)
                return SCORE_GAME;
            else if(number == 1)
                return ROOM;

            else return null;
        }

        public view.WhichGameSelection select(boolean toUp){
            int selection;

            if(lineNumber > -1 && lineNumber < 3){
                selection = lineNumber - (toUp ? 1 : -1);
                if(selection == -1)
                    selection = 2;
                else if(selection == 2)
                    selection = 0;
                return getSelection(selection);
            }

            return null;
        }

        public int getLineNumber() {
            return lineNumber;
        }
    }


