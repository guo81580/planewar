package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;

public class Prop extends BaseSprite implements Drawable, Moveable {
    private Image image;
    private int speed = FrameConstant.GAME_SPEED*5;


    public Prop() {
        this(0,0, ImageMap.get("blood"));
    }

    public Prop(int x, int y,Image image) {
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
            gameFrame.propList.remove(this);
        }
    }


    public Rectangle getRectangle(){
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }


    public void collisionTesting(Plane plane){
        GameFrame gameFrame = DataStore.get("gameFrame");

        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.propList.remove(this);
            if (gameFrame.hp<100){
                gameFrame.hp+= 10;
                if (gameFrame.hp>100){
                    gameFrame.hp=100;
                }
            }

        }
    }


}
