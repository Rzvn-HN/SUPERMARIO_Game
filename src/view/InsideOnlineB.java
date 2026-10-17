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

public class InsideOnlineB extends JPanel {


    private Font smallFont;
    private Font bigFont;

    private JButton pointyGameButton;
    private JButton roomButton;
    private JButton backButton;

    Image wallpaper = loadImage("/wallpaperMario.png");
    private Gamer gamer;

    private GameEngine engine;


    InsideOnlineB(Gamer gamer, GameEngine engine){
        this.gamer=gamer;
        this.engine = engine;

        bigFont = new Font("m", 0, 40);
        smallFont = new Font("m", 0, 30);


        this.setLayout(null);
        this.setSize(1268, 708);
        this.setVisible(true);
        ImageIcon wallImg = new ImageIcon(wallpaper);
        JLabel wallpaperLabel = new JLabel(wallImg);
        wallpaperLabel.setBounds(0, 0, 1258, 708);
        wallpaperLabel.setBackground(getBackground());
        wallpaperLabel.setBorder(BorderFactory.createEmptyBorder());

        pointyGameButton = new JButton("Points Game");
        pointyGameButton.setFont(bigFont);
        pointyGameButton.setBounds(600,80,300,100);
        pointyGameButton.setContentAreaFilled(false);
        pointyGameButton.setForeground(Color.white);
        pointyGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == pointyGameButton) {
                    //TODO Game
                    engine.setGameStatus(GameStatus.WHICH_SCORE_GAME_SCREEN);

                }
            }
        });

        roomButton = new JButton("Enter Room");
        roomButton.setFont(bigFont);
        roomButton.setBounds(100,80,300,100);
        roomButton.setContentAreaFilled(false);
        roomButton.setForeground(Color.white);
        roomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == roomButton) {
                    //TODO Game
                   engine.setGameStatus(GameStatus.ROOM_GAME_SCREEN);

                }
            }
        });


        backButton = new JButton("BACK");
        backButton.setFont(smallFont);
        backButton.setBounds(50,600,200,40);
        backButton.setContentAreaFilled(false);
        backButton.setForeground(Color.black);

        wallpaperLabel.add(roomButton);
        wallpaperLabel.add(pointyGameButton);
        wallpaperLabel.add(backButton);
        this.add(wallpaperLabel);



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

