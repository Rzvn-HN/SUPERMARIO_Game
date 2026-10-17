package view;


import manager.GameEngine;
import manager.GameStatus;
import model.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class UIManager extends JPanel{

    private GameEngine engine;
    private Font gameFont;
    private BufferedImage startScreenImage,  helpScreenImage, gameOverScreen,marioMainImg,wallpaperW;
    private BufferedImage heartIcon;
    private BufferedImage coinIcon;
    private BufferedImage selectIcon;
    private MapSelection mapSelection;





    public UIManager(GameEngine engine, int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));

        this.engine = engine;
        ImageLoader loader = engine.getImageLoader();

        mapSelection = new MapSelection();

        BufferedImage sprite = loader.loadImage("/sprite.png");
        this.heartIcon = loader.loadImage("/heart-icon.png");
        this.coinIcon = loader.getSubImage(sprite, 1, 5, 48, 48);
        this.selectIcon = loader.loadImage("/select-icon.png");
        this.startScreenImage = loader.loadImage("/start-screen.png");
        this.helpScreenImage = loader.loadImage("/help-screen.png");
        this.gameOverScreen = loader.loadImage("/game-over.png");
        this.marioMainImg =  loader.loadImage("/marioMainFrameImg.png");
        this.wallpaperW = loader.loadImage("/wallpaperMario.png");

        try {
            InputStream in = getClass().getResourceAsStream("/media/font/mario-font.ttf");
            gameFont = Font.createFont(Font.TRUETYPE_FONT, in);
        } catch (FontFormatException | IOException e) {
            gameFont = new Font("Verdana", Font.PLAIN, 12);
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        GameStatus gameStatus = engine.getGameStatus();


        if(gameStatus == GameStatus.START_SCREEN){
            drawStartScreen(g2);
        }
        else if(gameStatus == GameStatus.First_SCREEN){
           this.add(new StartPanel());
        }
        else if(gameStatus == GameStatus.MAIN_SCREEN){
            this.add(new MainFrame(engine.getGamer(),engine));
        }

        else if(gameStatus == GameStatus.PROFILE_SCREEN){
            this.add(new Profile());
        }
        else if(gameStatus == GameStatus.STORE_SCREEN){
            this.add(new Store());
        }
        else if(gameStatus == GameStatus.WHICH_GAME_SCREEN){
            drawWhichGameScreen(g2);
//            System.out.println("FUCKING TWO");
        }
        else if(gameStatus == GameStatus.WHICH_SCORE_GAME_SCREEN){
            drawWhichScoreGameScreen(g2);
        }
        else if(gameStatus == GameStatus.ROOM_GAME_SCREEN){
            this.add(new InsideRoomScreen(engine.getGamer(),engine));
        }
        else if(gameStatus == GameStatus.SECOND_SCREEN){
            drawSecondScreen(g2);
        }
        else if(gameStatus == GameStatus.STORE_SCREEN){
            drawStoreScreen(g2);
        }
        else if(gameStatus == GameStatus.ME_SCREEN){
            drawMeScreen(g2);
        }
        else if(gameStatus == GameStatus.EDITOR_SCREEN){
            drawEditorScreen(g2);
        }
        else if(gameStatus == GameStatus.GAME_OVER){
            drawGameOverScreen(g2);
        }

        else {
            Point camLocation = engine.getCameraLocation();
            g2.translate(-camLocation.x, -camLocation.y);
            engine.drawMap(g2);
            g2.translate(camLocation.x, camLocation.y);

            drawPoints(g2);
            drawRemainingLives(g2);
            drawAcquiredCoins(g2);
            drawRemainingTime(g2);
            drawLevels(g2);


            ////
            if(gameStatus == GameStatus.PAUSED){
                drawPauseScreen(g2);
            }
            else if(gameStatus == GameStatus.ERROR_SCREEN){
                drawPoorMarioScreen(g2);
            }

            if(gameStatus == GameStatus.CHECKPOINT_SCREEN){
                drawCheckPointScreen(g2);
            }

            if(gameStatus == GameStatus.CHECKPOINT_SCREEN){
                drawEditorScreen(g2);
            }

            else if(gameStatus == GameStatus.MISSION_PASSED){
                drawVictoryScreen(g2);
            }
        }

        g2.dispose();
    }


    private void drawVictoryScreen(Graphics2D g2) {
        g2.setFont(gameFont.deriveFont(50f));
        g2.setColor(Color.WHITE);
        String displayedStr = "YOU WON!";
        int stringLength = g2.getFontMetrics().stringWidth(displayedStr);
        g2.drawString(displayedStr, (getWidth()-stringLength)/2, getHeight()/2);
        String displayedStr1 = "READY FOR LEVEL "+ (engine.getRemainingLevels()+1) +"?";
        int stringLength1 = g2.getFontMetrics().stringWidth(displayedStr1);
        g2.drawString(displayedStr1, (getWidth()-stringLength1)/2, getHeight()/2+60);

    }

    private void drawStoreScreen(Graphics2D g2) {

        //TODO
        g2.drawImage(helpScreenImage, 0, 0, null);
    }

    private void drawMeScreen(Graphics2D g2) {
        //TODO
        g2.drawImage(helpScreenImage, 0, 0, null);
    }

    private void drawEditorScreen(Graphics2D g2) {
        //TODO
        g2.drawImage(helpScreenImage, 0, 0, null);

        JFrame editorFrame = new JFrame("MarioMaker");
        editorFrame.setSize(500,500);
        editorFrame.setBackground(Color.black);
        editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JLabel headLabel =new JLabel();

      //  JLabel.setFont(gameFont.deriveFont(20f));
       // JLabel.setText("Make your own game.");
        headLabel.setBounds(150,20,200,60);

        JLabel levelLabel =new JLabel();
        levelLabel.setBounds(50,120,150,60);

        JLabel liveLabel =new JLabel();
        liveLabel.setBounds(50,220,150,60);

        JLabel marioStateLabel =new JLabel();
        marioStateLabel.setBounds(50,320,150,60);

        JTextField levelJText =new JTextField();
        marioStateLabel.setBounds(300,120,150,60);

        JTextField liveJText =new JTextField();
        marioStateLabel.setBounds(300,220,150,60);

        JTextField marioStateJText =new JTextField();
        marioStateLabel.setBounds(300,320,150,60);

        JButton addButton =new JButton();
        marioStateLabel.setBounds(200,420,100,60);


        editorFrame.add(headLabel);
        editorFrame.add(levelLabel);
        editorFrame.add(liveLabel);
        editorFrame.add(marioStateLabel);
        editorFrame.add(levelJText);
        editorFrame.add(liveJText);
        editorFrame.add(marioStateJText);
        editorFrame.add(addButton);




    }

    private void drawGameOverScreen(Graphics2D g2) {
        g2.drawImage(gameOverScreen, 0, 0, null);
        g2.setFont(gameFont.deriveFont(50f));
        g2.setColor(new Color(130, 48, 48));
        String acquiredPoints = "Score: " + engine.getScore();
        int stringLength = g2.getFontMetrics().stringWidth(acquiredPoints);
        int stringHeight = g2.getFontMetrics().getHeight();
        g2.drawString(acquiredPoints, (getWidth()-stringLength)/2, getHeight()-stringHeight*2);
    }

    private void drawPoorMarioScreen(Graphics2D g2) {
        g2.setFont(gameFont.deriveFont(30f));
        g2.setColor(Color.BLACK);
        String displayedStr0 = "YOU DONT HAVE ENOUGH MONEY,MARIO...";
        String displayedStr1 = "ITS COST <<3>> COINS!";
        String displayedStr2 = "GO RICH.";

        int stringLength0 = g2.getFontMetrics().stringWidth(displayedStr0);
        int stringLength1 = g2.getFontMetrics().stringWidth(displayedStr0);
        int stringLength2 = g2.getFontMetrics().stringWidth(displayedStr0);
        g2.drawString(displayedStr0, (getWidth()-stringLength0)/2 , getHeight()/2-100);
        g2.drawString(displayedStr1, (getWidth()-stringLength1)/2 + selectIcon.getWidth(), getHeight()/2 );
        g2.drawString(displayedStr2, (getWidth()-stringLength2)/2 + selectIcon.getWidth(), getHeight()/2 + 110);



    }

    private void drawCheckPointScreen(Graphics2D g2) {
        double PR = engine.getPR() ;

        g2.setFont(gameFont.deriveFont(20f));
        g2.setColor(Color.BLACK);
        String displayedStr0 = "DO YOU WANT TO SAVE YOUR LOCATION?";
        String displayedStr1 = "YES(ITS COST <<"+(Math.ceil(PR))+ ">> COINS)";
        String displayedStr2 = "NO(GET <<"+ (Math.ceil(0.25*PR))+ ">> COINS)";

        int stringLength0 = g2.getFontMetrics().stringWidth(displayedStr0);
        int stringLength1 = g2.getFontMetrics().stringWidth(displayedStr0);
        int stringLength2 = g2.getFontMetrics().stringWidth(displayedStr0);
        g2.drawString(displayedStr0, (getWidth()-stringLength0)/2 , getHeight()/2-100);
        g2.drawString(displayedStr1, (getWidth()-stringLength1)/2 + selectIcon.getWidth(), getHeight()/2 );
        g2.drawString(displayedStr2, (getWidth()-stringLength2)/2 + selectIcon.getWidth(), getHeight()/2 + 110);

        int row = engine.getCheckPointScreen().getLineNumber();
        int y_location = row*100+370 -selectIcon.getHeight();
        g2.drawImage(selectIcon, (getWidth()-stringLength1)/2, y_location, null);


    }

    private void drawPauseScreen(Graphics2D g2) {
        g2.setFont(gameFont.deriveFont(50f));
        g2.setColor(Color.BLACK);
        String displayedStr0 = "PAUSED MENU";
        String displayedStr1 = "1.SOUND";
        String displayedStr2 = "2.EXIT(WITH SAVE)";
        String displayedStr3 = "3.CONTINUE";

        int stringLength0 = g2.getFontMetrics().stringWidth(displayedStr0);
        int stringLength1 = g2.getFontMetrics().stringWidth(displayedStr0);
        int stringLength2 = g2.getFontMetrics().stringWidth(displayedStr0);
        int stringLength3 = g2.getFontMetrics().stringWidth(displayedStr0);
        g2.drawString(displayedStr0, (getWidth()-stringLength0)/2 , getHeight()/2-100);
        g2.drawString(displayedStr1, (getWidth()-stringLength1)/2 + selectIcon.getWidth(), getHeight()/2 );
        g2.drawString(displayedStr2, (getWidth()-stringLength2)/2 + selectIcon.getWidth(), getHeight()/2 + 110);
        g2.drawString(displayedStr3, (getWidth()-stringLength3)/2 + selectIcon.getWidth(), getHeight()/2 + 220);


            int row = engine.getPausedScreenSelection().getLineNumber();
            int y_location = row*100+370 -selectIcon.getHeight();
            g2.drawImage(selectIcon, 360, y_location, null);


    }




    private void drawAcquiredCoins(Graphics2D g2) {
        g2.setFont(gameFont.deriveFont(30f));
        g2.setColor(Color.WHITE);
        String displayedStr = "" + engine.getCoins();
        g2.drawImage(coinIcon, 200, 10, null);
        g2.drawString(displayedStr, 250, 50);
    }



    private void drawLevels(Graphics2D g2) {
        g2.setFont(gameFont.deriveFont(25f));
        g2.setColor(Color.WHITE);
        String displayedStr = "Level : " + engine.getRemainingLevels();
        //g2.drawImage(null, 50, 110, null);
        g2.drawString(displayedStr,  50, 150);
    }

    private void drawRemainingTime(Graphics2D g2) {
        g2.setFont(gameFont.deriveFont(25f));
        g2.setColor(Color.WHITE);
        String displayedStr = "TIME  : " + engine.getRemainingTime();
        g2.drawString(displayedStr, 50, 200);
    }

    private void drawRemainingLives(Graphics2D g2) {
        g2.setFont(gameFont.deriveFont(30f));
        g2.setColor(Color.WHITE);
        String displayedStr = "" + engine.getRemainingLives();
        g2.drawImage(heartIcon, 50, 10, null);
        g2.drawString(displayedStr, 100, 50);
    }


    private void drawPoints(Graphics2D g2){
        g2.setFont(gameFont.deriveFont(25f));
        g2.setColor(Color.WHITE);
        String displayedStr = "Points: " + engine.getScore();
        int stringLength = g2.getFontMetrics().stringWidth(displayedStr);;
        //g2.drawImage(coinIcon, 50, 10, null);
        g2.drawString(displayedStr, 50, 100);
    }

    private void drawStartScreen(Graphics2D g2){

        g2.drawImage(helpScreenImage, 0, 0, null);
        g2.drawImage(marioMainImg,(getWidth()/2) -110 ,10, null);
        g2.setFont(gameFont.deriveFont(50f));
        g2.setColor(Color.WHITE);


        String displayedStr1 = "GAME";
        String displayedStr2 = "CHAT.HISTORY";
        String displayedStr3 = "CHAT";
        String displayedStr4 = "STORE";


        int stringLength1 = g2.getFontMetrics().stringWidth(displayedStr1);
        int stringLength2 = g2.getFontMetrics().stringWidth(displayedStr2);
        int stringLength3 = g2.getFontMetrics().stringWidth(displayedStr3);
        int stringLength4 = g2.getFontMetrics().stringWidth(displayedStr4);



        g2.drawString(displayedStr1, (getWidth()-stringLength1)/2 + selectIcon.getWidth(), getHeight()/2 );
        g2.drawString(displayedStr2, (getWidth()-stringLength2)/2 + selectIcon.getWidth()-10, getHeight()/2 + 110);
        g2.drawString(displayedStr3, (getWidth()-stringLength3)/2 + selectIcon.getWidth(), getHeight()/2 + 220);
        g2.drawString(displayedStr4, (getWidth()-stringLength4)/2 + selectIcon.getWidth(), getHeight()/2 + 320);



        int row = engine.getStartScreenSelection().getLineNumber();
        int y_location = row*100+370 -selectIcon.getHeight();
        g2.drawImage(selectIcon, 350, y_location, null);




    }

    private void drawWhichGameScreen(Graphics2D g2){

        g2.drawImage(wallpaperW, 0, 0, null);
        g2.setFont(gameFont.deriveFont(50f));
        g2.setColor(Color.black);


        String displayedStr1 = "SCORE GAME";
        String displayedStr2 = "ROOM";


        int stringLength1 = g2.getFontMetrics().stringWidth(displayedStr1);
        int stringLength2 = g2.getFontMetrics().stringWidth(displayedStr2);


        g2.drawString(displayedStr1, (getWidth()-stringLength1)/2 + selectIcon.getWidth(), getHeight()/2 );
        g2.drawString(displayedStr2, (getWidth()-stringLength2)/2 + selectIcon.getWidth()-10, getHeight()/2 + 110);



        int row = engine.getWhichGameSelection().getLineNumber();
        int y_location = row*100+370 -selectIcon.getHeight();
        g2.drawImage(selectIcon, 350, y_location, null);




    }

    private void drawWhichScoreGameScreen(Graphics2D g2){

        g2.drawImage(wallpaperW, 0, 0, null);
        g2.setFont(gameFont.deriveFont(50f));
        g2.setColor(Color.black);


        String displayedStr1 = "MARATHON MARIO";
        String displayedStr2 = "SURVIVE MARIO";


        int stringLength1 = g2.getFontMetrics().stringWidth(displayedStr1);
        int stringLength2 = g2.getFontMetrics().stringWidth(displayedStr2);


        g2.drawString(displayedStr1, (getWidth()-stringLength1)/2 + selectIcon.getWidth(), getHeight()/2 );
        g2.drawString(displayedStr2, (getWidth()-stringLength2)/2 + selectIcon.getWidth()-10, getHeight()/2 + 110);



        int row = engine.getWhichScoreGameSelection().getLineNumber();
        int y_location = row*100+370 -selectIcon.getHeight();
        g2.drawImage(selectIcon, 50, y_location, null);




    }





    private void drawSecondScreen(Graphics2D g2){

       // mapSelection.draw(g2);
        g2.drawImage(helpScreenImage, 0, 0, null);
        g2.setFont(gameFont.deriveFont(50f));
        g2.setColor(Color.WHITE);

        String displayedStr0 = "SUPER MARIO";
        String displayedStr1 = "NEW GAME";
        String displayedStr2 = "LAST GAME";
        String displayedStr3 = "SAVED GAME";

        int stringLength0 = g2.getFontMetrics().stringWidth(displayedStr0);
        int stringLength1 = g2.getFontMetrics().stringWidth(displayedStr0);
        int stringLength2 = g2.getFontMetrics().stringWidth(displayedStr0);
        int stringLength3 = g2.getFontMetrics().stringWidth(displayedStr0);
        g2.drawString(displayedStr0, (getWidth()-stringLength0)/2 , getHeight()/2-100);
        g2.drawString(displayedStr1, (getWidth()-stringLength1)/2 + selectIcon.getWidth(), getHeight()/2 );
        g2.drawString(displayedStr2, (getWidth()-stringLength2)/2 + selectIcon.getWidth(), getHeight()/2 + 110);
        g2.drawString(displayedStr3, (getWidth()-stringLength3)/2 + selectIcon.getWidth(), getHeight()/2 + 220);


        int row = engine.getSecondScreen().getLineNumber();
        int y_location = row*100+370 -selectIcon.getHeight();
        g2.drawImage(selectIcon, 360, y_location, null);


    }

    public String selectMapViaMouse(Point mouseLocation) {
        return mapSelection.selectMap(mouseLocation);
    }

    public int selectMapViaKeyboard(int index){
        return index;
    }

    public int changeSelectedMap(int index, boolean up){
        return mapSelection.changeSelectedMap(index, up);
    }
}
