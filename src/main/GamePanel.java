package main;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;

public class GamePanel  extends JPanel implements Runnable {
    // SCREEn SETTINGS
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;
    final int tileSize = originalTileSize * scale; // 48x48
    final int maxSCreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxSCreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    KeyInput KeyInput = new KeyInput();
    Thread gameThread;

    // GAME SETTINGS
    int fps = 60;

    // Set player position default
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 10;
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
    @Override
    public void run() {
        double drawInterval = 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {


            // 1 UPDATE : update information such as chracter positions
            update();
            // 2 DRAW: draw the screeen with the update information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) (remainingTime ));

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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
