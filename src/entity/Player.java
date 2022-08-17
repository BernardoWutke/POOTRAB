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
    int sizeMovement;

    Entity entity = new Entity();
    public Player(GamePanel gp, KeyInput KeyInput) {
        this.gp = gp;
        this.KeyInput = KeyInput;
        
        setDefaultValues();
        getPLayerImage();
        sizeMovement = gp.tileSize/this.speed;
    }

    public void setDefaultValues() {
        this.x = 0;
        this.y = 0;
        this.speed = 1;
        entity.setDirection("down");
    }

    public void getPLayerImage(){
        try {
            entity.setUp(ImageIO.read(getClass().getResourceAsStream("/res/assets/car/car_up.png")));
            entity.setDown(ImageIO.read(getClass().getResourceAsStream("/res/assets/car/car_down.png")));
            entity.setLeft(ImageIO.read(getClass().getResourceAsStream("/res/assets/car/car_left.png")));
            entity.setRight(ImageIO.read(getClass().getResourceAsStream("/res/assets/car/car_right.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    void setX(int x){
        this.x = x;
    }

    int getX(int x){
        return this.x;
    }

    void setY(int y){
        this.x = y;
    }

    int getY(int y){
        return this.x;
    }

    public void pecorrerRota(){
        
    }
    
    public void start(){
        goDown();
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

    public void goDown(){
        entity.setDirection("down");
        for(int i = 0; i < sizeMovement; i++) {
            y += speed;
            
        } 
    }
    public void goUp(){
        entity.setDirection("up");
        for(int i = 0; i < sizeMovement; i++) y -= speed;
    }
    public void goRight(){
        entity.setDirection("right");
        for(int i = 0; i < sizeMovement; i++) x += speed;
    }
    public void goLeft(){
        entity.setDirection("left");
        for(int i = 0; i < sizeMovement; i++) x -= speed;
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

