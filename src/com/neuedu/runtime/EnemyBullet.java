package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;

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
        move();
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);

 }

    @Override
    public void move() {
        setY(getY()+speed);
        boderTesting();
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
        EnemyPlane enemyPlane = new EnemyPlane();
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.enemyBulletList.remove(this);
            gameFrame.hp-=enemyPlane.getType()*3;
            if (gameFrame.hp<=0){
                    gameFrame.hp = 0;
                    gameFrame.gameOver = true;

            }

        }
        }

}
