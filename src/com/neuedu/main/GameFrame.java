package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.*;
import com.neuedu.util.ImageMap;
import com.sun.scenario.effect.impl.prism.PrReflectionPeer;

import java.awt.*;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.CopyOnWriteArrayList;


public class GameFrame extends Frame {


    //创建背景对象
    private Background background = new Background();

    //创建飞机对象
    private Plane plane = new Plane( );

    //创建道具对象
    private Prop prop = new Prop();

    //创建子弹集合
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();

    //创建敌方飞机子弹集合
    public final List<EnemyBullet> enemyBulletList = new CopyOnWriteArrayList<>();

    //创建敌方飞机
    public  final List<EnemyPlane> enemyPlaneList = new CopyOnWriteArrayList<>();

    //创建道具
    public final List<Prop> propList = new CopyOnWriteArrayList<>();


    public boolean gameOver = false;
    public int score=0;
    public int hp=100;



    @Override
    public void paint(Graphics g) {
        if (!gameOver){
            background.draw(g);
            plane.draw(g);
            prop.draw(g);

            for (Bullet bullet : bulletList) {
                bullet.draw(g);
            }

            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.draw(g);
            }

//

            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.draw(g);
            }
            for (Prop prop : propList) {
                prop.draw(g);
            }



            for (Bullet bullet : bulletList) {
                bullet.collisionTesting(enemyPlaneList);
            }
            for (EnemyBullet enemyBullet : enemyBulletList) {
           enemyBullet.collisionTesting(plane);
            }
            for (EnemyPlane enemyPlane : enemyPlaneList) {
               enemyPlane.collisionTesting(plane);
            }
            for (Prop prop : propList) {
                prop.collisionTesting(plane);
            }


            g.setFont(new Font("楷体",Font.BOLD,25));

            g.setColor(new Color(60, 255, 164));

            g.drawString("得分："+score,20,60);

            g.drawString("生命值："+hp,520,60);

        }

    }
    public void init(){
        setSize(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);


        //设置居中
        setLocationRelativeTo(null);
        enableInputMethods(false);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //添加键盘监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                plane.keyPressed(e);

            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyReleased(e);

            }
        });

        new Thread(){
            public void run(){
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }.start();


        //游戏初始时添加一些敌方飞机
        enemyPlaneList.add(new EnemyPlane(100,100,  1));
        enemyPlaneList.add(new EnemyPlane(360,-100,  1));
        enemyPlaneList.add(new EnemyPlane(360,-200,  1));
        enemyPlaneList.add(new EnemyPlane(360,-300,  1));
        enemyPlaneList.add(new EnemyPlane(360,-400,  1));
        enemyPlaneList.add(new EnemyPlane(360,-500,  1));
        enemyPlaneList.add(new EnemyPlane(360,-600,  1));
        enemyPlaneList.add(new EnemyPlane(360,-1600,  1));
        enemyPlaneList.add(new EnemyPlane(360,-1200,  1));
        enemyPlaneList.add(new EnemyPlane(360,-1300,  1));
        enemyPlaneList.add(new EnemyPlane(360,-1500,  1));
        enemyPlaneList.add(new EnemyPlane(360,-3500,  1));
        enemyPlaneList.add(new EnemyPlane(360,-6500,  1));
        enemyPlaneList.add(new EnemyPlane(550,-3600,  1));
        enemyPlaneList.add(new EnemyPlane( 0,30,  2));
        enemyPlaneList.add(new EnemyPlane( 30,30,  2));
        enemyPlaneList.add(new EnemyPlane( 50,30,  2));


        propList.add(new Prop(400,-500, ImageMap.get("blood")));
        propList.add(new Prop(200,-1000, ImageMap.get("blood")));
        propList.add(new Prop(400,-500, ImageMap.get("blood")));
        propList.add(new Prop(400,-600, ImageMap.get("blood")));
        setVisible(true);

    }
    private Image offScreenImage = null;
    @Override
    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);

        }
        Graphics goff = offScreenImage.getGraphics();
        paint(goff);
        g.drawImage(offScreenImage,0,0,null);
    }

}
