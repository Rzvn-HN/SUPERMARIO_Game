package start;

import manager.InputManager;
import model.GameObject;

import java.net.InetAddress;
import java.util.ArrayList;

public class GamerMP extends Gamer{


    public InetAddress ipAddress;
    public int port;

    public GamerMP(String username, String password, InputManager input, double x, double y,
                   ArrayList<GameObject> removedObj ,ArrayList<String> notification,InetAddress ipAddress, int port,
                   int score,int totalScore,int highestScore,int coins , int totalCoin,int remainingLives,
                   int remainingTime,boolean isMoving,int movingDir) {
        super(username,password,input,x,y,removedObj,notification,
                score,totalScore, highestScore, coins ,  totalCoin,remainingLives,
                remainingTime,isMoving,movingDir);

        this.ipAddress = ipAddress;
        this.port = port;
    }


//    public GamerMP(int level, int x, int y,String username,
//                   String password, ArrayList<GameState> runningGames , InetAddress ipAddress, int port) {
//        super(level, x, y, null, username,password,runningGames);
//        this.ipAddress = ipAddress;
//        this.port = port;
//    }

    public GamerMP(InetAddress ipAddress, int port) {
//        super(level, x, y, null, username);
        this.ipAddress = ipAddress;
        this.port = port;
    }

}
