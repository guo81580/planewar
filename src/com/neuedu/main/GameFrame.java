package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.*;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

    //创建敌机对象
   // private EnemyPlane enemyPlane = new EnemyPlane();

    //穿件boss对象
   // private Boss boss = new Boss();

    //创建子弹对象
   // private Bullet bullet = new Bullet();

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
    public int bosshp=5000;
    public int count=0;

    Random random = new Random();
    //private Image image;
    //public int ephp=100;




    @Override
    public void paint(Graphics g) {
        if (!gameOver){
            background.draw(g);
            plane.draw(g);
            prop.draw(g);



            if (random.nextInt(1000)>995){
                enemyPlaneList.add(new EnemyPlane(random.nextInt(700),0,1 ));
            }
            if (random.nextInt(1000)>995){
                enemyPlaneList.add(new EnemyPlane(random.nextInt(700),0,2 ));
            }
            if (random.nextInt(1000)>998){
                enemyPlaneList.add(new EnemyPlane(random.nextInt(700),250,5 ));
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


            g.setFont(new Font("隶书",Font.BOLD,30));

            g.setColor(new Color(60, 255, 164));

            g.drawString("得分："+score,20,60);

            g.drawString("生命值："+hp,520,60);
            g.drawString("boss生命值"+bosshp,20,100);
            g.drawString("击落敌机"+count,20,150);



        }if (gameOver){
            g.setFont(new Font("隶书",Font.BOLD,67));

            g.setColor(new Color(60, 255, 164));
            g.drawString("GAMEOVER!",225,350);
        }
        if (bosshp <= 0 ){

            g.setFont(new Font("隶书",Font.BOLD,67));

            g.setColor(new Color(60, 255, 164));
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


        //游戏初始时添加一些敌方飞机

//        enemyPlaneList.add(new EnemyPlane(100,100,  1));
//        enemyPlaneList.add(new EnemyPlane(200,30,  1));
//        enemyPlaneList.add(new EnemyPlane(360,-200,  1));
//        enemyPlaneList.add(new EnemyPlane(420,-600,  1));
//        enemyPlaneList.add(new EnemyPlane( 550,-400,  1));
//        enemyPlaneList.add(new EnemyPlane(670,-900,  1));
//        enemyPlaneList.add(new EnemyPlane(150,-1600,  1));
//        enemyPlaneList.add(new EnemyPlane(220,-1900,  1));
//        enemyPlaneList.add(new EnemyPlane(480,-1200,  1));
//        enemyPlaneList.add(new EnemyPlane(350,-2300,  1));
//        enemyPlaneList.add(new EnemyPlane(760,-3300,  1));
//        enemyPlaneList.add(new EnemyPlane(120,-6500,  1));
//        enemyPlaneList.add(new EnemyPlane(650,-5000,  1));
//        enemyPlaneList.add(new EnemyPlane(340,-3600,  1));
//        enemyPlaneList.add(new EnemyPlane( 80,-9800,  2));
//        enemyPlaneList.add(new EnemyPlane( 700,-900,  2));
//        enemyPlaneList.add(new EnemyPlane( 700,-1900,  2));
//        enemyPlaneList.add(new EnemyPlane( 700,-6900,  2));
//        enemyPlaneList.add(new EnemyPlane( 700,0,  2));
//        enemyPlaneList.add(new EnemyPlane( 700,-6900,  2));
//        enemyPlaneList.add(new EnemyPlane( 500,-4400,  2));
//        enemyPlaneList.add(new EnemyPlane(10, 170, 5));
//        enemyPlaneList.add(new EnemyPlane(200, 220, 5));
//        enemyPlaneList.add(new EnemyPlane(400, 270, 5));
//
             bossList.add(new Boss(30,10,ImageMap.get("boss")));








//        propList.add(new Prop(400,-500, ImageMap.get("blood")));
//        propList.add(new Prop(200,-1000, ImageMap.get("blood")));
//        propList.add(new Prop(400,-500, ImageMap.get("blood")));
//        propList.add(new Prop(400,-1600, ImageMap.get("blood")));
//        propList.add(new Prop(340,-2600, ImageMap.get("blood")));
//        propList.add(new Prop(450,-5600, ImageMap.get("blood")));
//        propList.add(new Prop(270,-6600, ImageMap.get("blood")));
//        propList.add(new Prop(430,-9600, ImageMap.get("blood")));


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
