package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Store extends JPanel{




    private Font gameFont;
    private JButton buyExplosiveBomb;
    private JButton buySpeedBomb;
    private JButton buyHammer;
    private JButton buySword;
    private JButton buySpeedPotion;
    private JButton buyInvisibilityPotion;
    private JButton buyHealingPotion;
    private JButton buttonBack;
    private JButton buttonChanging;

    private JLabel jLabelUsername;
    private JLabel jLabelDiamond;
    private JLabel jLabelCoin;
    private JLabel jLabelExplosiveBomb;
    private JLabel jLabelSpeedBomb;
    private JLabel jLabelHammer;
    private JLabel jLabelSword;
    private JLabel jLabelSpeedPotion;
    private JLabel jLabelInvisibilityPotion;
    private JLabel jLabelHealingPotion;
    private JLabel jLabelItems;
    private JLabel jLabelExpBombNum;
    private JLabel jLabelSpeedBombNum;
    private JLabel jLabelHammerNum;
    private JLabel jLabelSwordNum;
    private JLabel jLabelSpeedPNum;
    private JLabel jLabelInvisPNum;
    private JLabel jLabelHealPNum;



    private JLabel jLabelExpBombName;
    private JLabel jLabelSpeedBombName;
    private JLabel jLabelHammerName;
    private JLabel jLabelSwordName;
    private JLabel jLabelSpeedPName;
    private JLabel jLabelInvisPName;
    private JLabel jLabelHealPName;



    private JPanel panelUsername;
    private JPanel panelDiamond;
    private JPanel panelCoin;
    private JPanel panelBack;
    private JPanel panelChangingCurrency;
    private JPanel panel;
    private JPanel panelExplosiveBomb;
    private JPanel panelSpeedBomb;
    private JPanel panelHammer;
    private JPanel panelSword;
    private JPanel panelSpeedPotion;
    private JPanel panelInvisibilityPotion;
    private JPanel panelHealingPotion;


    public Store() {

        this.setBackground(Color.black);
        this.setLayout(null);
        this.setSize(1268, 708);
        this.setVisible(true);


        initComponents();




    }

    private void initComponents() {


        //username
        panelUsername = new JPanel();
        panelUsername.setLocation(0,0);
        panelUsername.setBounds(0,0,422,94);
        panelUsername.setBackground(Color.black);
        Image name1Icon = loadImage("/unknownUserStore.png");
        ImageIcon nameImg = new ImageIcon(name1Icon);
        JLabel nameIcon = new JLabel(nameImg);
        nameIcon.setBounds(92,2,57,90);
        jLabelUsername = new JLabel();
        jLabelUsername.setText("name");//TODO username ro bgir
        jLabelUsername.setBackground(Color.black);
        jLabelUsername.setForeground(Color.white);
        jLabelUsername.setBounds(212,2,212,90);
        panelUsername.add(nameIcon);
        panelUsername.add(jLabelUsername);

        //diamond
        panelDiamond = new JPanel();
        panelDiamond.setLocation(0,0);
        panelDiamond.setBounds(422,0,424,94);
        panelDiamond.setBackground(Color.black);
        Image diamondsIcon = loadImage("/diamondPack.png");
        ImageIcon diamondsImg = new ImageIcon(diamondsIcon);
        JLabel diamondIcon = new JLabel( diamondsImg);
        diamondIcon.setBounds(92,2,90,90);
        jLabelDiamond = new JLabel();
        jLabelDiamond.setText("diamond");//TODO diamond ro bgir
        jLabelDiamond.setBackground(Color.black);
        jLabelDiamond.setForeground(Color.white);
        jLabelDiamond.setBounds(212,2,212,90);
        panelDiamond.add(diamondIcon);
        panelDiamond.add(jLabelDiamond);

        Image AdiamondImg = loadImage("/Adiamon.png");
        ImageIcon AdiamondsIcon = new ImageIcon(AdiamondImg);


        //coin
        panelCoin = new JPanel();
        panelCoin.setLocation(846,0);
        panelCoin.setBounds(846,0,422,94);
        panelCoin.setBackground(Color.black);
        Image coinsImg = loadImage("/coinStore.png");
        ImageIcon coinsIcon = new ImageIcon(coinsImg);
        JLabel coinIcon = new JLabel( coinsIcon);
        coinIcon.setBounds(92,2,90,90);
        jLabelCoin = new JLabel();
        jLabelCoin.setText("diamond");//TODO coin ro bgir
        jLabelCoin.setBackground(Color.black);
        jLabelCoin.setForeground(Color.white);
        jLabelCoin.setBounds(212,2,212,90);
        panelCoin.add(coinIcon);
        panelCoin.add(jLabelDiamond);

        //explodingBomb
        panelExplosiveBomb = new JPanel();
        panelExplosiveBomb.setBounds(0,94,317,260);
        panelExplosiveBomb.setBackground(Color.black);
        jLabelExpBombNum = new JLabel();
        jLabelExpBombNum.setBackground(Color.black);
        jLabelExpBombNum.setForeground(Color.orange);
        jLabelExpBombNum.setBounds(218,42,22,22);
        jLabelExpBombNum.setText("0");//TODO tedadi k exp bomb ro bgir
        jLabelExpBombName = new JLabel();
        jLabelExpBombName.setBackground(Color.black);
        jLabelExpBombName.setForeground(Color.red);
        jLabelExpBombName.setBounds(90,154,137,42);
        jLabelExpBombName.setText("Explosive Bomb");
        //ImageIcon explosiveBombIcon = new ImageIcon("");
        Image explosiveBombImg = loadImage("/ExpBomb.png");
        ImageIcon explosiveBombIcon = new ImageIcon(explosiveBombImg);
        JLabel eplBombIcon = new JLabel(explosiveBombIcon);
        eplBombIcon.setBounds(84,64,80,88);
        buyExplosiveBomb = new JButton();
        buyExplosiveBomb.setIcon(AdiamondsIcon);
        buyExplosiveBomb.setBackground(Color.black);
        buyExplosiveBomb.setForeground(Color.orange);
        buyExplosiveBomb.setBounds(90,198,137,42);
        buyExplosiveBomb.setText("Buy");//TODO cost exp bomb ro bgir
        panelExplosiveBomb.add(eplBombIcon);
        panelExplosiveBomb.add(jLabelExpBombNum);
        panelExplosiveBomb.add(jLabelExpBombName);
        panelExplosiveBomb.add(buyExplosiveBomb);

        //speedBomb
        panelSpeedBomb = new JPanel();
        panelSpeedBomb.setBounds(317,94,317,260);
        panelSpeedBomb.setBackground(Color.black);
        jLabelSpeedBombNum = new JLabel();//darayi spBomb az qabl
        jLabelSpeedBombNum.setBackground(Color.black);
        jLabelSpeedBombNum.setForeground(Color.orange);
        jLabelSpeedBombNum.setBounds(218,42,22,22);
        jLabelSpeedBombNum.setText("0");//TODO tedadi k exp bomb ro bgir
        jLabelSpeedBombName = new JLabel();//darayi spBomb az qabl
        jLabelSpeedBombName.setBackground(Color.black);
        jLabelSpeedBombName.setForeground(Color.orange);
        jLabelSpeedBombName.setBounds(90,154,137,42);
        jLabelSpeedBombName.setText("Speed Bomb");
        Image speedBombImg = loadImage("/speedBomb.png");
        ImageIcon speedBombIcon = new ImageIcon(speedBombImg);
        JLabel speedyBombIcon = new JLabel(speedBombIcon);
        speedyBombIcon.setBounds(84,64,68,88);
        buySpeedBomb = new JButton();
        buySpeedBomb.setIcon(AdiamondsIcon);
        buySpeedBomb.setBackground(Color.black);
        buySpeedBomb.setForeground(Color.orange);
        buySpeedBomb.setBounds(90,198,137,42);
        buySpeedBomb.setText("buy");//TODO cost speed bomb ro bgir
        panelSpeedBomb.add(jLabelSpeedBombNum);
        panelSpeedBomb.add(jLabelSpeedBombName);
        panelSpeedBomb.add(speedyBombIcon);
        panelSpeedBomb.add(buyExplosiveBomb);

        //Hammer
        panelHammer = new JPanel();
        panelHammer.setBounds(634,94,317,260);
        panelHammer.setBackground(Color.black);
        jLabelHammerNum = new JLabel(); //darayi hammer az qabl
        jLabelHammerNum.setBackground(Color.black);
        jLabelHammerNum.setForeground(Color.orange);
        jLabelHammerNum.setBounds(218,42,22,22);
        jLabelHammerNum.setText("0");//TODO tedadi hammer ro bgir
        jLabelHammerName = new JLabel(); //darayi hammer az qabl
        jLabelHammerName.setBackground(Color.black);
        jLabelHammerName.setForeground(Color.orange);
        jLabelHammerName.setBounds(90,154,137,42);
        jLabelHammerName.setText("Hammer");//
        Image hammerImg = loadImage("/Hammer.png");
        ImageIcon hammer1Icon = new ImageIcon(hammerImg);
        JLabel hammerIcon = new JLabel(hammer1Icon);
        hammerIcon.setBounds(84,64,96,96);
        buyHammer = new JButton(); // kharid hammer
        buyHammer.setIcon(AdiamondsIcon);
        buyHammer.setBackground(Color.black);
        buyHammer.setForeground(Color.orange);
        buyHammer.setBounds(90,198,137,42);
        buyHammer.setText("buy");//TODO cost hammer ro bgir
        panelHammer.add(jLabelHammerNum);
        panelHammer.add(jLabelHammerName);
        panelHammer.add(hammerIcon);
        panelHammer.add(buyHammer);

        //sword
        panelSword = new JPanel();
        panelSword.setBounds(951,94,317,260);
        panelSword.setBackground(Color.black);
        jLabelSwordNum = new JLabel(); //darayi sword az qabl
        jLabelSwordNum.setBackground(Color.black);
        jLabelSwordNum.setForeground(Color.orange);
        jLabelSwordNum.setBounds(218,42,22,22);
        jLabelSwordNum.setText("0");//TODO darayi sword ro bgir
        jLabelSwordName = new JLabel();
        jLabelSwordName.setBackground(Color.black);
        jLabelSwordName.setForeground(Color.orange);
        jLabelSwordName.setBounds(90,154,137,42);
        jLabelSwordName.setText("Sword");
        Image sword1Img = loadImage("/sword.png");
        ImageIcon sword1Icon = new ImageIcon(sword1Img);
        JLabel swordIcon = new JLabel(sword1Icon);
        swordIcon.setBounds(84,64,68,88);
        buySword = new JButton(); //
        buySword.setIcon(AdiamondsIcon);
        buySword.setBackground(Color.black);
        buySword.setForeground(Color.orange);
        buySword.setBounds(90,198,137,42);
        buySword.setText("buy");//TODO qeymat sword ro bgir
        panelSword.add(jLabelSwordNum);
        panelSword.add(jLabelSwordName);
        panelSword.add(swordIcon);
        panelSword.add(buySword);

        //speed pot
        panelSpeedPotion = new JPanel();
        panelSpeedPotion.setBounds(0,354,317,260);
        panelSpeedPotion.setBackground(Color.black);
        jLabelSpeedPNum = new JLabel(); //darayi sword az qabl
        jLabelSpeedPNum.setBackground(Color.black);
        jLabelSpeedPNum.setForeground(Color.orange);
        jLabelSpeedPNum.setBounds(218,42,22,22);
        jLabelSpeedPNum.setText("0");//TODO darayi speed pot ro bgir
        jLabelSpeedPName = new JLabel();
        jLabelSpeedPName.setBackground(Color.black);
        jLabelSpeedPName.setForeground(Color.orange);
        jLabelSpeedPName.setBounds(90,154,137,42);
        jLabelSpeedPName.setText("Speed Pot");
        Image speedPotImg = loadImage("/speedPot.png");
        ImageIcon speedPotIcon = new ImageIcon(speedPotImg);
        JLabel speedPotionIcon = new JLabel(speedPotIcon);
        speedPotionIcon.setBounds(84,64,75,88);
        buySpeedPotion = new JButton(); // kharid hammer
        buySpeedPotion.setIcon(AdiamondsIcon);
        buySpeedPotion.setBackground(Color.black);
        buySpeedPotion.setForeground(Color.orange);
        buySpeedPotion.setBounds(90,198,137,42);
        buySpeedPotion.setText("0");//TODO qeymat speedpot ro bgir
        panelSpeedPotion.add(jLabelSpeedPNum);
        panelSpeedPotion.add(jLabelSpeedPName);
        panelSpeedPotion.add(speedPotionIcon);
        panelSpeedPotion.add(buySpeedPotion);

        //invis pot
        panelInvisibilityPotion = new JPanel();
        panelInvisibilityPotion.setBounds(317,354,317,260);
        panelInvisibilityPotion.setBackground(Color.black);
        jLabelInvisPNum = new JLabel();
        jLabelInvisPNum.setBackground(Color.black);
        jLabelInvisPNum.setForeground(Color.orange);
        jLabelInvisPNum.setBounds(218,42,22,22);
        jLabelInvisPNum.setText("0");//TODO invis pot ro bgir
        jLabelInvisPName = new JLabel();
        jLabelInvisPName.setBackground(Color.black);
        jLabelInvisPName.setForeground(Color.orange);
        jLabelInvisPName.setBounds(90,154,137,42);
        jLabelInvisPName.setText("Invisiblity Pot");
        Image invPotionImg = loadImage("/invisPot.png");
        ImageIcon invPotionIcon = new ImageIcon(invPotionImg);
        JLabel invisPotionIcon = new JLabel( invPotionIcon);
        invisPotionIcon.setBounds(84,64,75,88);
        buyInvisibilityPotion = new JButton();
        buyInvisibilityPotion.setIcon(AdiamondsIcon);
        buyInvisibilityPotion.setBackground(Color.black);
        buyInvisibilityPotion.setForeground(Color.orange);
        buyInvisibilityPotion.setBounds(90,198,137,42);
        buyInvisibilityPotion.setText("buy");//TODO  cost invis pot ro bgir
        panelInvisibilityPotion.add(jLabelInvisPNum);
        panelInvisibilityPotion.add(jLabelInvisPName);
        panelInvisibilityPotion.add(invisPotionIcon);
        panelInvisibilityPotion.add(buyInvisibilityPotion);

        //heal pot
        panelHealingPotion = new JPanel();
        panelHealingPotion.setBounds(634,354,317,260);
        panelHealingPotion.setBackground(Color.black);
        jLabelHealPNum = new JLabel();
        jLabelHealPNum.setText("0");//TODO invis pot ro bgir
        jLabelHealPNum.setBackground(Color.black);
        jLabelHealPNum.setForeground(Color.orange);
        jLabelHealPNum.setBounds(218,42,22,22);
        jLabelHealPName = new JLabel();
        jLabelHealPName.setBackground(Color.black);
        jLabelHealPName.setForeground(Color.orange);
        jLabelHealPName.setBounds(90,154,137,42);
        jLabelHealPName.setText("Healing Pot");
        Image healingPotImg = loadImage("/healingPot.png");
        ImageIcon healingPotIcon = new ImageIcon(healingPotImg);
        JLabel healPotIcon = new JLabel( healingPotIcon);
        healPotIcon.setBounds(84,64,68,66);
        buyHealingPotion = new JButton();
        buyHealingPotion.setIcon(AdiamondsIcon);
        buyHealingPotion.setBackground(Color.black);
        buyHealingPotion.setForeground(Color.orange);
        buyHealingPotion.setBounds(90,198,137,42);
        buyHealingPotion.setText("0");//TODO  cost invis pot ro bgir
        panelHealingPotion.add(jLabelHealPNum);
        panelHealingPotion.add(jLabelHealPName);
        panelHealingPotion.add(healPotIcon);
        panelHealingPotion.add(buyHealingPotion);

        //nothing
//        panelHealingPotion = new JPanel();
//        panelHealingPotion.setBounds(951,354,317,260);
//        panelHealingPotion.setBackground(Color.black);
//        jLabelHealPNum = new JLabel();
//        jLabelHealPNum.setText("0");//TODO invis pot ro bgir
//        ImageIcon healPotIcon = new ImageIcon("");
//        buyHealingPotion = new JButton();
//        buyHealingPotion.setIcon(diamondIcon);
//        buyHealingPotion.setText("0");//TODO  cost invis pot ro bgir
//        panelHealingPotion.add(jLabelHealPNum);
//        panelHealingPotion.add(new JLabel(healPotIcon));
//        panelHealingPotion.add(buyHealingPotion);

        //back
        panelBack = new JPanel();
        panelBack.setLocation(846,0);
        panelBack.setBounds(0,614,422,94);
        buttonBack = new JButton();
        buttonBack.setText("Back");//TODO action listen fqt
        buttonBack.setBackground(Color.black);
        buttonBack.setForeground(Color.red);
        buttonBack.setBounds(100,2,222,90);
        panelBack.add(buttonBack);
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()== buttonBack){
                    //TODO brgrde aqab
                }}
        });




        //changing diamond
        panelChangingCurrency = new JPanel();
        panelChangingCurrency.setLocation(846,0);
        panelChangingCurrency.setBounds(422,614,422,94);
        buttonChanging = new JButton();
        buttonChanging.setText("ChangingCurrency");//TODO action listen fqt
        buttonBack.setBackground(Color.black);
        buttonBack.setForeground(Color.pink);
        buttonBack.setBounds(522,2,222,90);
        panelChangingCurrency.add(buttonChanging);

        //nothing
//        panelBack = new JPanel();
//        panelBack.setLocation(846,0);
//        panelBack.setBounds(0,614,422,94);
//        jLabelBack = new JLabel();
//        jLabelBack.setText("Back");//TODO action listen fqt
//        panelCoin.add(jLabelBack);




        this.add(panelUsername);
        this.add(panelDiamond);
        this.add(panelCoin);
        this.add(panelExplosiveBomb);
        this.add(panelSpeedBomb);
        this.add(panelHammer);
        this.add(panelSword);
        this.add(panelSpeedPotion);
        this.add(panelInvisibilityPotion);
        this.add(panelHealingPotion);
        this.add(panelBack);
        this.add(panelChangingCurrency);



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
