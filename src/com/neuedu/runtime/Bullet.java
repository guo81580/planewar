package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.util.ImageMap;

import java.awt.*;

public class Bullet extends BaseSprite implements Drawable, Moveable {
     private  Image image;
     private int speed=FrameConstant.GAME_SPEED*5;

    public Bullet() {
        this(0,0, ImageMap.get("mb01"));

    }

    public Bullet(int x, int y, Image image) {
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
        setY(getY()- speed);

    }
}
