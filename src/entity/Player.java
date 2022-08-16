package entity;

import main.GamePanel;
import main.KeyInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends  Entity {
    GamePanel gp;
    KeyInput KeyInput;

    Entity entity = new Entity();
    public Player(GamePanel gp, KeyInput KeyInput) {
        this.gp = gp;
        this.KeyInput = KeyInput;

        setDefaultValues();
        getPLayerImage();
    }

    public void setDefaultValues() {
        this.x = 100;
        this.y = 100;
        this.speed = 4;
        entity.setDirection("down");
    }

    public void getPLayerImage(){
        try {
           entity.setUp(ImageIO.read(getClass().getResourceAsStream("/res/Assets/carrinho_up.png")));
           entity.setDown(ImageIO.read(getClass().getResourceAsStream("/res/Assets/carrinho_down.png")));
           entity.setLeft(ImageIO.read(getClass().getResourceAsStream("/res/Assets/carrinho_left.png")));
           entity.setRight(ImageIO.read(getClass().getResourceAsStream("/res/Assets/carrinho_right.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {
        if(KeyInput.upPressed) {
            entity.setDirection("up");
            y -= speed;
        } else if (KeyInput.downPressed) {
            entity.setDirection("down");
            y += speed;
        } else if (KeyInput.leftPressed) {
            entity.setDirection("left");
            x -= speed;
        } else if (KeyInput.rightPressed) {
            entity.setDirection("right");
            x += speed;
        }
    }

    public void  draw(Graphics2D g) {
        BufferedImage img = null;

        switch (entity.getDirection()) {
            case "up":
                img = entity.getUp();
                break;
            case "down":
                img = entity.getDown();
                break;
            case "left":
                img = entity.getLeft();
                break;
            case "right":
                img = entity.getRight();
                break;
        }
        g.drawImage(img, x, y, gp.getTileSize(), gp.getTileSize(), null);
    }


}

