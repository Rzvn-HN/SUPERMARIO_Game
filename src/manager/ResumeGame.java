package manager;

import start.Gamer;

public class ResumeGame extends GameEngine {
    public ResumeGame(Gamer gamer) {

        super(gamer);
        super.setResumeGame(true);


        double x = gamer.getRunningGames().get(0).getMarioXLocation();

        init(x, 0);
        createMap(gamer.getRunningGames().get(0).getLevel(),gamer.getRunningGames().get(0).getRemainingTime(), getIsResumeGame());

    }

}
