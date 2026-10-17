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


    public class InsideRoomScreen extends JPanel {



        private Font smallFont;
        private Font bigFont;

        private JPanel enterPanel;
        private JPanel createPanel;

        private JLabel enterLabel;
        private JLabel createLabel;

        private JLabel enterLabelT;
        private JLabel createLabelT;

        private JTextField getEnter;
        private JTextField getCreate;
        private JButton enterButton;
        private JButton createButton;
        private JButton backButton;

        Image wallpaper = loadImage("/wallpaperMario.png");

        private Gamer gamer;

        private GameEngine engine;


        InsideRoomScreen(Gamer gamer, GameEngine engine){

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


            //enter panel
            enterPanel = new JPanel();
            enterPanel.setBounds(50, 50, 574, 608);
            enterPanel.setBackground(Color.black);
            enterPanel.setBorder(BorderFactory.createEmptyBorder());
            enterLabelT = new JLabel("JOIN Game");
            enterLabelT.setFont(bigFont);
            enterLabelT.setForeground(Color.white);
            enterLabelT.setBounds(100, 50, 574, 101);
            enterLabelT.setBackground(Color.black);
            enterLabelT.setBorder(BorderFactory.createEmptyBorder());
            enterLabel = new JLabel("Enter the <PORT-PASSWORD> :");
            enterLabel.setFont(smallFont);
            enterLabel.setForeground(Color.white);
            enterLabel.setBounds(15, 151, 574, 101);
            enterLabel.setBackground(Color.black);
            enterLabel.setBorder(BorderFactory.createEmptyBorder());
            getEnter = new JTextField();
            getEnter.setForeground(Color.white);
            getEnter.setFont(smallFont);
            getEnter.setBounds(60, 252, 454, 101);
            getEnter.setBackground(Color.black);
            enterButton = new JButton("JOIN");
            enterButton.setFont(smallFont);
            enterButton.setForeground(Color.white);
            enterButton.setBounds(70, 380, 434, 80);
            enterButton.setBackground(Color.black);
            enterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == enterButton) {
                        //TODO Game
//                        engine.setGameStatus(GameStatus.WHICH_SCORE_GAME_SCREEN);

                    }
                }
            });



            //host game
            createPanel = new JPanel();
            createPanel.setBounds(644, 50, 574, 608);
            createPanel.setBackground(Color.black);
            createPanel.setBorder(BorderFactory.createEmptyBorder());
            createLabelT = new JLabel("HOST Game");
            createLabelT.setFont(bigFont);
            createLabelT.setForeground(Color.white);
            createLabelT.setBounds(100, 50, 574, 101);
            createLabelT.setBackground(Color.black);
            createLabelT.setBorder(BorderFactory.createEmptyBorder());
            createLabel = new JLabel("Enter the <PORT-PASSWORD> :");
            createLabel.setFont(smallFont);
            createLabel.setForeground(Color.white);
            createLabel.setBounds(15, 151, 574, 101);
            createLabel.setBackground(Color.black);
            createLabel.setBorder(BorderFactory.createEmptyBorder());
            getCreate = new JTextField();
            getCreate.setForeground(Color.white);
            getCreate.setFont(smallFont);
            getCreate.setBounds(60, 252, 454, 101);
            getCreate.setBackground(Color.black);
            createButton = new JButton("CREATE");
            createButton.setFont(smallFont);
            createButton.setForeground(Color.white);
            createButton.setBounds(70, 380, 434, 80);
            createButton.setBackground(Color.black);
            createButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == createButton) {
                        //TODO Game
//                        engine.setGameStatus(GameStatus.WHICH_SCORE_GAME_SCREEN);

                    }
                }
            });




            backButton = new JButton("BACK");
            backButton.setFont(smallFont);
            backButton.setBounds(50,600,200,40);
            backButton.setContentAreaFilled(false);
            backButton.setForeground(Color.black);
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == backButton) {
                        //TODO Game
                      //  engine.setGameStatus(GameStatus.WHICH_GAME_SCREEN);

                    }
                }
            });



            createPanel.add(createLabel);
            createPanel.add(createLabelT);
            createPanel.add(getCreate);
            createPanel.add(createButton);

            enterPanel.add(enterLabel);
            enterPanel.add(enterLabelT);
            enterPanel.add(enterButton);
            enterPanel.add(getEnter);

            wallpaperLabel.add(createPanel);
            wallpaperLabel.add(enterPanel);
            //wallpaperLabel.add(backButton);

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





