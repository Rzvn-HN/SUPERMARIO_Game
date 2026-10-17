package start;
public class GameState {
    private int remainingLives;
    private int remainingTime;
    private int coins;
    private int totalScore;
    private boolean place;
    private int killedEnemy;
    private int Score;
    private double marioXLocation;
    private double marioYLocation;
    private int marioState;

    private int level;


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public double getMarioYLocation() {
        return marioYLocation;
    }

    public void setMarioYLocation(double marioYLocation) {
        this.marioYLocation = marioYLocation;
    }

    public int getMarioState() {
        return marioState;
    }

    public void setMarioState(int marioState) {
        this.marioState = marioState;
    }

    public GameState(int level,int remainingLives, int remainingTime, int coins, int Score, double marioXLocation, double marioYLocation, int marioState) {
        this.remainingTime = remainingTime;
        this.remainingLives = remainingLives;
        this.level=level;
        this.coins = coins;
        this.Score = Score;
        this.marioXLocation = marioXLocation;
        this.marioYLocation = marioYLocation;
        this.marioState = marioState;
    }

    public double getMarioXLocation() {
        return marioXLocation;
    }

    public void setMarioXLocation(double marioXLocation) {
        this.marioXLocation = marioXLocation;
    }
/* public GameState(int highestScore, int totalScore) {
        this.highestScore = highestScore;
        this.totalScore = totalScore;
    } */

    public int getRemainingLives() {
        return remainingLives;
    }

    public void setLives(int lives) {
        this.remainingLives = lives;
    }


    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public boolean getPlace() {
        return place;
    }

    public void setPlace(int place) {
        //this.place = Mario.getisInFirstSection();
    }

    public int getKilledEnemy() {
        return killedEnemy;
    }

    public void setKilledEnemy(int killedEnemy) {
        this.killedEnemy = killedEnemy;
    }

    public void setRemainingLives(int remainingLives) {
        this.remainingLives = remainingLives;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }


    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int totalScore(int remainingLives, int coins, int remainingTime, int points) {
        totalScore = remainingTime + points + remainingLives * 20;

        return totalScore;
    }


    public boolean isPlace() {
        return place;
    }

    public void setPlace(boolean place) {
        this.place = place;
    }
}
