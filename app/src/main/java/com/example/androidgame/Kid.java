package com.example.androidgame;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Kid {

    public static final float INIT_X =100;
    public static final int SPRITE_SIZE_WIDTH =100;
    public static final int SPRITE_SIZE_HEIGTH=100;
    public static final float GRAVITY_FORCE=10;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    private float maxY;
    private float maxX;

    private float speed = 0;
    private float positionX;
    private float positionY;
    private Bitmap spriteKid;
    private boolean isJumping;


    public Kid(Context context, float screenWidth, float screenHeigth){

        speed = 20F;
        isJumping = false;
        //Getting bitmap from resource
        Bitmap originalBitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.kid);
        spriteKid = Bitmap.createScaledBitmap(originalBitmap, SPRITE_SIZE_WIDTH, SPRITE_SIZE_HEIGTH, false);

        this.maxX = screenWidth;
        this.maxY = screenHeigth - spriteKid.getHeight();
        positionX = this.maxX +spriteKid.getWidth();
        Random rnd = new Random();
        positionY =(float) rnd.nextInt((int) maxY);

    }

    public Kid (Context context, float initialX, float initialY, float screenWidth, float screenHeigth){

        speed = 0.09F;
        positionX = this.maxX + spriteKid.getWidth();
            Random rnd = new Random();
        positionY =(float) rnd.nextInt((int) maxY);

        Bitmap originalBitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.kid);
        spriteKid = Bitmap.createScaledBitmap(originalBitmap, SPRITE_SIZE_WIDTH, SPRITE_SIZE_HEIGTH, false);

        this.maxX = screenWidth;
        this.maxY = screenHeigth - spriteKid.getHeight();

    }

    public static float getInitX() {
        return INIT_X;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public Bitmap getSpriteKid() {
        return spriteKid;
    }

    public void setSpriteKid(Bitmap spriteKid) {
        this.spriteKid = spriteKid;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    /**
     * Control the position and behaviour of the icecream car
     */
    public void updateInfo () {
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }
        this.positionX -= speed;
        if (positionX < 0) {
            positionX = maxX;
            Random rnd = new Random();
            positionY =(float) rnd.nextInt((int) maxY);
        }
        if (positionY < 0) {
            positionY = 0;
        }
        if (positionY > maxY) {
            positionY = maxY;
        }
    }
}
