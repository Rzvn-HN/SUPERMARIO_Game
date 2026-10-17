package model;

import model.brick.Brick;
import model.brick.OrdinaryBrick;
import model.enemy.Bird;
import model.enemy.Bomb;
import model.enemy.Enemy;
import model.hero.Fireball;
import model.hero.Hammer;
import model.hero.Mario;
import model.prize.BoostItem;
import model.prize.Coin;
import model.prize.Prize;
import start.GamerMP;
import view.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Map {
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    int level;

    private double remainingTime;
    private Mario mario;
    private ArrayList<Brick> bricks = new ArrayList<>();
    private ArrayList<Brick> pipes = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Brick> groundBricks = new ArrayList<>();
    private ArrayList<Prize> revealedPrizes = new ArrayList<>();
    private ArrayList<Brick> revealedBricks = new ArrayList<>();
    private ArrayList<Fireball> fireballs = new ArrayList<>();
    private ArrayList<Hammer> hammers = new ArrayList<>();

    public ArrayList<Mario> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Mario> players) {
        this.players = players;
    }

    private ArrayList<Mario> players = new ArrayList<>();
    private EndFlag endPoint;
    private BufferedImage backgroundImage;
    private double bottomBorder = 720 - 96;
    private String path;



    ImageLoader imageLoader = new ImageLoader();
    BufferedImage bomb = imageLoader.loadImage("/Bomb.png");






    public Map(double remainingTime, BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
        this.remainingTime = remainingTime;
    }


    public Mario getMario() {
        return mario;
    }

    public void setMario(Mario mario) {
        this.mario = mario;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<Fireball> getFireballs() {
        return fireballs;
    }

    public ArrayList<Hammer> getHammers() {
        return hammers;
    }

    public ArrayList<Prize> getRevealedPrizes() {
        return revealedPrizes;
    }

    public ArrayList<Brick> getAllBricks() {
        ArrayList<Brick> allBricks = new ArrayList<>();

        allBricks.addAll(bricks);
        allBricks.addAll(groundBricks);

        return allBricks;
    }

    public ArrayList<Brick> getAllPipes() {
        ArrayList<Brick> allPipes = new ArrayList<>();

        allPipes.addAll(pipes);


        return allPipes;
    }

    public void addBrick(Brick brick) {
        this.bricks.add(brick);
    }


    public void addGroundBrick(Brick brick) {
        this.groundBricks.add(brick);
    }

    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }

    public void addMario(Mario mario) {
        this.players.add(mario);
    }

    public void drawMap(Graphics2D g2){
        drawBackground(g2);
        drawPrizes(g2);
        drawEnemies(g2);
        drawBricks(g2);
        drawFireballs(g2);
        drawHammers(g2);
        drawMarios(g2);
        endPoint.draw(g2);
    }

    public synchronized void movePlayer(String username, int x, int y, int numSteps, boolean isMoving, int movingDir) {
        int index = getGamerMPIndex(username);
        GamerMP player = (GamerMP) this.getPlayers().get(index);
        player.x = x;
        player.y = y;
        player.setMoving(isMoving);
        player.setNumSteps(numSteps);
        player.setMovingDir(movingDir);
    }

    public synchronized void removeGamerMP(String username) {
        int index = 0;
        for (Mario e : getPlayers()) {
            if (e instanceof GamerMP && ((GamerMP) e).getUsername().equals(username)) {
                break;
            }
            index++;
        }
        this.getPlayers().remove(index);
    }

    private int getGamerMPIndex(String username) {
        int index = 0;
        for (Mario e : getPlayers()) {
            if (e instanceof GamerMP && ((GamerMP) e).getUsername().equals(username)) {
                break;
            }
            index++;
        }
        return index;
    }

    private void drawFireballs(Graphics2D g2) {
        for(Fireball fireball : fireballs){
            fireball.draw(g2);
        }
    }

    private void drawHammers(Graphics2D g2) {
        for(Hammer hammer : hammers){
            hammer.draw(g2);
        }
    }

    private void drawMarios(Graphics2D g2) {
        for(Mario mario1 : players){
            mario1.draw(g2);
        }
    }

    private void drawPrizes(Graphics2D g2) {
        for(Prize prize : revealedPrizes){
            if(prize instanceof Coin){
                ((Coin) prize).draw(g2);
            }
            else if(prize instanceof  BoostItem){
                ((BoostItem) prize).draw(g2);
            }
        }
    }

    private void drawBackground(Graphics2D g2){
        g2.drawImage(backgroundImage, 0, 0, null);
    }

    private void drawBricks(Graphics2D g2) {
        for(Brick brick : bricks ) {
            if(brick != null)
                brick.draw(g2);
        }

        for(Brick brick : groundBricks){
            brick.draw(g2);
        }
    }

    private void drawEnemies(Graphics2D g2) {
        for (Enemy enemy : enemies) {

            if (enemy != null) {
                enemy.draw(g2);
            }
        }
    }

    private void drawMario(Graphics2D g2) {
        mario.draw(g2);
    }

    public void updateLocations() {


        for(Mario mario : players) {
            mario.updateLocation();
        }
            for(Enemy enemy : enemies){

            if (enemy instanceof Bird){

                enemy.updateLocation();

                /*Enemy enemy1 = new Bomb(enemy.getX(),enemy.getY(),bomb);
                addEnemy(enemy1);*/
//TODO

            }
            else {

                enemy.updateLocation();
            }

        }

        for(Iterator<Prize> prizeIterator = revealedPrizes.iterator(); prizeIterator.hasNext();){
            Prize prize = prizeIterator.next();
            if(prize instanceof Coin){
                ((Coin) prize).updateLocation();
                if(((Coin) prize).getRevealBoundary() > ((Coin) prize).getY()){
                    prizeIterator.remove();
                }
            }
            else if(prize instanceof BoostItem){
                ((BoostItem) prize).updateLocation();
            }
        }

        for (Fireball fireball: fireballs) {
            fireball.updateLocation();
        }

        for (Hammer hammer: hammers) {

            int a = 0;
            int b = 0;


            if (a == 0 && (hammer.getX()) <= (mario.getX() + (6 * 48))) {
                hammer.updateLocation();
                if ((hammer.getX()) == (mario.getX() + (6 * 48))) {
                    a = 1;
                }
            } else {
                hammer.setVelX(-3);
                removeHammer(hammer);

               /*
               System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println(hammer.getX() + "     " + mario.getX());

                if (b == 0 && hammer.getX() >= mario.getX()) {
                    System.out.println("@@@@@@@@@");

                    if (hammer.get) {
                        hammer.setVelX(0);
                        removeHammer(hammer);
                    } else {
                        hammer.setVelX(-3);
                    }
                    hammer.updateLocation(hammer);
                }
                */


            }
                //if (hammer.getX() != mario.getX())


                //hammer.comeBack(hammer);

           /* while ((hammer.getX()) > (mario.getX())){

                removeHammer(hammer);
            }*/


            }


  /*         while(hammer.getX()!=mario.getX()+(5*48)){
               hammer.updateLocation(hammer);

           }
           */

        for(Iterator<Brick> brickIterator = revealedBricks.iterator(); brickIterator.hasNext();){
            OrdinaryBrick brick = (OrdinaryBrick)brickIterator.next();
            brick.animate();
            if(brick.getFrames() < 0){
                bricks.remove(brick);
                brickIterator.remove();
            }
        }

        endPoint.updateLocation();
    }

    public double getBottomBorder() {
        return bottomBorder;
    }

    public void addRevealedPrize(Prize prize) {
        revealedPrizes.add(prize);
    }

    public void addFireball(Fireball fireball) {
        fireballs.add(fireball);
    }

    public void addHammer(Hammer hammer) {
        hammers.add(hammer);
    }

    public void setEndPoint(EndFlag endPoint) {
        this.endPoint = endPoint;
    }

    public EndFlag getEndPoint() {
        return endPoint;
    }

    public void addRevealedBrick(OrdinaryBrick ordinaryBrick) {
        revealedBricks.add(ordinaryBrick);
    }

    public void removeFireball(Fireball object) {
        fireballs.remove(object);
    }

    public void removeHammer(Hammer object) {
        hammers.remove(object);
    }

    public void removeEnemy(Enemy object) {
        enemies.remove(object);
    }

    public void removePrize(Prize object) {
        revealedPrizes.remove(object);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void updateTime(double passed){
        remainingTime = remainingTime - passed;
    }

    public boolean isTimeOver(){
        return remainingTime <= 0;
    }

    public double getRemainingTime() {
        return remainingTime;
    }
}
