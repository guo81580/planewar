package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.Random;

public class EnemyPlane extends BaseSprite implements Drawable, Moveable {

    private Image image;
    private Image image2;
    private Image image3;
    private int speed = FrameConstant.GAME_SPEED * 3;
    private Random random = new Random();
    private int type;
    public int ephp;
    public int bosshp;

    public EnemyPlane( ) {
        this(0,0,  1);

    }

    public int getType() {
        return type;
    }



    public EnemyPlane(int x, int y , int type) {
        super(x, y);

        this.type = type;
        this.image = ImageMap.get("ep01");
        this.image2 = ImageMap.get("ep02");
        this.image3 = ImageMap.get("ep03");
        if (type ==1){
            ephp = 50;
        }else if (type == 2){
            ephp = 100;
        }else if (type == 5){
            ephp = 1000;
        }
    }

    @Override
    public void draw(Graphics g) {

        move();
        if (type == 1){
            g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);

        }else if (type ==2){
            g.drawImage(image2,getX(),getY(),image.getWidth(null),image.getHeight(null),null);

        }else if (type==5 ){
            g.drawImage(image3,getX(),getY(),image.getWidth(null),image.getHeight(null),null);

        }


        fire();
    }

    public void fire(){
        GameFrame gameFrame =    DataStore.get("gameFrame");
        if (getY()>0){
            if (random.nextInt(1000)>999){
                gameFrame.enemyBulletList.add(new EnemyBullet(
                        getX()+ (image.getWidth(null)/2) - ImageMap.get("epb01").getWidth(null)/2,
                        getY()+image.getHeight(null)/2 - ImageMap.get("epb01").getHeight(null)/2
                        ,ImageMap.get("epb01")));
            }

        }
  }

    private boolean right=true;
    @Override
    public void move() {
        if (type == 1){
            setY(getY()+ speed);
        }else  if (type==2  ){
            setY(getY()+ speed);

        }else  if ( type == 5){
            if (right){
                setX(getX()+speed);

            }else{
                setX(getX()-speed);

            }

        }

        borderTesting();

    }

    public void borderTesting(){
        if (type == 1){
            if (getY()>FrameConstant.FRAME_HEIGHT){
                GameFrame gameFrame = DataStore.get("gameFrame");
                gameFrame.enemyPlaneList.remove(this);
            }
        }else  if (type==2){
            if (getY()>FrameConstant.FRAME_HEIGHT){
                GameFrame gameFrame = DataStore.get("gameFrame");
                gameFrame.enemyPlaneList.remove(this);
            }

//            if (getX() + image2.getWidth(null)>= FrameConstant.FRAME_WIDTH){
//                right = false;
//            }else if (getX()<0){
//                right=true;
//
//            }
        }else  if (type==5){
            if (getX() + image3.getWidth(null)>= FrameConstant.FRAME_WIDTH){
                right = false;
            }else if (getX()<0){
                right=true;

            }
        }


    }
    public Rectangle getRectangle(){
         return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }


    public void collisionTesting(Plane plane){
        GameFrame gameFrame = DataStore.get("gameFrame");
        EnemyPlane enemyPlane = new EnemyPlane();
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.enemyPlaneList.remove(this);
            gameFrame.hp-=enemyPlane.getType()*5;
            if (gameFrame.hp<=0){
                gameFrame.hp = 0;
                gameFrame.gameOver = true;
            }
        }
    }


}
