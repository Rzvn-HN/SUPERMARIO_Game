package view;


    public enum WhichScoreGameSelection {
        MARATHON(0),
        SURVIVE(1);

        private final int lineNumber;
        WhichScoreGameSelection(int lineNumber){ this.lineNumber = lineNumber; }

        public view.WhichScoreGameSelection getSelection(int number){
            if(number == 0)
                return MARATHON;
            else if(number == 1)
                return SURVIVE;

            else return null;
        }

        public view.WhichScoreGameSelection select(boolean toUp){
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




