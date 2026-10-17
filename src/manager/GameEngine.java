package manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.GameObject;
import model.Map;
import model.hero.Mario;
import net.GameClient;
import net.GameServer;
import net.packets.Packet00Login;
import net.packets.Packet03GameLogin;
import start.GameState;
import start.Gamer;

import start.GamerMP;
import start.NewJFrame;
import view.*;
import view.UIManager;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class GameEngine implements Runnable {

    boolean muted = false;

    boolean nextLevel = false;

    private final static int WIDTH = 1268, HEIGHT = 708;

    private MapManager mapManager;
    private UIManager uiManager;


    public Map getMap() {
        return mapManager.getMap();
    }

    public void setMap(Map map) {
        mapManager.setMap(map);
    }



    private Gamer gamer;

    public Gamer getGamer() {
        return gamer;
    }

    public void setGamer(Gamer gamer) {
        this.gamer = gamer;
    }

    private SoundManager soundManager;

    private MapCreator mapCreator;
    private GameStatus gameStatus;
    private boolean isRunning;

    private Camera camera;
    private ImageLoader imageLoader;
    private Thread thread;
    private StartScreenSelection startScreenSelection = StartScreenSelection.GAME;



    private WhichGameSelection whichGameSelection = WhichGameSelection.SCORE_GAME;



    private WhichScoreGameSelection whichScoreGameSelection = WhichScoreGameSelection.MARATHON;

    private boolean isResumeGame = false;

    public GameClient socketClient;
    public GameServer socketServer;

    public NewJFrame log;

    public boolean isApplet() {
        return isApplet;
    }

    public void setApplet(boolean applet) {
        isApplet = applet;
    }

    public boolean isApplet = false;

    public boolean getIsResumeGame() {
        return isResumeGame;
    }

    public void setResumeGame(boolean resumeGame) {
        isResumeGame = resumeGame;
    }
    private PausedScreenSelection pausedScreenSelection = PausedScreenSelection.SOUND;
    private CheckPointScreen checkPointScreen = CheckPointScreen.YES;
    private SecondScreen secondScreen = SecondScreen.NEW_GAME;

    ArrayList<GameObject> toBeRemoved = new ArrayList<>();
    ArrayList<String> notification = new ArrayList<>();
    private int selectedMap = 0;

    public GameEngine(Gamer gamer) {
        this.gamer= gamer;
        setGameStatus(GameStatus.START_SCREEN);
        setApplet(true);
        init(600,0);

    }

    void init(double xLocation, double yLocation) {
        imageLoader = new ImageLoader();
        InputManager inputManager = new InputManager(this);
        camera = new Camera(xLocation, yLocation);
        uiManager = new UIManager(this, WIDTH, HEIGHT);
        soundManager = new SoundManager();
        mapManager = new MapManager();

        if (getIsResumeGame()){
            isRunning = false;
            gameStatus = GameStatus.RUNNING;}
        else {
//            if (socketClient!=null){ gameStatus = GameStatus.MAIN_SCREEN;}
//            else{ gameStatus = GameStatus.First_SCREEN;}
        }

        JFrame frame = new JFrame("Super Mario Bros.");
        frame.add(uiManager);
        frame.addKeyListener(inputManager);
        frame.setBackground(Color.BLACK);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);




        gamer = new GamerMP(gamer.getUsername(),gamer.getPassword(),
                inputManager,0,0,toBeRemoved,notification,null, -1,
                0,0,0,0,0
                ,3,200,false,1);

        start();

        if (!isApplet) {
            Packet00Login loginPacket = new Packet00Login(gamer.getUsername(),gamer.getPassword());
            if (socketServer != null) {
                socketServer.addConnection((GamerMP) gamer, loginPacket);
            }
            loginPacket.writeData(socketClient);
        }





    }

    private synchronized void start() {
        if (isRunning)
            return;

        isRunning = true;

        thread = new Thread(this,  "_main");
        thread.start();

        if (!isApplet) {
            if (JOptionPane.showConfirmDialog(null, "Do you want to run the server") == 0) {
                socketServer = new GameServer(this);
                socketServer.start();
            }
            else {
                socketClient = new GameClient(this, "localhost");
                socketClient.start();
            }
        }
    }

    private void reset(){
        resetCamera();
        setGameStatus(GameStatus.START_SCREEN);
    }


    public void resetCamera(){
        camera = new Camera(0,0);
    }




    public void creatMapLevel(){
        int level = uiManager.selectMapViaKeyboard(selectedMap);
        // String path = uiManager.selectMapViaKeyboard(selectedMap);

        if (level != 0) {
            createMap(level,400,isResumeGame);
        }
    }

    public void selectMapViaKeyboard(){
        int level = uiManager.selectMapViaKeyboard(selectedMap);
       // String path = uiManager.selectMapViaKeyboard(selectedMap);

        if (level != 0) {
            createMap(level,400,isResumeGame);
        }
    }

    public void changeSelectedMap(boolean up){
        selectedMap = uiManager.changeSelectedMap(selectedMap, up);
    }

    void createMap(int level, double time, boolean isResumeGame) {
        boolean loaded = mapManager.createMap(imageLoader, level,time,isResumeGame,gamer );

        if(loaded){
            setGameStatus(GameStatus.RUNNING);
            if (muted==true){}
            else {soundManager.restartBackground();}


        }

        else
            setGameStatus(GameStatus.START_SCREEN);
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (isRunning && !thread.isInterrupted()) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                if (gameStatus == GameStatus.RUNNING) {
                    gameLoop();
                }
                delta--;
            }
            render();

            if(gameStatus != GameStatus.RUNNING) {
                timer = System.currentTimeMillis();
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                mapManager.updateTime();
            }
        }
    }

    private void render() {
        uiManager.repaint();
    }

    private void gameLoop() {
        updateLocations();
        checkCollisions();
        updateCamera();




        if (isGameOver()) {
            setGameStatus(GameStatus.GAME_OVER);
        }

        int missionPassed = passMission();
        if(missionPassed > -1){
            mapManager.acquirePoints(missionPassed);
            //setGameStatus(GameStatus.MISSION_PASSED);
        } else if(mapManager.endLevel())
            setGameStatus(GameStatus.MISSION_PASSED);
        updateMap();



    }



    private void updateCamera() {
        Mario mario = mapManager.getMario();
        double marioVelocityX = mario.getVelX();
        double shiftAmount = 0;

        if (marioVelocityX > 0 && mario.getX() - 600 > camera.getX()) {
            shiftAmount = marioVelocityX;
        }

        camera.moveCam(shiftAmount, 0);
    }


    private void updateMap () {
       if (mapManager.endLevel()) {

               Mario mario = mapManager.getMario();
              // mapManager.createMap(this.getImageLoader(),"Map 2.png" );
               mario.resetLocation();
               this.resetCamera();

              createMap(mario.getLevels()+1,mapManager.getRemainingTime(),false);
              mapManager.setMario(mario);
              mapManager.setLevel(mario.getLevels()+1);


        }
    }


    private void updateLocations() {
        mapManager.updateLocations();
    }

    private void checkCollisions() {
        mapManager.checkCollisions(this);
    }

    public void receiveInput(ButtonAction input) {

        if (gameStatus == GameStatus.START_SCREEN) {
            if (input == ButtonAction.SELECT && startScreenSelection == StartScreenSelection.GAME) {
                setGameStatus(GameStatus.WHICH_GAME_SCREEN);
            } else if (input == ButtonAction.SELECT && startScreenSelection == StartScreenSelection.STORE) {
                setGameStatus(GameStatus.STORE_SCREEN);
            } else if (input == ButtonAction.SELECT && startScreenSelection == StartScreenSelection.CHAT) {
                setGameStatus(GameStatus.CHAT_SCREEN);
            }
            else if (input == ButtonAction.SELECT && startScreenSelection == StartScreenSelection.CHAT_HISTORY) {
                setGameStatus(GameStatus.CHAT_SCREEN_HISTORY);


            }
            else if (input == ButtonAction.GO_UP) {
                selectOption(true);
            } else if (input == ButtonAction.GO_DOWN) {
                selectOption(false);
            }
        }
        else if (gameStatus == GameStatus.ERROR_SCREEN) {
            if (input == ButtonAction.SELECT) {
                //TODO
                setGameStatus(GameStatus.RUNNING);
            }
        }

        else if (gameStatus == GameStatus.First_SCREEN) {

                setGameStatus(GameStatus.First_SCREEN);

        }
        else if (gameStatus == GameStatus.MAIN_SCREEN) {

            setGameStatus(GameStatus.MAIN_SCREEN);

        }

            else if (gameStatus == GameStatus.CHECKPOINT_SCREEN) {
            Mario mario = mapManager.getMario();

            if (input == ButtonAction.SELECT && checkPointScreen == checkPointScreen.YES) {
                    setGameStatus(GameStatus.RUNNING);

            }
            else if (input == ButtonAction.SELECT && checkPointScreen == checkPointScreen.NO) {
               mario.acquireCoin((int)Math.ceil(0.25*getPR()));

                setGameStatus(GameStatus.RUNNING);
            }

            //TODO
            else if (input == ButtonAction.GO_UP) {
                checkPointScreen(true);
            }
            else if (input == ButtonAction.GO_DOWN) {
                checkPointScreen(false);
            }
        }


        else if (gameStatus == GameStatus.PAUSED) {
            if (input == ButtonAction.SELECT && pausedScreenSelection == PausedScreenSelection.SOUND) {
                muted = true;
                //seda bazi qaat mikne
            }
            else if (input == ButtonAction.SELECT && pausedScreenSelection == PausedScreenSelection.EXIT) {

                System.out.println(getMapManager().getMario().getX() + "  ----  " +getMapManager().getMario().getY());
                        GameState game = new GameState( gamer.getLevel(),getRemainingLives(), getRemainingTime(),
                                getCoins(), getScore(), getMapManager().getMario().getX(),
                                getMapManager().getMario().getY(),0);

                        if (gamer.getHighestScore() < game.totalScore(getRemainingLives(), getCoins(), getRemainingTime(), getScore())){
                            gamer.setHighestScore(game.totalScore(getRemainingLives(), getCoins(), getRemainingTime(), getScore()));
                        }

                        //TODO fix this line this is important
                gamer.getRunningGames().add(0,game);
                        FileWriter file = null;
                        try {
                            file = new FileWriter("./src/data/"+ gamer.getName()+".json");
                            new Gson().toJson(gamer, Gamer.class,  file);
                            file.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        System.out.println("game Closed!");


                setResumeGame(true);

                reset();
            }


        else if (input == ButtonAction.SELECT && pausedScreenSelection == PausedScreenSelection.CONTINUE) {
                pauseGame();

            } else if (input == ButtonAction.GO_UP) {
                pausedSelectOption(true);
            } else if (input == ButtonAction.GO_DOWN) {
                pausedSelectOption(false);
            }
        }

        else if(gameStatus == GameStatus.MISSION_PASSED  ){
            if(input == ButtonAction.YES){
                setNextLevel(true);
            }

        }



        else if(gameStatus == GameStatus.EDITOR_SCREEN) {


        }

        else if(gameStatus == GameStatus.SECOND_SCREEN){
            if (input == ButtonAction.SELECT && secondScreen == secondScreen.NEW_GAME) {
                //TODO shoru bazi sakht map1 start game
                String path = "Map 1.png";

                if (path != null) {
                    createMap(1,400, getIsResumeGame());
                    mapManager.setLevel(1);
                }



            }


            else if (input == ButtonAction.SELECT && secondScreen == secondScreen.LAST_GAME) {



                ResumeGame gameEngine = new ResumeGame(gamer);



                //TODO BAZI QABLI
                }



            else if (input == ButtonAction.SELECT && secondScreen == secondScreen.SAVED_GAME) {
                //TODO
            }
            else if(input == ButtonAction.GO_UP){
                setSecondScreen(true);
            }
            else if(input == ButtonAction.GO_DOWN){
                setSecondScreen(false);
            }
        }

        else if(gameStatus == GameStatus.WHICH_GAME_SCREEN){
            if (input == ButtonAction.SELECT && whichGameSelection == whichGameSelection.SCORE_GAME) {
              setGameStatus(GameStatus.WHICH_SCORE_GAME_SCREEN);
            }
            else if (input == ButtonAction.SELECT && whichGameSelection == whichGameSelection.ROOM) {
                setGameStatus(GameStatus.ROOM_GAME_SCREEN);
            }

            else if (input == ButtonAction.GO_UP) {
                setWhichGameSelection(true);
            }
            else if (input == ButtonAction.GO_DOWN) {
                setWhichGameSelection(false);
            }
        }
        else if(gameStatus == GameStatus.WHICH_SCORE_GAME_SCREEN){
            if (input == ButtonAction.SELECT && whichScoreGameSelection == whichScoreGameSelection.MARATHON) {
                //TODO MARTHON
                String path = "Map 1.png";

                if (path != null) {
                    createMap(1,400, getIsResumeGame());
                    mapManager.setLevel(1);
                }
            }
            else if (input == ButtonAction.SELECT && whichScoreGameSelection == whichScoreGameSelection.SURVIVE) {
                //TODO SURVIVE
            }

            else if (input == ButtonAction.GO_UP) {
                setWhichScoreGameSelection(true);
            }
            else if (input == ButtonAction.GO_DOWN) {
                setWhichScoreGameSelection(false);
            }
        }


        else if (gameStatus == GameStatus.RUNNING) {
            Mario mario = mapManager.getMario();
            if (input == ButtonAction.JUMP) {
                mario.jump(this,40/3);
            } else if (input == ButtonAction.SIT) {
                mario.sit(this);
            }else if (input == ButtonAction.M_RIGHT) {
                mario.move(true, camera);
            } else if (input == ButtonAction.M_LEFT) {
                mario.move(false, camera);
            } else if (input == ButtonAction.ACTION_COMPLETED) {
                mario.setVelX(0);
            } else if (input == ButtonAction.FIRE) {
                mapManager.fire(this);
            } else if (input == ButtonAction.HAMMER) {
                mapManager.hammer(this);
            }else if (input == ButtonAction.PAUSE_RESUME) {
                pauseGame();
            }

        }

        else if(gameStatus == GameStatus.GAME_OVER && input == ButtonAction.GO_TO_START_SCREEN){
            reset();
        } else if(gameStatus == GameStatus.MISSION_PASSED && input == ButtonAction.GO_TO_START_SCREEN){
            reset();
        }

        if(input == ButtonAction.GO_TO_START_SCREEN){
            setGameStatus(GameStatus.START_SCREEN);
        }
    }

    private void selectOption(boolean selectUp) {
        startScreenSelection = startScreenSelection.select(selectUp);

    }

    private void pausedSelectOption(boolean selectUp) {
        pausedScreenSelection = pausedScreenSelection.select(selectUp);
    }


    private void setSecondScreen(boolean selectUp) {
        secondScreen = secondScreen.select(selectUp);
    }
    private void checkPointScreen (boolean selectUp) {
        checkPointScreen = checkPointScreen.select(selectUp);
    }

    private void startGame() {
        if (gameStatus != GameStatus.GAME_OVER) {
            setGameStatus(GameStatus.SECOND_SCREEN);
        }
    }

    private void editorStart() {
        if (gameStatus != GameStatus.GAME_OVER) {
            setGameStatus(GameStatus.EDITOR_SCREEN);
        }
    }

    private void pauseGame() {
        if (gameStatus == GameStatus.RUNNING) {
            setGameStatus(GameStatus.PAUSED);

            if (muted==true){}
            else {soundManager.pauseBackground();}



        } else if (gameStatus == GameStatus.PAUSED) {
            setGameStatus(GameStatus.RUNNING);

            if (muted==true){}
            else {soundManager.resumeBackground();}

        }
    }






    public void shakeCamera(){
        camera.shakeCamera();
    }

    private boolean isGameOver() {
        if(gameStatus == GameStatus.RUNNING)
            return mapManager.isGameOver();
        return false;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public StartScreenSelection getStartScreenSelection() {
        return startScreenSelection;
    }

    public WhichGameSelection getWhichGameSelection() {
        return whichGameSelection;
    }
    public WhichScoreGameSelection getWhichScoreGameSelection() {
        return whichScoreGameSelection;
    }

    public void setWhichGameSelection(boolean selectUp) {

        whichGameSelection = whichGameSelection.select(selectUp);
    }

    public void setWhichScoreGameSelection(boolean selectUp) {
        whichScoreGameSelection = whichScoreGameSelection.select(selectUp);
    }

    public PausedScreenSelection getPausedScreenSelection() {
        return pausedScreenSelection;
    }

    public CheckPointScreen getCheckPointScreen() {
        return checkPointScreen;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setNextLevel(boolean nextLevel) {
        this.nextLevel = nextLevel;
    }

    public int getScore() {
        return mapManager.getScore();
    }

    public int getRemainingLives() {
        return mapManager.getRemainingLives();
    }

    public int getRemainingLevels() {
        return mapManager.getRemainingLevels();
    }

    public int getCoins() {
        return mapManager.getCoins();
    }

    public int getSelectedMap() {
        return selectedMap;
    }

    private int PR = 0  ;

    public void setPR(MapManager mapManager){
        this.PR= mapManager.PR();
    }
    public int getPR() {
        return PR;
    }

    public void drawMap(Graphics2D g2) {
        mapManager.drawMap(g2);
    }

    public Point getCameraLocation() {
        return new Point((int)camera.getX(), (int)camera.getY());
    }


    public boolean isNextLevel() {
        return nextLevel;
    }

    private int passMission(){
        return mapManager.passMission();
    }

    public void playCoin() {

        if (muted==true){}
        else {soundManager.playCoin();}

    }

    public void playOneUp() {

        if (muted==true){}
        else {soundManager.playOneUp();}

    }

    public void playSuperMushroom() {

        if (muted==true){}
        else {soundManager.playSuperMushroom();}

    }

    public void playMarioDies() {

        if (muted==true){}
        else {soundManager.playMarioDies();}

    }

    public void playJump() {
        if (muted==true){}
        else {soundManager.playJump();}

    }

    public void playSit() {
        if (muted==true){}
        else {soundManager.playJump();}

    }

    public void playFireFlower() {
        if (muted==true){}
        else {soundManager.playFireFlower();}

    }

    public void playFireball() {
        if (muted==true){}
        else {soundManager.playFireball();}

    }

    public void playHammer() {
        if (muted==true){}
        else {soundManager.playHammer();}

    }

    public void playStomp() {
        if (muted==true){}
        else {soundManager.playStomp();}

    }

    public MapManager getMapManager() {
        return mapManager;
    }


    public int getRemainingTime() {
        return mapManager.getRemainingTime();
    }

    public SecondScreen getSecondScreen() {
        return secondScreen;
    }




}