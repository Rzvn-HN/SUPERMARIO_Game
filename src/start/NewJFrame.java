package start;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import manager.GameEngine;
import manager.GameStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Objects;

public class NewJFrame extends JFrame implements ActionListener {
    private Font gameFont;

    static JFrame frame;

    private boolean isMatch=false;
    public boolean isMatch() {
        return isMatch;
    }

    public void setMatch(boolean match) {
        isMatch = match;
    }






    private BufferedImage helpScreenImage;

   // this.helpScreenImage = loader.loadImage("/help-screen.png");


    public NewJFrame() {


       setBackground(Color.black);


        initComponents();




    }


    private void initComponents() {

        try {
            InputStream in = getClass().getResourceAsStream("/media/font/mario-font.ttf");
            gameFont = Font.createFont(Font.TRUETYPE_FONT, in);
        } catch (FontFormatException | IOException e) {
            gameFont = new Font("Verdana", Font.PLAIN, 12);
            e.printStackTrace();
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        gamerManager = new HashMap<>();





        jLabel1.setFont(gameFont.deriveFont(50f));
        jLabel1.setFont(new Font("m", 0, 36));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("SuperMario Login");

        jLabel2.setFont(gameFont.deriveFont(24f));
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setText("Username");

        jLabel3.setFont(gameFont.deriveFont(24f));
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setText("Password");

        jButton1.setFont(new Font("l", 0, 24));
        jButton1.setText("Login");
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isLogin = true;
                jButton1ActionPerformed(e);
            }
        });




        jButton2.setFont(new Font("s", 0, 24));
        jButton2.setText("SignUp");
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSignup = true;
                jButton2ActionPerformed(e);

            }
        });





        jButton3.setFont(new Font("d", 0, 24));
        jButton3.setText("Delete");
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              //  jButton3ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new Font("ue", 0, 24));

        jTextField2.setFont(new Font("pe", 0, 24));


        //sakhtar bandi
        GroupLayout layout = new GroupLayout(getContentPane());

        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(155, 155, 155)
                                                .addComponent(jLabel1,GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel2,GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                                        .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(66, 66, 66)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jTextField1)
                                                        .addComponent(jTextField2, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(85, 85, 85)
                                                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 125,GroupLayout.PREFERRED_SIZE)
                                                .addGap(45, 45, 45)
                                                .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 125,GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50)
                                                .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 74,GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField1,GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                        .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField2, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58))
        );

        pack();
    }
    protected static void jButton2ActionPerformed(ActionEvent evt){//GEN-FIRST:event_jButton2ActionPerformed

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter file = new FileWriter("./src/data/"+ jTextField1.getText()+".json", true);
            gson.toJson(new UserPass(jTextField1.getText(), jTextField2.getText()), UserPass.class,  file);
            file.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error occured");
        }
        JOptionPane.showMessageDialog(null, "Data Saved");
    }

    protected void jButton1ActionPerformed(ActionEvent evt){


        if(new File("./src/data", jTextField1.getText()+".json").exists()){
            FileReader file = null;
            try {
                file = new FileReader("./src/data/"+jTextField1.getText()+".json");
                Gson gson = new Gson();
                UserPass gamer = gson.fromJson(file, UserPass.class);
                gamer.setPathJsonFile("./src/data/"+jTextField1.getText()+".json");

                if(Objects.equals(gamer.getPassword(), jTextField2.getText())) {
                    JOptionPane.showMessageDialog(null, "Password matched");
                   //TODO
            new GameEngine(new Gamer(jTextField1.getText(),jTextField2.getText()));
//    engine.setGameStatus(GameStatus.START_SCREEN);

                   setMatch(true);
                   if (isMatch()){
//                    System.out.println("mother f");
                    }

                }else {
                    JOptionPane.showMessageDialog(null, "Password Not matched");
                }
                file.close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error Occured While fetching");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            JOptionPane.showMessageDialog(null, "User not found!");
        }}




//    public static void main(String args[]) {
//
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new NewJFrame().setVisible(true);
//            }
//        });
//    }

    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;

    public String getjTextField1() {
        return jTextField1.getText();
    }


    static JTextField jTextField1;

    public String getjTextField2() {
        return jTextField2.getText();
    }

    static JTextField jTextField2;
    static HashMap<String, String> gamerManager;
    boolean isSignup;
    boolean isLogin;

    @Override
    public void actionPerformed(ActionEvent e)     //pass action listener as a parameter
        { if (isSignup==true) {
            String userValue = jTextField1.getText();        //get user entered username from the textField1
            String passValue = jTextField2.getText();//get user entered pasword from the textField2
          //  NewPage page = new NewPage();
            //make page visible to the user
          //  page.setVisible(true);

            class signupDemo {

            }
        }
/////////??????????????
            else if(isLogin==true) {
            String userValue = jTextField1.getText();
            String passValue = jTextField2.getText();

            for (int i = 0; i < gamerManager.size(); i++) {

                if (gamerManager.containsValue(passValue) && gamerManager.containsKey(userValue)) {

                    //create instance of the NewPage

                   // new GameEngine(gamer);


                    //make page visible to the user

                    //create a welcome label and set it to the new page
                    JLabel wel_label = new JLabel("Welcome: " + userValue);
                    //engin.getContentPane().add(wel_label);
                } else {
                  //  NewPage page = new NewPage();
                  //  page.setVisible(true);
                    //show error message

                }
            }
        }


        }

    public static void main(String args[]) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {


                     frame = new NewJFrame();
                     frame.setVisible(true);


            }
        });
    }


    }




