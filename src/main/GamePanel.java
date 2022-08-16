package main;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;

public class GamePanel  extends JPanel implements Runnable {
    // SCREEN SETTINGS
    private final int originalTileSize = 16; //16x16 tile
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale; // 48x48
    private final int maxSCreenCol = 16;
    private final int maxScreenRow = 12;
    private final int screenWidth = tileSize * maxSCreenCol;
    private final int screenHeight = tileSize * maxScreenRow;

    KeyInput KeyInput = new KeyInput();
    Thread gameThread;

    // GAME SETTINGS
    private int fps = 60;

    // Set player position default
    private int playerX = 100;
    private int playerY = 100;
    private int playerSpeed = 10;
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
        if(KeyInput.upPressed) {
            playerY -= playerSpeed;
            playerY = playerY - playerSpeed;
        } else if (KeyInput.downPressed) {
            playerY += playerSpeed;
            playerY = playerY + playerSpeed;
        } else if (KeyInput.leftPressed) {
            playerX -= playerSpeed;
            playerX = playerX - playerSpeed;
        } else if (KeyInput.rightPressed) {
            playerX += playerSpeed;
            playerX = playerX + playerSpeed;
        }
    }

    public  void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);

        g2.fillRect(playerX,playerY, tileSize, tileSize);


        g2.dispose();
    }
}
