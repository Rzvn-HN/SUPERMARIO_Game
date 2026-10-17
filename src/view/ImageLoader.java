package view;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    private BufferedImage marioForms , marioFormsMega , marioFormsFire,marioFormsMini ;
    private BufferedImage brickAnimation;

    public ImageLoader(){
        marioForms = loadImage("/mario-forms.png");
        brickAnimation = loadImage("/brick-animation.png");

        marioFormsMini= loadImage("/img_5.png");
       marioFormsMega = loadImage("/img_1.png");
        marioFormsFire = loadImage("/BigRedMario.png");
    }

    public BufferedImage loadImage(String path){
        BufferedImage imageToReturn = null;

        try {
            imageToReturn = ImageIO.read(getClass().getResource("/media" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageToReturn;
    }

    public BufferedImage loadImage(File file){
        BufferedImage imageToReturn = null;

        try {
            imageToReturn = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageToReturn;
    }

    public BufferedImage getSubImage(BufferedImage image, int col, int row, int w, int h){
        if((col == 1 || col == 4) && row == 3){ //koopa
            return image.getSubimage((col-1)*48, 128, w, h);
        }
        return image.getSubimage((col-1)*48, (row-1)*48, w, h);
    }



    public BufferedImage[] getLeftFrames(int marioForm){
        BufferedImage[] leftFrames = new BufferedImage[6];
        int width = 48, height = 48;

        if(marioForm == 1) { //super mario
            width = 48;
            height = 96;
        }
        else if(marioForm == 2){ //fire mario
            width = 48;
            height = 96;
        }

        for(int i = 0; i < 6; i++){


            if (marioForm == 1) {
                leftFrames[0] = marioFormsMega.getSubimage(144, 0, width,height );
                leftFrames[1] =  marioFormsMega.getSubimage(48, 0, width,height );
                leftFrames[2] = marioFormsMega.getSubimage(0, 192, width,height );
                leftFrames[3] = marioFormsMega.getSubimage(48, 192, width,height );
                leftFrames[4] = marioFormsMega.getSubimage(96, 192, width,height );
                leftFrames[5] = marioFormsMega.getSubimage(48, 624, 48,48 );




            }
            else if (marioForm == 2){
                leftFrames[0] = marioFormsFire.getSubimage(144, 0, width,height );
                leftFrames[1] = marioFormsFire.getSubimage(48, 0, width,height );
                leftFrames[2] = marioFormsFire.getSubimage(0, 192, width,height );
                leftFrames[3] = marioFormsFire.getSubimage(48, 192, width,height );
                leftFrames[4] = marioFormsFire.getSubimage(96, 192, width,height );
                leftFrames[5] = marioFormsFire.getSubimage(48, 624, 48,48 );
            }
            else {

                leftFrames[0] = marioFormsMini.getSubimage(144, 0, width,height );
                leftFrames[1] =  marioFormsMini.getSubimage(48, 0, width,height );
                leftFrames[2] = marioFormsMini.getSubimage(96, 96, width,height );
                leftFrames[3] = marioFormsMini.getSubimage(48, 96, width,height );
                leftFrames[4] = marioFormsMini.getSubimage(0, 96, width,height );
                leftFrames[5] = marioFormsMini.getSubimage(0, 288, width,height );
            }


        }
        return leftFrames;
    }

    public BufferedImage[] getRightFrames(int marioForm){
        BufferedImage[] rightFrames = new BufferedImage[6];
        int col = 2;
        int width = 48, height = 48;

        if(marioForm == 1) { //super mario
            col = 5;
            width = 48;
            height = 96;
        }
        else if(marioForm == 2){ //fire mario
            col = 8;
            width = 48;
            height = 96;
        }

        for(int i = 0; i < 6; i++){

            if (marioForm == 1) {
                rightFrames[0] = marioFormsMega.getSubimage(96, 0, width, height);
                rightFrames[1] = marioFormsMega.getSubimage(0, 0, width,height );
                rightFrames[2] = marioFormsMega.getSubimage(0, 96, width, height);
                rightFrames[3] = marioFormsMega.getSubimage(48, 96, width, height);
                rightFrames[4] = marioFormsMega.getSubimage(96, 96, width, height);
                rightFrames[5] = marioFormsMega.getSubimage(0, 624, 48, 48);




            }
            else if (marioForm == 2){
                rightFrames[0] = marioFormsFire.getSubimage(96, 0, width, height);
                rightFrames[1] = marioFormsFire.getSubimage(0, 0, width,height );
                rightFrames[2] = marioFormsFire.getSubimage(0, 96, width, height);
                rightFrames[3] = marioFormsFire.getSubimage(48, 96, width, height);
                rightFrames[4] = marioFormsFire.getSubimage(96, 96, width, height);
                rightFrames[5] = marioFormsFire.getSubimage(0, 624, 48, 48);
            }
            else {

                rightFrames[0] = marioFormsMini.getSubimage(96, 0, width, height);
                rightFrames[1] = marioFormsMini.getSubimage(0, 0, width,height );
                rightFrames[2] = marioFormsMini.getSubimage(0, 48, width, height);
                rightFrames[3] = marioFormsMini.getSubimage(48, 48, width, height);
                rightFrames[4] = marioFormsMini.getSubimage(96, 48, width, height);
                rightFrames[5] = marioFormsMini.getSubimage(0, 288, 48, 48);
            }
        }
        return rightFrames;
    }

    public BufferedImage[] getBrickFrames() {
        BufferedImage[] frames = new BufferedImage[4];
        for(int i = 0; i < 4; i++){
            frames[i] = brickAnimation.getSubimage(i*105, 0, 105, 105);
        }
        return frames;
    }
}

