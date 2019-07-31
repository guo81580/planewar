package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.List;

public class EnemyBullet extends BaseSprite implements Drawable, Moveable {
    private Image image;
    private int speed = FrameConstant.GAME_SPEED*5;

    public EnemyBullet( ) {
        this(0,0, ImageMap.get("epb01"));


    }

    public EnemyBullet(int x, int y, Image image) {
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
        setY(getY()+speed);
    }

    public void boderTesting(){
        if (getY()>FrameConstant.FRAME_HEIGHT){
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.enemyBulletList.remove(this);
        }
    }

    public Rectangle getRectangle(){
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }

    public void collisionTesting(Plane plane){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.enemyBulletList.remove(this);
            gameFrame.hp-=10;
            if (gameFrame.hp<=0){
                gameFrame.gameOver = true;
            }

           //
        }
        }

}
