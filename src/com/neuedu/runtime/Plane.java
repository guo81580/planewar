package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyEvent;


public class Plane extends BaseSprite implements Drawable, Moveable {
    private  boolean up,right,down,left;
    private Image image;
    private int speed=FrameConstant.GAME_SPEED*2;

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

        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();



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

    }
}
