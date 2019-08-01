package com.neuedu.runtime;

import com.neuedu.GameStart;
import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyEvent;


public class Plane extends BaseSprite implements Drawable, Moveable {
    private  boolean up,right,down,left;
    private Image image;

    private int speed=FrameConstant.GAME_SPEED*5;
    private boolean fire;



    private int index = 0;






    public Plane() {
        this((FrameConstant.FRAME_WIDTH - ImageMap.get("my01").getWidth(null))/2,
                FrameConstant.FRAME_HEIGHT - ImageMap.get("my01").getHeight(null),
                ImageMap.get("my01"));
    }

    public Plane(int x, int y,Image image) {
        super(x, y);
        this.image = image;

    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);


        fire();


       index++;
       if (fire){
          if (index>=10){
              index=0;
       }
       }



    }
    EnemyPlane enemyPlane = new EnemyPlane();
    public void fire() {

        GameFrame gameFrame = DataStore.get("gameFrame");

        if (fire && index == 0 && gameFrame.score >=0 && gameFrame.score<20) {
            gameFrame.bulletList.add(new Bullet(
                    getX() + (image.getWidth(null) / 2) - ImageMap.get("mb01").getWidth(null) / 2,
                    getY() - ImageMap.get("mb01").getHeight(null),
                    ImageMap.get("mb01")
            ));

        }else if (fire && index == 0 && gameFrame.score >=20 && gameFrame.score < 50){


                gameFrame.bulletList.add(new Bullet(
                        getX() + (image.getWidth(null) / 2) - ImageMap.get("mb02").getWidth(null) / 2,
                        getY() - ImageMap.get("mb02").getHeight(null),
                        ImageMap.get("mb02")));



        } else if (fire && index == 0 && gameFrame.score>=50){


                gameFrame.bulletList.add(new Bullet(
                        getX() + (image.getWidth(null) / 2) - ImageMap.get("mb03").getWidth(null) / 2,
                        getY() - ImageMap.get("mb03").getHeight(null),
                        ImageMap.get("mb03")));



        }
    }




    @Override
    public void move() {

        if(up){
            setY(getY() - speed);
        }
        if(right){
            setX(getX() +speed);
        }
        if(down){
            setY(getY() + speed);
        }
        if(left){
            setX(getX() - speed);
        }
        borderTesting();
    }

    public void borderTesting(){

        if(getX() <0){
            setX(0);
        }
        if (getX()>FrameConstant.FRAME_WIDTH - image.getWidth(null)){
            setX(FrameConstant.FRAME_WIDTH - image.getWidth(null));
        }
        if (getY()<30){
            setY(30);
        }
        if (getY()>FrameConstant.FRAME_HEIGHT - image.getHeight(null)){
            setY(FrameConstant.FRAME_HEIGHT - image.getHeight(null));
        }

    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            up = true;

        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            right = true;

        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            down = true;

        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            left = true;

        }
        if(e.getKeyCode() == KeyEvent.VK_J){
              fire = true;
        }
}





    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            up = false;

        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            right = false;

        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            down = false;

        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            left = false;

        }

        if(e.getKeyCode() == KeyEvent.VK_J){

            fire = false;

        }





    }
    public Rectangle getRectangle(){
        return new Rectangle(getX(),getY(),image.getWidth(null),
                image.getHeight(null));
    }

}
