package manager;

import model.GameObject;
import model.Map;
import model.brick.*;
import model.enemy.*;
import manager.GameEngine;
import model.hero.Fireball;
import model.hero.Hammer;
import model.hero.Mario;
import model.prize.BoostItem;
import model.prize.Coin;
import model.prize.Prize;
import start.Gamer;
import view.ImageLoader;
import view.UIManager;

import java.awt.*;
import java.util.ArrayList;

import static manager.GameStatus.ERROR_SCREEN;

public class MapManager {

    private Map map;




    public int getLevel() {
        return map.getLevel();
    }

    public void setLevel(int level) {
        map.setLevel(level);
    }







    public MapManager() {}

    public void updateLocations() {
        if (map == null)
            return;

        map.updateLocations();
    }

    private Gamer MAPGamer;
    public void resetCurrentMap(GameEngine engine,Gamer gamer) {
        this.MAPGamer = gamer;
        Mario mario = getMario();
        mario.resetLocation();
        engine.resetCamera();


        createMap(engine.getImageLoader(), getLevel(),map.getRemainingTime(),false,gamer);
        map.setMario(mario);
    }

    public String getPath(){
        return map.getPath();
    }

    public boolean createMap(ImageLoader loader, int level , double time,boolean  isResumeGame,Gamer gamer) {
        MapCreator mapCreator = new MapCreator(loader);
        String path =  String.valueOf(level);
        map = mapCreator.createMap("/maps/"+"Map "+path+".png", time, isResumeGame , gamer);


        return map != null;


    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void acquirePoints(int point) {
        map.getMario().acquirePoints(point);
    }

    public Mario getMario() {
        return map.getMario();
    }

    public void setMario(Mario mario) {
        map.setMario(mario) ;
    }

    public void fire(GameEngine engine) {
        Fireball fireball = getMario().fire();
        if (fireball != null) {
            map.addFireball(fireball);
            engine.playFireball();
        }
    }

    public void hammer(GameEngine engine) {

        if (checkBuying(engine)){
            Hammer hammer = getMario().hammer();
            if (hammer != null) {
                map.addHammer(hammer);
                engine.playHammer();
                //TODO
            }

        }

    }

    public boolean isGameOver() {
        return getMario().getRemainingLives() == 0 || map.isTimeOver();
    }

    public int getScore() {
        return getMario().getScore();
    }

    public int getRemainingLives() {
        return getMario().getRemainingLives();
    }


    public int getRemainingLevels() {
        return getMario().getLevels();
    }

    public int getCoins() {
        return getMario().getCoins();
    }

    public void drawMap(Graphics2D g2) {
        map.drawMap(g2);
    }

    public int passMission() {
        if(getMario().getX() >= map.getEndPoint().getX() && !map.getEndPoint().isTouched()){
            map.getEndPoint().setTouched(true);
            int height = (int)getMario().getY();
            return height * 2;

        }
        else
            return -1;
    }

    public boolean endLevel(){
        return getMario().getX() >= map.getEndPoint().getX() + 320;

    }

    public int PR(){
        return ((int)Math.ceil(getMario().getX() / map.getEndPoint().getX())* getMario().getCoins());
    }

    public void checkCollisions(GameEngine engine) {
        if (map == null) {
            return;
        }

        checkBottomCollisions(engine);
        checkTopCollisions(engine);
        checkMarioHorizontalCollision(engine);
        checkEnemyCollisions();
        checkPrizeCollision();
        checkPrizeContact(engine);
        checkFireballContact();
        checkHammerContact();

    }

    //TODO
    private boolean checkBuying (GameEngine engine) {
        Mario mario = getMario();

        if (engine.getCoins()>2){
            mario.buyHammer();
            return true;
        }
        else{
            engine.setGameStatus(GameStatus.ERROR_SCREEN);
            return false;

        }
    }
    private void checkBottomCollisions(GameEngine engine) {
        Mario mario = getMario();
        ArrayList<Brick> bricks = map.getAllBricks();
        ArrayList<Enemy> enemies = map.getEnemies();
        ArrayList<GameObject> toBeRemoved = new ArrayList<>();

        Rectangle marioBottomBounds = mario.getBottomBounds();

        if (!mario.isJumping())
            mario.setFalling(true);

        for (Brick brick : bricks) {


            Rectangle brickTopBounds = brick.getTopBounds();
            boolean marioDies = false;
            if (marioBottomBounds.intersects(brickTopBounds)) {

                if (mario.isSiting()){

                    if (brick instanceof Black) {


                        marioDies = mario.onTouchEnemy(engine);

                    } else if (brick instanceof CheckPoint) {



                        mario.setY(brick.getY() - mario.getDimension().height + 1);

                        mario.setFalling(false);
                        mario.setVelY(0);
                        engine.setGameStatus(GameStatus.CHECKPOINT_SCREEN);
                        engine.setPR(this);
                        //TODO safhe baz kon va save kon ta oonja va vaqti mario die azunja shoru beshe
                        mario.setX(mario.getX()+50);

                    } else if (brick instanceof SlimeBrick) {

                        mario.setY(brick.getY() - mario.getDimension().height + 1);
                        //mario.setVelY(0);
                        mario.setFalling(false);
                        mario.jump(engine, 20);
                        engine.playJump();

                    }
                    else if (brick instanceof WhitePipe) {

                        //  mario.setY(brick.getY() - mario.getDimension().height + 1);
                        // mario.setFalling(false);
                        //mario.setVelY(0);

                        // ImageLoader loader = new ImageLoader() ;
                   /* String path = "Map 4.png";
                    if (path != null) {
                        createMap(path);
                    }*/
                        mario = getMario();
                        // mapManager.createMap(this.getImageLoader(),"Map 2.png" );
                        mario.resetLocation();
                        engine.resetCamera();

                        createMap(engine.getImageLoader(),4,map.getRemainingTime(),engine.getIsResumeGame(),MAPGamer);
                        setMario(mario);
//TODO




                    }
                    else {

                        mario.setY(brick.getY() - mario.getDimension().height + 48);
                        mario.setFalling(false);
                        mario.setVelY(0);
                    }


                    if (marioDies) {
                        resetCurrentMap(engine,MAPGamer);
                    }
                }



                else{

                    if (brick instanceof Black) {


                        marioDies = mario.onTouchEnemy(engine);

                    } else if (brick instanceof CheckPoint) {



                        mario.setY(brick.getY() - mario.getDimension().height + 1);

                        mario.setFalling(false);
                        mario.setVelY(0);
                        engine.setGameStatus(GameStatus.CHECKPOINT_SCREEN);
                        engine.setPR(this);
                        //TODO safhe baz kon va save kon ta oonja va vaqti mario die azunja shoru beshe
                        mario.setX(mario.getX()+50);

                    } else if (brick instanceof SlimeBrick) {

                        mario.setY(brick.getY() - mario.getDimension().height + 1);
                        //mario.setVelY(0);
                        mario.setFalling(false);
                        mario.jump(engine, 20);
                        engine.playJump();

                    }
                    else if (brick instanceof WhitePipe) {

                        //  mario.setY(brick.getY() - mario.getDimension().height + 1);
                        // mario.setFalling(false);
                        //mario.setVelY(0);

                        // ImageLoader loader = new ImageLoader() ;
                   /* String path = "Map 4.png";
                    if (path != null) {
                        createMap(path);
                    }*/
                        mario = getMario();
                        // mapManager.createMap(this.getImageLoader(),"Map 2.png" );
                        mario.resetLocation();
                        engine.resetCamera();

                        createMap(engine.getImageLoader(),4,map.getRemainingTime(),engine.getIsResumeGame(),MAPGamer);
                        setMario(mario);
//TODO




                    }
                    else {

                        mario.setY(brick.getY() - mario.getDimension().height + 1);
                        mario.setFalling(false);
                        mario.setVelY(0);
                    }


                    if (marioDies) {
                        resetCurrentMap(engine,MAPGamer);
                    }

                }




            }
        }

        for (Enemy enemy : enemies) {
            Rectangle enemyTopBounds = enemy.getTopBounds();
            boolean marioDies = false;

            if (marioBottomBounds.intersects(enemyTopBounds)) {

            if (enemy instanceof CannibalFlower){
                marioDies = mario.onTouchEnemy(engine);}

            else if (enemy instanceof Goomba){
                    mario.acquirePoints(1);
                    toBeRemoved.add(enemy);
                    engine.playStomp();
            }
            else if (enemy instanceof KoopaTroopa){
                mario.acquirePoints(2);
                toBeRemoved.add(enemy);
                engine.playStomp();
            }
            else if (enemy instanceof Spikey){
                marioDies = mario.onTouchEnemy(engine);
            }
            else if (enemy instanceof Bird){
                marioDies = mario.onTouchEnemy(engine);
            }

            }

            if(marioDies) {
                resetCurrentMap(engine,MAPGamer);
            }

        }

        if (mario.getY() + mario.getDimension().height >= map.getBottomBorder()) {
            mario.setY(map.getBottomBorder() - mario.getDimension().height);
            mario.setFalling(false);
            mario.setVelY(0);
        }

        removeObjects(toBeRemoved);
    }

    private void checkTopCollisions(GameEngine engine) {
        Mario mario = getMario();
        ArrayList<Brick> bricks = map.getAllBricks();

        Rectangle marioTopBounds = mario.getTopBounds();


        for (Brick brick : bricks) {

            Rectangle brickBottomBounds = brick.getBottomBounds();
            if (marioTopBounds.intersects(brickBottomBounds)) {

                mario.setVelY(0);
                mario.setY(brick.getY() + brick.getDimension().height);
                Prize prize = (Prize) brick.reveal(engine);
                if(prize != null)
                    map.addRevealedPrize(prize);
            }
        }
    }

    private void checkMarioHorizontalCollision(GameEngine engine){
        Mario mario = getMario();
        ArrayList<Brick> bricks = map.getAllBricks();
        ArrayList<Enemy> enemies = map.getEnemies();
        ArrayList<GameObject> toBeRemoved = new ArrayList<>();

        boolean marioDies = false;
        boolean toRight = mario.getToRight();

        Rectangle marioBounds = toRight ? mario.getRightBounds() : mario.getLeftBounds();

        for (Brick brick : bricks) {
            Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
            if (marioBounds.intersects(brickBounds)) {
                mario.setVelX(0);
                if(toRight)
                    mario.setX(brick.getX() - mario.getDimension().width);
                else
                    mario.setX(brick.getX() + brick.getDimension().width);
            }
        }

        for(Enemy enemy : enemies){
            Rectangle enemyBounds = !toRight ? enemy.getRightBounds() : enemy.getLeftBounds();
            if (marioBounds.intersects(enemyBounds)) {
                marioDies = mario.onTouchEnemy(engine);
                toBeRemoved.add(enemy);
            }
        }
        removeObjects(toBeRemoved);


        if (mario.getX() <= engine.getCameraLocation().getX() && mario.getVelX() < 0) {
            mario.setVelX(0);
            mario.setX(engine.getCameraLocation().getX());
        }

        if(marioDies) {
            resetCurrentMap(engine,MAPGamer);
        }
    }

    private void checkEnemyCollisions() {
        ArrayList<Brick> bricks = map.getAllBricks();

        ArrayList<Enemy> enemies = map.getEnemies();

        for (Enemy enemy : enemies) {
            boolean standsOnBrick = false;

            for (Brick brick : bricks) {
                Rectangle enemyBounds = enemy.getLeftBounds();
                Rectangle brickBounds = brick.getRightBounds();


                Rectangle enemyBottomBounds = enemy.getBottomBounds();
                Rectangle brickTopBounds = brick.getTopBounds();


                if (enemy instanceof CannibalFlower) {

                    if (enemy.getVelY() < 0) {
                        enemyBounds = enemy.getBottomBounds();
                        brickBounds = brick.getTopBounds();

                        if (enemy.getY() < brick.getY() -230) {
                            enemy.setVelY(-enemy.getVelY());
                        }
                    }
                    else if (enemy.getVelY() > 0){
                        enemyBounds = enemy.getTopBounds();
                        brickBounds = brick.getBottomBounds();


                        if (enemyBounds.intersects(brickBounds)) {
                            enemy.setVelY(-enemy.getVelY());
                        }
                    }

                    enemy.setFalling(false);
                    enemy.setVelX(0);
                }




                else if (enemy instanceof Bird){
                    if (enemy.getVelX() > 0) {


                        enemyBounds = enemy.getRightBounds();
                        brickBounds = brick.getLeftBounds();
                    }

                    if (enemyBounds.intersects(brickBounds)) {
                        enemy.setVelX(-enemy.getVelX());
                    }

                    enemy.setFalling(false);
                    enemy.setVelY(0);
                }


                else {
                    if (enemy.getVelX() > 0) {


                        enemyBounds = enemy.getRightBounds();
                        brickBounds = brick.getLeftBounds();
                    }

                    if (enemyBounds.intersects(brickBounds)) {
                        enemy.setVelX(-enemy.getVelX());
                    }

                    if (enemyBottomBounds.intersects(brickTopBounds)) {
                        enemy.setFalling(false);
                        enemy.setVelY(0);
                        enemy.setY(brick.getY() - enemy.getDimension().height);
                        standsOnBrick = true;
                    }

//
                    if (enemy.getY() + enemy.getDimension().height > map.getBottomBorder()) {
                        enemy.setFalling(false);
                        enemy.setVelY(0);
                        enemy.setY(map.getBottomBorder() - enemy.getDimension().height);
                    }

                    if (!standsOnBrick && enemy.getY() < map.getBottomBorder()) {
                        enemy.setFalling(true);
                    }

                }
            }
        }
    }

    private void checkPrizeCollision() {
        ArrayList<Prize> prizes = map.getRevealedPrizes();
        ArrayList<Brick> bricks = map.getAllBricks();

        for (Prize prize : prizes) {
            if (prize instanceof BoostItem) {
                BoostItem boost = (BoostItem) prize;
                Rectangle prizeBottomBounds = boost.getBottomBounds();
                Rectangle prizeRightBounds = boost.getRightBounds();
                Rectangle prizeLeftBounds = boost.getLeftBounds();
                boost.setFalling(true);

                for (Brick brick : bricks) {
                    Rectangle brickBounds;

                    if (boost.isFalling()) {
                        brickBounds = brick.getTopBounds();

                        if (brickBounds.intersects(prizeBottomBounds)) {
                            boost.setFalling(false);
                            boost.setVelY(0);
                            boost.setY(brick.getY() - boost.getDimension().height + 1);
                            if (boost.getVelX() == 0)
                                boost.setVelX(2);
                        }
                    }

                    if (boost.getVelX() > 0) {
                        brickBounds = brick.getLeftBounds();

                        if (brickBounds.intersects(prizeRightBounds)) {
                            boost.setVelX(-boost.getVelX());
                        }
                    } else if (boost.getVelX() < 0) {
                        brickBounds = brick.getRightBounds();

                        if (brickBounds.intersects(prizeLeftBounds)) {
                            boost.setVelX(-boost.getVelX());
                        }
                    }
                }

                if (boost.getY() + boost.getDimension().height > map.getBottomBorder()) {
                    boost.setFalling(false);
                    boost.setVelY(0);
                    boost.setY(map.getBottomBorder() - boost.getDimension().height);
                    if (boost.getVelX() == 0)
                        boost.setVelX(2);
                }

            }
        }
    }

    private void checkPrizeContact(GameEngine engine) {
        ArrayList<Prize> prizes = map.getRevealedPrizes();
        ArrayList<GameObject> toBeRemoved = new ArrayList<>();

        Rectangle marioBounds = getMario().getBounds();
        for(Prize prize : prizes){
            Rectangle prizeBounds = prize.getBounds();
            if (prizeBounds.intersects(marioBounds)) {
                prize.onTouch(getMario(), engine);
                toBeRemoved.add((GameObject) prize);
            } else if(prize instanceof Coin){
                prize.onTouch(getMario(), engine);
            }
        }

        removeObjects(toBeRemoved);
    }

    private void checkFireballContact() {
        ArrayList<Fireball> fireballs = map.getFireballs();
        ArrayList<Enemy> enemies = map.getEnemies();
        ArrayList<Brick> bricks = map.getAllBricks();
        ArrayList<GameObject> toBeRemoved = new ArrayList<>();

        for(Fireball fireball : fireballs){
            Rectangle fireballBounds = fireball.getBounds();

            for(Enemy enemy : enemies){
                Rectangle enemyBounds = enemy.getBounds();
                if (fireballBounds.intersects(enemyBounds)) {
                    acquirePoints(100);
                    toBeRemoved.add(enemy);
                    toBeRemoved.add(fireball);
                }
            }

            for(Brick brick : bricks){
                Rectangle brickBounds = brick.getBounds();
                if (fireballBounds.intersects(brickBounds)) {
                    toBeRemoved.add(fireball);
                }
            }
        }

        removeObjects(toBeRemoved);
    }

    private void checkHammerContact() {
        Mario mario = getMario();
        ArrayList<Hammer> hammers = map.getHammers();
        ArrayList<Enemy> enemies = map.getEnemies();
        ArrayList<Brick> bricks = map.getAllBricks();
        ArrayList<GameObject> toBeRemoved = new ArrayList<>();

        for(Hammer hammer : hammers){
            Rectangle hammerBounds = hammer.getBounds();
            Rectangle hammerBoundsLeft = hammer.getLeftBounds();


            for(Enemy enemy : enemies){
                Rectangle enemyBounds = enemy.getBounds();
                if (hammerBounds.intersects(enemyBounds)) {
                    acquirePoints(100);
                    toBeRemoved.add(enemy);
                    toBeRemoved.add(hammer);
                }
            }

            for(Brick brick : bricks){
                Rectangle brickBounds = brick.getBounds();
                if (hammerBounds.intersects(brickBounds)) {
                    toBeRemoved.add(hammer);
                }
            }

//TODO planB for removing hammer
            Rectangle marioBounds = mario.getRightBounds();
            if(hammerBoundsLeft.intersects(marioBounds)){
                hammer.setVelX(0);
                toBeRemoved.add(hammer);
            }

        }

        removeObjects(toBeRemoved);
    }

    private void removeObjects(ArrayList<GameObject> list){
        if(list == null)
            return;

        for(GameObject object : list){
            if(object instanceof Fireball){
                map.removeFireball((Fireball)object);
            }
            else if(object instanceof Enemy){
                map.removeEnemy((Enemy)object);
            }
            else if(object instanceof Hammer){
                map.removeHammer((Hammer)object);
            }
            else if(object instanceof Coin || object instanceof BoostItem){
                map.removePrize((Prize)object);
            }
        }
    }

    public void addRevealedBrick(OrdinaryBrick ordinaryBrick) {
        map.addRevealedBrick(ordinaryBrick);
    }

    public void updateTime(){
        if(map != null)
            map.updateTime(1);
    }

    public int getRemainingTime() {
        return (int)map.getRemainingTime();
    }
}
