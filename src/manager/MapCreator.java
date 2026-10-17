package manager;

import com.google.gson.Gson;
import model.EndFlag;
import model.brick.*;
import model.enemy.*;
import model.prize.*;
import start.GameState;
import start.Gamer;
import view.ImageLoader;
import model.Map;
import model.hero.Mario;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

class MapCreator {

    private ImageLoader imageLoader;

    private BufferedImage backgroundImage;
    private BufferedImage superMushroom, star, fireFlower, coin;
    private BufferedImage ordinaryBrick, surpriseBrick, groundBrick, pipe,emptyBrick,black,slimeBrick;
    private BufferedImage spikeyLeft,spikeyRight,goombaLeft, goombaRight, koopaLeft, koopaRight, endFlag ,whitePipe,reversePipe,
            openCanFlo ,birdImg ,coinM, checkPoint,bomb;

    public boolean isResumeGame() {
        return isResumeGame;
    }

    public void setisResumeGame(boolean isResumeGame) {
        isResumeGame = isResumeGame;
    }

    private boolean isResumeGame;


    MapCreator(ImageLoader imageLoader) {

        this.imageLoader = imageLoader;



        BufferedImage sprite = imageLoader.loadImage("/sprite.png");
        BufferedImage spikeyImg = imageLoader.loadImage("/Spikey.png");
        BufferedImage canFlo = imageLoader.loadImage("/plant.png");
        BufferedImage black = imageLoader.loadImage("/Black.png");
        BufferedImage birdImg = imageLoader.loadImage("/NukeBird.png");
        BufferedImage checkPoint = imageLoader.loadImage("/Checkpoint.png");
        BufferedImage slimeBrick = imageLoader.loadImage("/SlimeBrick.png");
        BufferedImage reversePipe = imageLoader.loadImage("/reversePipe.png");
        BufferedImage whitePipe = imageLoader.loadImage("/img_3.png");
        BufferedImage coinM = imageLoader.loadImage("/Coin.png");
        this.backgroundImage = imageLoader.loadImage("/background.png");
        this.black = black.getSubimage( 0,0 , 96, 96);
        this.spikeyLeft = spikeyImg.getSubimage( 0,0 , 48, 48);
        this.spikeyRight = spikeyImg.getSubimage( 144, 0, 48, 48);
        this.openCanFlo = canFlo.getSubimage( 64, 0, 64, 96);
        this.birdImg = birdImg.getSubimage( 0, 0, 96, 96);

        this.checkPoint = checkPoint.getSubimage( 0, 0, 48, 59);
        this.slimeBrick = slimeBrick.getSubimage( 0, 0, 48, 48);
        this.reversePipe = reversePipe.getSubimage( 0, 0, 96, 96);
        this.whitePipe = whitePipe.getSubimage( 0, 0, 96, 96);
        this.coinM = coinM.getSubimage( 0, 0, 32, 32);
        this.superMushroom = imageLoader.getSubImage(sprite, 2, 5, 48, 48);
        this.star= imageLoader.getSubImage(sprite, 5, 5, 48, 48);
        this.fireFlower= imageLoader.getSubImage(sprite, 4, 5, 48, 48);
        this.coin = imageLoader.getSubImage(sprite, 1, 5, 48, 48);
        this.ordinaryBrick = imageLoader.getSubImage(sprite, 1, 1, 48, 48);
        this.emptyBrick = imageLoader.getSubImage(sprite, 1, 2, 48, 48);
        this.surpriseBrick = imageLoader.getSubImage(sprite, 2, 1, 48, 48);
        this.groundBrick = imageLoader.getSubImage(sprite, 2, 2, 48, 48);
        this.pipe = imageLoader.getSubImage(sprite, 3, 1, 96, 96);
        this.goombaLeft = imageLoader.getSubImage(sprite, 2, 4, 48, 48);
        this.goombaRight = imageLoader.getSubImage(sprite, 5, 4, 48, 48);
        this.koopaLeft = imageLoader.getSubImage(sprite, 1, 3, 48, 64);
        this.koopaRight = imageLoader.getSubImage(sprite, 4, 3, 48, 64);
        this.endFlag = imageLoader.getSubImage(sprite, 5, 1, 48, 48);



    }


    Map createMap(String mapPath,double timeLimit, boolean isResumeGame,Gamer gamer) {
        BufferedImage mapImage = imageLoader.loadImage(mapPath);

        if (mapImage == null) {
            System.out.println("Given path is invalid...");
            return null;
        }

        Map createdMap = new Map(timeLimit, backgroundImage);
        String[] paths = mapPath.split("/");
        createdMap.setPath(paths[paths.length-1]);

        int pixelMultiplier = 48;

        int mario = new Color(160, 160, 160).getRGB();
        int ordinaryBrick = new Color(0, 0, 255).getRGB();
        int surpriseBrick = new Color(255, 255, 0).getRGB();
        int groundBrick = new Color(255, 0, 0).getRGB();
        int pipe = new Color(0, 255, 0).getRGB();
        int goomba = new Color(0, 255, 255).getRGB();
        int koopa = new Color(255, 0, 255).getRGB();
        int end = new Color(255, 255, 255).getRGB();
        int emptyBrick = new Color(163, 73, 164).getRGB();
        int spikey = new Color(185, 122, 87).getRGB();
        int cannibalFlower = new Color(34, 177, 76).getRGB();
        int black = new Color(112, 146, 190).getRGB();
        int bird = new Color(128, 128, 128).getRGB();
        int checkPoint = new Color(239, 228, 176).getRGB();
        int slimeBrick = new Color(128, 128, 64).getRGB();
        int reversePipe = new Color(0, 134, 33).getRGB();
        int whitePipe = new Color(106, 106, 106).getRGB();
        int coinM = new Color(230, 115, 0).getRGB();




        for (int x = 0; x < mapImage.getWidth(); x++) {
            for (int y = 0; y < mapImage.getHeight(); y++) {

                int currentPixel = mapImage.getRGB(x, y);
                int xLocation = x*pixelMultiplier;
                int yLocation = y*pixelMultiplier;

                if (currentPixel == ordinaryBrick) {
                    Brick brick = generateRandomOrdinary(xLocation, yLocation);
                    createdMap.addBrick(brick);
                }
                else if (currentPixel == surpriseBrick) {
                    Prize prize = generateRandomPrize(xLocation, yLocation);
                    Brick brick = new SurpriseBrick(xLocation, yLocation, this.surpriseBrick, prize);
                    createdMap.addBrick(brick);
                }
                else if (currentPixel == coinM) {
                    Prize prize = new Coin(xLocation, yLocation, this.coinM,0);
                    createdMap.addRevealedPrize(prize);
                }
                else if (currentPixel == emptyBrick) {
                    Brick brick = new emptyBrick(xLocation, yLocation, this.emptyBrick);
                    createdMap.addBrick(brick);
                }
                else if (currentPixel == cannibalFlower) {
                    Enemy enemy = new CannibalFlower(xLocation+18, yLocation+2 - 48, this.openCanFlo );
                    ((CannibalFlower) enemy).setUpImage(openCanFlo);
                    createdMap.addEnemy(enemy);
                }
                else if (currentPixel == pipe) {
                    Brick brick = new Pipe(xLocation, yLocation-1, this.pipe);
                    createdMap.addGroundBrick(brick);
                }
                else if (currentPixel == reversePipe) {
                    Brick brick = new ReversePipe(xLocation, yLocation-50, this.reversePipe);
                    createdMap.addGroundBrick(brick);
                }
                else if (currentPixel == whitePipe) {
                    Brick brick = new WhitePipe(xLocation, yLocation, this.whitePipe);
                    createdMap.addGroundBrick(brick);
                }
                else if (currentPixel == checkPoint) {
                    Brick brick = new CheckPoint(xLocation, yLocation-10, this.checkPoint);
                    createdMap.addGroundBrick(brick);
                }
                else if (currentPixel == groundBrick) {
                    Brick brick = new GroundBrick(xLocation, yLocation, this.groundBrick);
                    createdMap.addGroundBrick(brick);
                }
                else if (currentPixel == slimeBrick) {
                    Brick brick = new SlimeBrick(xLocation, yLocation, this.slimeBrick);
                    createdMap.addGroundBrick(brick);
                }
                else if (currentPixel == black) {
                    Brick brick = new Black(xLocation, yLocation-1, this.black) ;
                    createdMap.addGroundBrick(brick);
                }
                else if (currentPixel == goomba) {
                    Enemy enemy = new Goomba(xLocation, yLocation, this.goombaLeft);
                    ((Goomba)enemy).setRightImage(goombaRight);
                    createdMap.addEnemy(enemy);
                }
                else if (currentPixel == spikey) {
                    Enemy enemy = new Spikey(xLocation, yLocation, this.spikeyLeft);
                    ((Spikey)enemy).setRightImage(spikeyRight);
                    createdMap.addEnemy(enemy);
                }
                else if (currentPixel == koopa) {
                    Enemy enemy = new KoopaTroopa(xLocation, yLocation, this.koopaLeft);
                    ((KoopaTroopa)enemy).setRightImage(koopaRight);
                    createdMap.addEnemy(enemy);
                }
                else if (currentPixel == bird) {
                    Enemy enemy1 = new Bird(xLocation, yLocation-60, this.birdImg);
                    ((Bird)enemy1).setRightImage(birdImg);

                    createdMap.addEnemy(enemy1);



                }
                else if (currentPixel == mario) {
                    if (isResumeGame) {
                        Gamer updateUser = getGamerUpdateData(gamer);
                        GameState updataGameState = updateUser.getRunningGames().get(0);
                        Mario marioObject = new Mario(updataGameState.getMarioXLocation(), updataGameState.getMarioYLocation());
                        createdMap.setMario(marioObject);
//                        createdMap.setMario(updataGameState.getMario());
                        //TODO this is the section
                        createdMap.getMario().setScore(updataGameState.getScore());
                        createdMap.getMario().setCoins(updataGameState.getCoins());
                        createdMap.getMario().setRemainingLives(updataGameState.getRemainingLives());
                    }
                    else {
                        Mario marioObject = new Mario(xLocation, yLocation);
                        createdMap.addMario(marioObject);

                        //TODO FIX SITING
                       // if (marioObject.isSiting()){}
                       // else {}

                    }




                }
                else if(currentPixel == end){
                    EndFlag endPoint= new EndFlag(xLocation+24, yLocation, endFlag);
                    createdMap.setEndPoint(endPoint);
                }
            }
        }

        System.out.println("Map is created..");
        return createdMap;
    }

    private Prize generateRandomPrize(double x, double y){
        Prize generated;
        int random = (int)(Math.random() * 12);

        if(random == 0){ //super mushroom
            generated = new SuperMushroom(x, y, this.superMushroom);
        }
        else if(random == 1){ //fire flower
            generated = new FireFlower(x, y, this.fireFlower);
        }
        else if(random == 2){ //star
            generated = new Star(x, y, this.star);
        }
        else{ //coin
            generated = new Coin(x, y, this.coin, 50);
        }

        return generated;
    }

    private Gamer getGamerUpdateData(Gamer gamer) {
        Gson gson = new Gson();
        Gamer updeteGamer = new Gamer();
        try {
            updeteGamer = gson.fromJson(new FileReader(new File(gamer.getPathJsonFile())), Gamer.class);
            System.out.println(updeteGamer);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return updeteGamer;
    }


    Coin coinb;
    private Brick generateRandomOrdinary(double x, double y){
        OrdinaryBrick generatedBrick;


        Random rand = new Random();
        int random = rand.nextInt(30);

        if(random%3 == 0){ //Ordinary brick 1
            generatedBrick = new OrdinaryBrick1(x, y, this.ordinaryBrick,coinb);
        }
        else if(random%3 == 1){ //Ordinary brick 2
            generatedBrick = new OrdinaryBrick2(x, y, this.ordinaryBrick,coinb);
        }
        else { //Ordinary brick 3
            generatedBrick = new OrdinaryBrick3(x, y, this.ordinaryBrick,coinb);
        }


        return generatedBrick;
    }


}
