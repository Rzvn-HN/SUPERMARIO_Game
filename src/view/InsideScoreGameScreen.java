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

public class InsideScoreGameScreen extends JPanel {


    private Font smallFont;
    private Font bigFont;

    private JButton survivalButton;
    private JButton marathonButton;
    private JButton backButton;

    Image wallpaper = loadImage("/wallpaperMario.png");

    private Gamer gamer;

    private GameEngine engine;

    InsideScoreGameScreen(Gamer gamer, GameEngine engine){

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
        survivalButton = new JButton("Mario Survive");
        survivalButton.setFont(bigFont);
        survivalButton.setBounds(600,80,300,100);
        survivalButton.setContentAreaFilled(false);
        survivalButton.setForeground(Color.white);
        survivalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == survivalButton) {
                    //TODO Game
//                    engine.setGameStatus(GameStatus.WHICH_SCORE_GAME_SCREEN);

                }
            }
        });

        marathonButton = new JButton("Mario Marathon");
        marathonButton.setFont(bigFont);
        marathonButton.setBounds(400,200,300,100);
        marathonButton.setContentAreaFilled(false);
        marathonButton.setForeground(Color.white);
        marathonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == marathonButton) {
                    //TODO Game
//                    engine.setGameStatus(GameStatus.WHICH_SCORE_GAME_SCREEN);

                }
            }
        });


        backButton = new JButton("BACK");
        backButton.setFont(smallFont);
        backButton.setBounds(50,600,200,40);
        backButton.setContentAreaFilled(false);
        backButton.setForeground(Color.black);

        wallpaperLabel.add(survivalButton);
        wallpaperLabel.add(marathonButton);
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
