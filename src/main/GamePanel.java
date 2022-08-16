package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;

public class GamePanel  extends JPanel implements Runnable {
    // SCREEN SETTINGS
    public final int originalTileSize = 16; //16x16 tile
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48

    public int getTileSize() {
        return tileSize;
    }

    public final int maxSCreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxSCreenCol;
    public final int screenHeight = tileSize * maxScreenRow;



    KeyInput KeyInput = new KeyInput();
    Thread gameThread;

    Player player = new Player(this, KeyInput);

    // GAME SETTINGS
    private int fps = 60;

    TileManager tileM = new TileManager(this);
    
    // Set player position default
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyInput);
        this.setFocusable(true);
    }
    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        int intervalDefalt = 1000000000;
        double drawInterval = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;


        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;


            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= intervalDefalt){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

    }

    public  void update() {
        player.update();
    }

    public  void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        
        tileM.draw(g2);

        player.draw(g2);
        g2.dispose();
    }

}
