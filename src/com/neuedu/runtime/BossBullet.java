package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;

public class BossBullet extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private int speed = FrameConstant.GAME_SPEED * 5;

    public BossBullet() {
        //this(0,0, ImageMap.get("bossb"));
    }

    public BossBullet(int x, int y, Image image ) {
        super(x, y);
        this.image = ImageMap.get("bossb");

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
       // BossBullet bossBullet= new BossBullet();
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.bossBulletList.remove(this);
            gameFrame.hp-=15;
            if (gameFrame.hp<=0){
                gameFrame.hp=0;
                gameFrame.gameOver = true;

            }

        }
    }

}
