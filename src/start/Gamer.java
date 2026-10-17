package start;
import manager.InputManager;
import model.GameObject;
import model.hero.Mario;
import start.GameState;

import java.net.InetAddress;
import java.util.ArrayList;

public class Gamer extends Mario {

    private String name;
    private String password;
    private int remainingLives;
    private int remainingTime;
    private int coins;
    private int totalScore;
    private int totalCoin;
    private int killedEnemy;
    private int Score;
    private int level;
    private boolean place;
    private ArrayList<GameState> runningGames = new ArrayList<>();

    private int highestScore;
    private String PathJsonFile;

    public int x, y;

    private String username;
    protected int numSteps = 0;
    protected boolean isMoving;

    protected int movingDir = 1;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public int getNumSteps() {
        return numSteps;
    }

    public void setNumSteps(int numSteps) {
        this.numSteps = numSteps;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public int getMovingDir() {
        return movingDir;
    }

    public void setMovingDir(int movingDir) {
        this.movingDir = movingDir;
    }
    ArrayList<GameObject> removedObj = new ArrayList<>();
    ArrayList<String> notification= new ArrayList<>();




    public Gamer(String username, String password,int level,
                 int remainingLives,int remainingTime,int coins,int totalScore,int killedEnemy ,
                 int score,boolean place,ArrayList<String> runningGames,int highestScore, String pathJsonFile ) {
        super(username,0,0,score,coins,remainingTime,remainingLives,false,1);
        this.username = username;
        this.password = password;
        this.remainingLives=remainingLives;
        this.remainingTime=remainingTime;
        this.setScore(score);
        this.highestScore=highestScore;
        this.totalScore=totalScore;
        this.setCoins(coins);
        this.totalCoin=totalCoin;
        this.isMoving=isMoving;
        this.movingDir=movingDir;
        removedObj.addAll(removedObj);
        notification.addAll(notification);


    }

    public Gamer(String username, String password, InputManager input, double x, double y,
                 ArrayList<GameObject> removedObj,ArrayList<String> notification
            ,int score,int totalScore,int highestScore,int coins , int totalCoin,
                 int remainingLives,int remainingTime,boolean isMoving,int movingDir ) {
        super(username,x,y,score,coins,remainingTime,remainingLives,isMoving,movingDir);
        this.username = username;
        this.password = password;
        this.remainingLives=remainingLives;
        this.remainingTime=remainingTime;
        this.setScore(score);
        this.highestScore=highestScore;
        this.totalScore=totalScore;
        this.setCoins(coins);
        this.totalCoin=totalCoin;
        this.isMoving=isMoving;
        this.movingDir=movingDir;


    }



    public Gamer() {
    }

    public int getRemainingLives() {
        return remainingLives;
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

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getTotalCoin() {
        return totalCoin;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
    public void setTotalCoin(int totalCoin) {
        this.totalCoin = totalCoin;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public int getKilledEnemy() {
        return killedEnemy;
    }

    public void setKilledEnemy(int killedEnemy) {
        this.killedEnemy = killedEnemy;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public boolean isPlace() {
        return place;
    }

    public void setPlace(boolean place) {
        this.place = place;
    }


    public String getPathJsonFile() {
        return PathJsonFile;
    }

    public void setPathJsonFile(String pathJsonFile) {
        PathJsonFile = pathJsonFile;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    public Gamer(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<GameState> getRunningGames() {
        return runningGames;
    }

    public void setRunningGames(ArrayList<GameState> runningGames) {
        this.runningGames = runningGames;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", level=" + level +
                ", remainingLives=" + remainingLives +
                ", remainingTime=" + remainingTime +
                ", coins=" + coins +
                ", totalScore=" + totalScore +
                ", killedEnemy=" + killedEnemy +
                ", Score=" + Score +
                ", place=" + place +
                ", runningGames=" + runningGames +
                ", highestScore=" + highestScore +
                ", PathJsonFile='" + PathJsonFile + '\'' +
                '}';
    }


}
