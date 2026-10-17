package view;

import manager.GameEngine;
import manager.GameStatus;
import start.Gamer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainFrame extends JPanel {



    private Font smallFont;
    private Font bigFont;


    private JButton buttonNotification;
    private JButton buttonMe;
    private JButton buttonBack;

    private JButton buttonGame;
    private JButton buttonChatHistory;
    private JButton buttonStore;

    private JPanel panelNotification;
    private JPanel panelMe;
    private JPanel panelRetry;
    private JPanel panelGame;
    private JPanel panelChatHistory;
    private JPanel panelStore;
    private JPanel panelBack;
    private JPanel panelPic;
    Image marioMainImg = loadImage("/marioMainFrameImg.png");
    Image unknownMainFImg = loadImage("/unknownMainFImg.png");
    Image notfMainImg = loadImage("/notfMainImg.png");
    Image retryImg = loadImage("/retryImg.png");

    private Gamer gamer;

    private GameEngine engine;






    public MainFrame(Gamer gamer, GameEngine engine) {

        this.setBackground(Color.black);
        this.setLayout(null);
        this.setSize(1268, 708);
        this.setVisible(true);
        this.gamer=gamer;
        this.engine = engine;



        bigFont = new Font("m", 0, 40);
        smallFont = new Font("m", 0, 30);

        //notification
        panelNotification = new JPanel();
        panelNotification.setBounds(0,0,212,100);
        panelNotification.setBackground(Color.black);
        ImageIcon notMainImg = new ImageIcon(notfMainImg);
        JButton notMainButton = new JButton(notMainImg);
        notMainButton.setBounds(10,0,90,90);
        notMainButton.setBackground(Color.black);
        notMainButton.setBorder(BorderFactory.createEmptyBorder());
        panelNotification.add(notMainButton);
        notMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == notMainButton) {
                    //TODO Game
                    //engine.setGameStatus(GameStatus.PROFILE_SCREEN);

                }
            }
        });


        //Me
        panelMe = new JPanel();
        panelMe.setBounds(0,110,212,100);
        panelMe.setBackground(Color.black);
        ImageIcon unknownMainImg = new ImageIcon(unknownMainFImg);
        JButton unknownMainButton = new JButton(unknownMainImg);
        unknownMainButton.setBounds(10,0,90,90);
        unknownMainButton.setBackground(Color.black);
        unknownMainButton.setBorder(BorderFactory.createEmptyBorder());
        unknownMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == unknownMainButton) {
                    //TODO Game
                    engine.setGameStatus(GameStatus.PROFILE_SCREEN);

                }
            }
        });

        panelMe.add(unknownMainButton);


        //retry
        panelRetry = new JPanel();
        panelRetry.setBounds(1100,0,212,100);
        panelRetry.setBackground(Color.black);
        ImageIcon retryMainImg = new ImageIcon(retryImg);
        JButton retryButton = new JButton(retryMainImg);
        retryButton.setBounds(0,0,90,90);
        retryButton.setBackground(Color.black);
        retryButton.setBorder(BorderFactory.createEmptyBorder());
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == retryButton) {
                    //TODO Game
//                    engine.setGameStatus(GameStatus.WHICH_GAME_SCREEN);

                }
            }
        });

        panelRetry.add(retryButton);



        //game button
        panelGame = new JPanel();
        panelGame.setBounds(0,380,1218,70);
        panelGame.setBackground(Color.black);
        buttonGame = new JButton();
        buttonGame.setBounds(534,0,200,55);
        buttonGame.setBackground(Color.black);
        buttonGame.setForeground(Color.red);
        buttonGame.setFont(bigFont);
        buttonGame.setText("Game");
        buttonGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buttonGame) {
                    //TODO Game
                    engine.setGameStatus(GameStatus.WHICH_GAME_SCREEN);

                }
            }
             });

      panelGame.add(buttonGame);

        //store button
        panelStore = new JPanel();
        panelStore.setBounds(0,450,1218,70);
        panelStore.setBackground(Color.black);
        buttonStore = new JButton();
        buttonStore.setBounds(534,0,200,55);
        buttonStore.setBackground(Color.black);
        buttonStore.setForeground(Color.red);
        buttonStore.setFont(bigFont);
        buttonStore.setText("Store");
        buttonStore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()== buttonStore){
                    engine.setGameStatus(GameStatus.STORE_SCREEN);
                }}
        });
        panelStore.add(buttonStore);


        //chat history button
        panelChatHistory = new JPanel();
        panelChatHistory.setBounds(0,520,1218,70);
        panelChatHistory.setBackground(Color.black);
        buttonChatHistory = new JButton();
        buttonChatHistory.setBounds(500,0,400,55);
        buttonChatHistory.setBackground(Color.black);
        buttonChatHistory.setForeground(Color.red);
        buttonChatHistory.setFont(bigFont);
        buttonChatHistory.setText("Chat History");
        buttonChatHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()== buttonChatHistory){
                    //TODO brgrde aqab
                }}
        });
        panelChatHistory.add(buttonChatHistory);



        //exit
        panelBack = new JPanel();
        panelBack.setBounds(0,600,300,55);
        panelBack.setBackground(Color.black);
        buttonBack = new JButton();
        buttonBack.setBounds(0,0,200,55);
        buttonBack.setBackground(Color.BLACK);
        buttonBack.setForeground(Color.red);
        buttonBack.setFont(smallFont);
        buttonBack.setText("Exit");
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()== buttonBack){
                    //TODO brgrde aqab
                }}
        });
        panelBack.add(buttonBack);

        //Img
        panelPic = new JPanel();
        panelPic.setBounds(424,0,421,354);
        panelPic.setBackground(Color.black);
        ImageIcon profilImg = new ImageIcon(marioMainImg);
        JLabel label = new JLabel(profilImg);
        label.setBounds(0,50,421,354);
        panelPic.add(label);








        this.add(panelNotification);
        this.add(panelMe);
        this.add(panelPic);
        this.add(panelGame);
        this.add(panelStore);
        this.add(panelChatHistory);
        this.add(panelBack);
        this.add(panelRetry);





    }


    public Image loadImage(String path){
        Image imageToReturn = null;

        try {
            imageToReturn = ImageIO.read(getClass().getResource("/media" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageToReturn;
    }

}
