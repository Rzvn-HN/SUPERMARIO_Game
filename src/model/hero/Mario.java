package model.hero;

import manager.Camera;
import manager.GameEngine;
import manager.InputManager;
import model.Map;
import view.Animation;
import model.GameObject;
import view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Mario extends GameObject{

    private int remainingLives;
    private int remainingTime;

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;


    private int level;
    private int coins;
    private int score;

    boolean isMoving;

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public int getMovingDir() {
        return movingDir;
    }

    public void setMovingDir(int movingDir) {
        this.movingDir = movingDir;
    }

    int movingDir;


    private double invincibilityTimer;
    private MarioForm marioForm;
    private boolean toRight = true;

    public Mario(double x, double y){
        super(x, y, null);
        setDimension(48,48);

        remainingLives = 3;
        level = 1;
        score = 0;
        coins = 0;
        invincibilityTimer = 0;

        ImageLoader imageLoader = new ImageLoader();
        BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SMALL);
        BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.SMALL);


        Animation animation = new Animation(leftFrames, rightFrames);

//hammer
        marioForm = new MarioForm(animation, false, false,true);

        setStyle(marioForm.getCurrentStyle(toRight, false, false,false));

    }
    public Mario(){
        super(0,0,null);
    }

    public Mario(String username, double x, double y, int score,int coins,
                 int remainingLives, int remainingTime, boolean isMoving, int movingDir ) {
        super(x,y,null);
        this.username = username;
        this.remainingLives=remainingLives;
        this.remainingTime=remainingTime;
        this.setScore(score);
        this.setCoins(coins);
        this.isMoving=isMoving;
        this.movingDir=movingDir;



    }

    //TODO DOWN MARIO
    @Override
    public void draw(Graphics g){
        boolean movingInX = (getVelX() != 0);
        boolean movingInY = (getVelY() != 0);
        boolean isDown = isSiting();
        if (isDown==true){
            setStyle(marioForm.getCurrentStyle(toRight, movingInX, movingInY,isDown));
            //setY();
            //TODO MARIO DOWN
           // setY(this.getY()+24);
        }
        else {setStyle(marioForm.getCurrentStyle(toRight, movingInX, movingInY,isDown));}



        super.draw(g);
    }



    public void jump(GameEngine engine,double x) {


        if(!isJumping() && !isFalling()){
            setJumping(true);
            setSiting(false);

            setVelY(x);
            engine.playJump();
        }
    }

    public void sit(GameEngine engine) {
        setSiting(true);
        setJumping(false);
       //setVelY();

            engine.playSit();

    }

    public void move(boolean toRight, Camera camera) {
        if(toRight){
            setVelX(5);
        }
        else if(camera.getX() < getX()){
            setVelX(-5);
        }

        this.toRight = toRight;
    }

    public boolean onTouchEnemy(GameEngine engine){

        if(marioForm.isFire()==true){

          //vaqti fire > mega
            ////tabe yedune payin

            engine.shakeCamera();
            marioForm = marioForm.onTouchEnemy(engine.getImageLoader());
            setDimension(48, 96);

            ImageLoader imageLoader = new ImageLoader();
            BufferedImage[] leftFrames = imageLoader.getLeftFrames(MarioForm.SUPER);
            BufferedImage[] rightFrames = imageLoader.getRightFrames(MarioForm.SUPER);


            Animation animation = new Animation(leftFrames, rightFrames);

            marioForm = new MarioForm(animation, true, false,marioForm.isHammer());

        }

        //vaqti mega > mini
         else if( marioForm.isSuper()==true ){
            engine.shakeCamera();
            marioForm = marioForm.onTouchEnemy(engine.getImageLoader());
            setDimension(48, 48);

        }

        // mini
        else {
            remainingLives--;
            engine.playMarioDies();
            return true;
        }

        return false;
    }

    public Fireball fire(){
        return marioForm.fire(toRight, getX(), getY());
    }

    public Hammer hammer(){

        return marioForm.hammer(toRight, getX(), getY());
    }



    public void buyHammer() {
        coins= coins-3;
//TODO DOBARE AVAZESH KON BE 3

    }

    public void buy(int price) {
        coins= coins-price;


    }

    public void acquireLevel() {
        level++;
    }

    public void acquirePoints(int point){
        score = score + point;
    }

    public void acquireCoin(int coin) {
        coins= coins + coin;
    }

    public int getRemainingLives() {
        return remainingLives;
    }

    public void setRemainingLives(int remainingLives) {
        this.remainingLives = remainingLives;
    }

    public int getScore() {
        return score;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setScore(int points) {
        this.score = score;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevels() {
        return level;
    }

    public int getCoins() {
        return coins;
    }

    public MarioForm getMarioForm() {
        return marioForm;
    }

    public void setMarioForm(MarioForm marioForm) {
        this.marioForm = marioForm;
    }

    public boolean isSuper() {
        return marioForm.isSuper();
    }

    public boolean isHammer() {
        return marioForm.isHammer();
    }

    public boolean getToRight() {
        return toRight;
    }

    public void resetLocation() {
        setVelX(0);
        setVelY(0);
        setX(50);
        setJumping(false);
        setSiting(false);
        setFalling(true);
    }
}
