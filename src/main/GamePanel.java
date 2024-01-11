package main;

import debugSystem.DebugLayout;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    final byte org_TileSize = 16;//tile size(in pixels)
    final byte scale = 3;

    public final int tileSize = org_TileSize * scale;// 48*48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;//768
    public final int screenHeight = tileSize * maxScreenRow;//576
    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    // amount of frames in the game
    public int drawCount = 0;
    //amount of FPS wanted in the game

    int FPS = 60;
    // initialize classes
    //keys and thread
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    // Player
    public Player player = new Player(this,keyH);
    //tileManager
    TileManager tileM = new TileManager(this);
    // debugger


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run(){
        //game loop
        double drawInterval = 1_000_000_000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while(gameThread != null) {
            // FPS
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);

            lastTime = currentTime;
            if(delta >= 1)
            {

                // update information
                update();
                // draw updated info
                repaint();

                delta--;
                drawCount++;
            }
            if(timer >= 1_000_000_000){
                System.out.println("FPS: "+ drawCount);
                
                drawCount = 0;
                timer = 0;

            }
        }
    }
    public void update() {

        player.update();

    }
    public void paintComponent(Graphics g){
        // player
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }

}