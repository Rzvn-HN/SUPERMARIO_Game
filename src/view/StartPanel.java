package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StartPanel extends JPanel {



        private Font smallFont;
        private Font bigFont;



        private JPanel panelPic;
        private JPanel panelConnecting;

        Image cutyImg = loadImage("/cutyImg.png");
        Image connectingIcon = loadImage("/connectIcon.png");



    public StartPanel() {

            this.setBackground(Color.black);
            this.setLayout(null);
            this.setSize(1268, 708);
            this.setVisible(true);



            bigFont = new Font("m", 0, 45);
            smallFont = new Font("m", 0, 30);

            //notification
            panelPic = new JPanel();
            panelPic.setBounds(0, 20, 500, 500);
            panelPic.setBackground(Color.black);
            ImageIcon cuteImg = new ImageIcon(cutyImg);
            JLabel cutyLabel = new JLabel(cuteImg);
            cutyLabel.setBounds(30, 0, 321, 500);
            cutyLabel.setBackground(Color.black);
            cutyLabel.setBorder(BorderFactory.createEmptyBorder());
            panelPic.add(cutyLabel);


            //connecting
        panelConnecting = new JPanel();
        panelConnecting.setBounds(600, 300, 500, 200);
        panelConnecting.setBackground(Color.black);
        ImageIcon connectImg = new ImageIcon(connectingIcon);
        JLabel connectLabel = new JLabel(connectImg);
        connectLabel.setBounds(0, 0, 80, 80);
        connectLabel.setBackground(Color.black);
        connectLabel.setBorder(BorderFactory.createEmptyBorder());
        JLabel connectText = new JLabel();
        connectText.setBounds(90, 0, 400, 90);
        connectText.setBackground(Color.black);
        connectText.setBorder(BorderFactory.createEmptyBorder());
        connectText.setText("Connecting...");
        connectText.setFont(bigFont);
        connectText.setForeground(Color.white);
        panelConnecting.add(connectLabel);
        panelConnecting.add(connectText);


        this.add(panelConnecting);
        this.add(panelPic);




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

