package com.example.androidgame;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;

public class GameSurfaceView extends SurfaceView implements Runnable {

    private boolean isPlaying;
    private IceCreamCar icecreamCar;
    private Kid kid;
    private Daniel daniel;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder holder;
    private Thread gameplayThread = null;
    private GameManager gameManager;

    /**
     * Contructor
     * @param context
     */
    public GameSurfaceView(Context context, float screenWith, float screenHeight) {
        super(context);
        icecreamCar = new IceCreamCar(context, screenWith, screenHeight);
        kid = new Kid(context, screenWith, screenHeight);
        daniel = new Daniel(context, screenWith, screenHeight);
        paint = new Paint();
        holder = getHolder();
        isPlaying = true;
        gameManager = new GameManager(kid,daniel,icecreamCar);
        gameManager.setScore(0);
    }

    /**
     * Method implemented from runnable interface
     */
    @Override
    public void run() {
        while (isPlaying) {
            updateInfo();
            paintFrame();
        }
    }

    private void updateInfo() {
        icecreamCar.updateInfo ();
        kid.updateInfo();
        daniel.updateInfo();
        gameManager.checkKidColision();
        if(gameManager.checkDanielColision()){
            isPlaying = false;
        }

    }

    private void paintFrame() {
        if (holder.getSurface().isValid()){
            canvas = holder.lockCanvas();
            canvas.drawColor(Color.CYAN);
            canvas.drawText(gameManager.getScore(), gameManager.getX(), gameManager.getY(), paint);
            canvas.drawBitmap(icecreamCar.getSpriteIcecreamCar(),icecreamCar.getPositionX(),icecreamCar.getPositionY(),paint);
            canvas.drawBitmap(kid.getSpriteKid(),kid.getPositionX(),kid.getPositionY(),paint);
            canvas.drawBitmap(daniel.getSpriteKid(),daniel.getPositionX(),daniel.getPositionY(),paint);
            holder.unlockCanvasAndPost(canvas);
        }

    }


    public void pause() {
        isPlaying = false;
        try {
            gameplayThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public void resume() {
        isPlaying = true;
        gameplayThread = new Thread(this);
        gameplayThread.start();
    }

    /**
     * Detect the action of the touch event
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                System.out.println("TOUCH UP - STOP JUMPING");
                icecreamCar.setJumping(false);
                break;
            case MotionEvent.ACTION_DOWN:
                System.out.println("TOUCH DOWN - JUMP");
                icecreamCar.setJumping(true);
                break;
        }
        return true;
    }

}
