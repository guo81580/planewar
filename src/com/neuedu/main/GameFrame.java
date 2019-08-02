package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.*;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;
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

    //创建敌方飞机集合
    public  final List<EnemyPlane> enemyPlaneList = new CopyOnWriteArrayList<>();

    //创建道具
    public final List<Prop> propList = new CopyOnWriteArrayList<>();

    public final List<Boss> bossList = new CopyOnWriteArrayList<>();

    public final List<BossBullet> bossBulletList = new CopyOnWriteArrayList<>();


    public boolean gameOver = false;
    public int score=0;
    public int hp=100;
    public int bosshp=1000;
    public int count=0;

    Random random = new Random();





    @Override
    public void paint(Graphics g) {
        if (!gameOver){
            background.draw(g);
            plane.draw(g);
            prop.draw(g);



            if(bosshp <= 0){

                if (random.nextInt(1000)>950){
                    enemyPlaneList.add(new EnemyPlane(random.nextInt(700),0,1 ));
                }
            }else if (bosshp > 0 ){
            if (random.nextInt(1000)>993){
                enemyPlaneList.add(new EnemyPlane(random.nextInt(700),0,1 ));
            }}

            if(bosshp <= 0){
                if (random.nextInt(1000)>950){
                    enemyPlaneList.add(new EnemyPlane(random.nextInt(700),0,2 ));
                }

            }else if (bosshp > 0 ){

                if (random.nextInt(1000)>995){
                    enemyPlaneList.add(new EnemyPlane(random.nextInt(700),0,2 ));
                }
            }

            if(bosshp <= 0){
                if (random.nextInt(1000)>950){
                    enemyPlaneList.add(new EnemyPlane(random.nextInt(700),250,5 ));
                }

            }else if (bosshp > 0 ){

                if (random.nextInt(1000)>998){
                    enemyPlaneList.add(new EnemyPlane(random.nextInt(700),250,5 ));
                }
            }
            if (random.nextInt(1000)>995){
                propList.add(new Prop(random.nextInt(800),0, ImageMap.get("blood")));
            }

            for (Bullet bullet : bulletList) {
                bullet.draw(g);
            }

            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.draw(g);
            }

            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.draw(g);
            }
            for (Prop prop : propList) {
                prop.draw(g);
            }
            for (Boss boss : bossList) {
                boss.draw(g);
            }
            for (BossBullet bossBullet : bossBulletList) {
                bossBullet.draw(g);
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
            for (Boss boss : bossList) {
                boss.collisionTesting(plane);
                boss.collisionTesting1(bulletList);
            }
            for (BossBullet bossBullet : bossBulletList) {
                bossBullet.collisionTesting(plane);
            }


            g.setFont(new Font("隶书",Font.BOLD,25));

            g.setColor(new Color(255, 255, 255));

            g.drawString("得分："+score,20,100);

            g.drawString("击落敌机"+count,20,150);

            g.setColor(Color.green);
            g.drawRect(550, 125, 100 , 15);
            g.fillRect(550, 125, hp , 15);
            g.drawString("我的生命值:" + hp, 550, 100);

            g.setColor(Color.red);
            g.drawRect(550, 225, 100 , 15);
            g.fillRect(550, 225, bosshp /10 , 15);
            g.drawString("boss生命值:" + bosshp, 550, 200);

        }if (gameOver){
            g.setFont(new Font("隶书",Font.BOLD,67));
            g.setColor(new Color(255, 36, 21));
            g.drawString("GAMEOVER!",225,450);
        }
        if (bosshp <= 0 ){
            g.setFont(new Font("隶书",Font.BOLD,67));
            g.setColor(new Color(255, 36, 21));
            g.drawString("WIN!",300,350);
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

        bossList.add(new Boss(30,10,ImageMap.get("boss")));

        setVisible(true);

    }

    //缓冲
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
