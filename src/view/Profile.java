package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Profile extends JPanel {
    static JFrame profile = new JFrame();


    private Font smallFont;
    private Font bigFont;
    private JLabel jLabelNameText;
    private JLabel jLabelUserName;
    private JLabel jLabelCoinText;
    private JLabel jLabelCoin;
    private JLabel jLabelDiamondText;
    private JLabel jLabelDiamond;
    private JLabel jLabelRateText;
    private JLabel jLabelRate;
    private JLabel jLabelTableText;


    private JButton buttonOnlineTable;
    private JButton buttonOfflineTable;
    private JButton buttonBack;

    private JPanel panelUsername;
    private JPanel panelTotalCoin;
    private JPanel panelTotalDiamond;
    private JPanel panelGamerRate;
    private JPanel panelScoresTable;
    private JPanel panelBack;
    private JPanel panelPic;
    private JPanel panelBlack;
    Image profileImg = loadImage("/profileImg.png");






    Profile() {


        this.setBackground(Color.black);
        this.setLayout(null);
        this.setSize(1268, 708);
        this.setVisible(true);



        bigFont = new Font("m", 0, 36);
        smallFont = new Font("m", 0, 24);

        //username
        panelUsername = new JPanel();
        panelUsername.setBounds(50,0,780,118);
        panelUsername.setBackground(Color.black);
        jLabelNameText = new JLabel();
        String username = "USER NAME :";
        int usernameStringLength = getFontMetrics(bigFont).stringWidth(username);
        jLabelNameText.setBounds(0,29,usernameStringLength,60);
        jLabelNameText.setFont(bigFont);
        jLabelNameText.setForeground(Color.white);
        jLabelNameText.setBackground(Color.BLACK);
        jLabelNameText.setText(username);
        jLabelUserName = new JLabel();
        jLabelUserName.setBounds(456,29,250,60);
        jLabelUserName.setFont(new Font("m", 0, 36));
        jLabelUserName.setForeground(Color.white);
        jLabelUserName.setBackground(Color.BLACK);
        jLabelUserName.setText("name");//TODO username ro bgir
        panelUsername.add(jLabelNameText);
        panelUsername.add(jLabelUserName);

        //coin
        panelTotalCoin = new JPanel();
        panelTotalCoin.setBounds(50,118,780,118);
        panelTotalCoin.setBackground(Color.black);
        jLabelCoinText = new JLabel();
        String coinString = "TOTAL COIN :";
        int coinStringLength = getFontMetrics(bigFont).stringWidth(coinString);
        jLabelCoinText.setBounds(0,29,coinStringLength,60);
        jLabelCoinText.setFont(bigFont);
        jLabelCoinText.setText(coinString);
        jLabelCoinText.setForeground(Color.white);
        jLabelCoinText.setBackground(Color.BLACK);
        jLabelCoin = new JLabel();
        jLabelCoin.setBounds(456,29,250,60);
        jLabelCoin.setFont(new Font("m", 0, 36));
        jLabelCoin.setText("coin");//TODO tedad coin kol ro bgir
        jLabelCoin.setForeground(Color.white);
        jLabelCoin.setBackground(Color.BLACK);
        panelTotalCoin.add(jLabelCoinText);
        panelTotalCoin.add(jLabelCoin);

        //diamond
        panelTotalDiamond = new JPanel();
        panelTotalDiamond.setBounds(50,236,780,118);
        panelTotalDiamond.setBackground(Color.black);
        jLabelDiamondText = new JLabel();
        String diamondString = "TOTAL DIAMOND :";
        int diamondStringLength = getFontMetrics(bigFont).stringWidth(diamondString);
        jLabelDiamondText.setBounds(0,29,diamondStringLength,60);
        jLabelDiamondText.setForeground(Color.white);
        jLabelDiamondText.setBackground(Color.BLACK);
        jLabelDiamondText.setFont(bigFont);
        jLabelDiamondText.setText(diamondString);
        jLabelDiamond = new JLabel();
        jLabelDiamond.setBounds(456,29,250,60);
        jLabelDiamond.setBackground(Color.BLACK);
        jLabelDiamond.setForeground(Color.white);
        jLabelDiamond.setFont(bigFont);
        jLabelDiamond.setText("diamond");//TODO tedad diamond kol ro bgir
        panelTotalDiamond.add(jLabelDiamondText);
        panelTotalDiamond.add(jLabelDiamond);

        //rate
        panelGamerRate = new JPanel();
        panelGamerRate.setBounds(50,354,780,118);
        panelGamerRate.setBackground(Color.black);
        jLabelRateText = new JLabel();
        String rateString = "YOUR RATE :";
        int rateStringLength = getFontMetrics(bigFont).stringWidth(rateString);
        jLabelRateText.setBounds(0,29,rateStringLength,60);
        jLabelRateText.setBackground(Color.BLACK);
        jLabelRateText.setForeground(Color.white);
        jLabelRateText.setFont(bigFont);
        jLabelRateText.setText(rateString);
        jLabelRate = new JLabel();
        jLabelRate.setBounds(456,29,250,60);
        jLabelRate.setBackground(Color.BLACK);
        jLabelRate.setForeground(Color.white);
        jLabelRate.setFont(bigFont);
        jLabelRate.setText("diamond");//TODO tedad diamond kol ro bgir
        panelGamerRate.add(jLabelRateText);
        panelGamerRate.add(jLabelRate);


        //table
        panelScoresTable = new JPanel();
        panelScoresTable.setBounds(50,472,1218,118);
        panelScoresTable.setBackground(Color.black);
        jLabelTableText = new JLabel();
        String tableString = "SCORE'S TABLE :";
        int tableStringLength = getFontMetrics(bigFont).stringWidth(tableString);
        jLabelTableText.setBounds(0,29,tableStringLength,60);
        jLabelTableText.setBackground(Color.BLACK);
        jLabelTableText.setForeground(Color.white);
        jLabelTableText.setFont(bigFont);
        jLabelTableText.setText(tableString);
        buttonOnlineTable = new JButton();
        buttonOnlineTable.setBounds(630,30,264,58);
        buttonOnlineTable.setBackground(Color.GRAY);
        buttonOnlineTable.setForeground(Color.black);
        buttonOnlineTable.setFont(smallFont);
        buttonOnlineTable.setText("ONLINE GAMES");
        buttonOnlineTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()== buttonOnlineTable){
                    //TODO brgrde aqab
                }}
        });
        buttonOfflineTable = new JButton();
        buttonOfflineTable.setBounds(934,30,264,58);
        buttonOfflineTable.setBackground(Color.GRAY);
        buttonOfflineTable.setForeground(Color.black);
        buttonOfflineTable.setFont(smallFont);
        buttonOfflineTable.setText("OFFLINE GAMES");
        buttonOfflineTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()== buttonOfflineTable){
                    //TODO brgrde aqab
                }}
        });
        panelScoresTable.add(jLabelTableText);
        panelScoresTable.add(buttonOnlineTable);
        panelScoresTable.add(buttonOfflineTable);

        //back
        panelBack = new JPanel();
        panelBack.setBounds(50,590,1218,118);
        panelBack.setBackground(Color.black);
        buttonBack = new JButton();
        buttonBack.setBounds(0,29,203,60);
        buttonBack.setBackground(Color.BLACK);
        buttonBack.setForeground(Color.red);
        buttonBack.setFont(smallFont);
        buttonBack.setText("BACK");
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
        panelPic.setBounds(840,50,400,329);
        panelPic.setBackground(Color.black);
        ImageIcon profilImg = new ImageIcon(profileImg);
        JLabel label = new JLabel(profilImg);
        label.setBounds(0,0,400,329);
        panelPic.add(label);




        //black
        panelBlack = new JPanel();
        panelBlack.setBounds(0,0,50,708);
        panelBlack.setBackground(Color.black);



        this.add(panelUsername);
        this.add(panelGamerRate);
        this.add(panelTotalCoin);
        this.add(panelTotalDiamond);
        this.add(panelPic);
        this.add(panelScoresTable);
        this.add(panelBack);
        this.add(panelBlack);




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
