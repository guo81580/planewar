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
        move();
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);

        borderTesting();

    }

    @Override
    public void move() {
        setY(getY()- speed);

    }
    public void borderTesting(){
        if (getY()<30-image.getHeight(null)){
           GameFrame gameFrame = DataStore.get("gameFrame");
           gameFrame.bulletList.remove(this);

        }
    }
    public Rectangle getRectangle(){
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));

    }

    public void collisionTesting(List<EnemyPlane> enemyPlaneList) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (EnemyPlane enemyPlane : enemyPlaneList) {
            if (enemyPlane.getRectangle().intersects(this.getRectangle())) {
                if (gameFrame.score <20) {
                    enemyPlane.ephp -= 25;
                }else  if (gameFrame.score >=20 && gameFrame.score<50){
                    enemyPlane.ephp -= 50;
                }else if (gameFrame.score >= 50){
                    enemyPlane.ephp -= 100;
                }
                gameFrame.bulletList.remove(this);
                if (enemyPlane.ephp<= 0){
                    gameFrame.count++;
                    enemyPlaneList.remove(enemyPlane);

                    gameFrame.score += enemyPlane.getType() * 4;
                }
          }
        }
    }
}
