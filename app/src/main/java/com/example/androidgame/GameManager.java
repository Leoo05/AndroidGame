package com.example.androidgame;
public class GameManager {

    private int score;
    private Kid kido;
    private Daniel tigre;
    private IceCreamCar danielVan;
    private final float X = 1000;
    private final float Y = 50;
    private final String text = "SCORE: ";



    public GameManager(Kid kido,Daniel tigre,IceCreamCar danielVan){
        this.kido=kido;
        this.tigre=tigre;
        this.score=0;
        this.danielVan = danielVan;
    }

    public void checkKidColision(){
        if((kido.getPositionY() >= danielVan.getPositionY() && kido.getPositionY() <= danielVan.getPositionY() + danielVan.getSpriteIcecreamCar().getHeight()) ||
                (kido.getPositionY() + kido.getSpriteKid().getHeight() >= danielVan.getPositionY() && kido.getPositionY() + kido.getSpriteKid().getHeight() <= danielVan.getPositionY() + danielVan.getSpriteIcecreamCar().getHeight())){
            if(kido.getPositionX() >= danielVan.getPositionX() && danielVan.getPositionX() + kido.getPositionX() <= danielVan.getSpriteIcecreamCar().getWidth()){
                score = score+1;
                kido.setPositionX(-1);
            }
        }
    }
    public boolean checkDanielColision(){
        if((tigre.getPositionY() >= danielVan.getPositionY() && tigre.getPositionY() <= danielVan.getPositionY() + danielVan.getSpriteIcecreamCar().getHeight()) ||
                (tigre.getPositionY() + tigre.getSpriteKid().getHeight() >= danielVan.getPositionY() && tigre.getPositionY() + tigre.getSpriteKid().getHeight() <= danielVan.getPositionY() + danielVan.getSpriteIcecreamCar().getHeight())){
            if(tigre.getPositionX() >= danielVan.getPositionX() && danielVan.getPositionX() + tigre.getPositionX() <= danielVan.getSpriteIcecreamCar().getWidth()){
                //return true;
            }
        }
        return false;
    }

    public String getScore(){
        return text + score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public float getX() {
        return X;
    }

    public float getY() {
        return Y;
    }
}
