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
import java.util.Random;

public class Boss extends BaseSprite implements Moveable, Drawable {

   private Image image;
    private int speed = FrameConstant.GAME_SPEED;
    private Random random = new Random();


    public Boss( ) {
        this.image = ImageMap.get("boss");
    }



    public Boss(int x, int y, Image iamge) {
        super(x, y);
        this.image = iamge;

    }

    @Override
    public void draw(Graphics g) {

        GameFrame gameFrame = DataStore.get("gameFrame");
            //if (gameFrame.score >=20) {
             move();
            g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
            fire();
        //}

    }
    public void fire(){
        GameFrame gameFrame =DataStore.get("gameFrame");
        if (getY()>0){
          if (random.nextInt(1000)>990){
                gameFrame.bossBulletList.add(new BossBullet(
                        getX()+ (image.getWidth(null)/2) - ImageMap.get("mb01").getWidth(null)/2,
                        getY()+image.getHeight(null)/2 - ImageMap.get("mb01").getHeight(null)/2
                        ,ImageMap.get("mb01")));
            }

        }
    }
    private boolean right=true;
    @Override
    public void move() {

        if (right){
            setX(getX()+speed);

        }else{
            setX(getX()-speed);

        }borderTesting();

    }

    public void borderTesting(){
        if (getX() + image.getWidth(null)>= FrameConstant.FRAME_WIDTH){
            right = false;
        }else if (getX()<0){
            right=true;
    }
 }
    public Rectangle getRectangle(){
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }



    public void collisionTesting(Plane plane){
     GameFrame gameFrame = DataStore.get("gameFrame");
     //Boss boss = new Boss();
     if (plane.getRectangle().intersects(this.getRectangle())) {
       //  gameFrame.bossList.remove(this);
         gameFrame.hp-=20;
         if (gameFrame.hp<=0){
             gameFrame.hp = 0;
             gameFrame.gameOver = true;
         }
     }
 }
    public void collisionTesting1(List<Bullet> bulletList) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (Bullet bullet: bulletList) {
            if (bullet.getRectangle().intersects(this.getRectangle())) {
                if (gameFrame.score < 20) {
                     gameFrame.bosshp  -= 25;
                } else if (gameFrame.score >= 20 && gameFrame.score < 50) {
                    gameFrame.bosshp  -= 50;
                } else if (gameFrame.score >= 50) {
                    gameFrame.bosshp  -= 100;
                }
                gameFrame.bulletList.remove(bullet);
                if ( gameFrame.bosshp  <= 0) {
                    gameFrame.bosshp = 0;
                    gameFrame.bossList.remove(this);
                    gameFrame.score += 20;
                }
            }
        }
    }


}
