package entity;

import main.GamePanel;
import main.KeyInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import ia.CarrinhoMaps;

public class Player extends  Entity {
    GamePanel gp;
    KeyInput KeyInput;
    int sizeMovement;

    private CarrinhoMaps carrinhoMaps;

    Entity entity = new Entity();

    public Player(GamePanel gp, KeyInput KeyInput) {
        this.gp = gp;
        this.KeyInput = KeyInput;
        
        carrinhoMaps = new CarrinhoMaps(gp.mapPath);

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

    private int[][] decodificarRota(){
        String caminho = carrinhoMaps.gerarCaminho(this.x/gp.tileSize, this.y/gp.tileSize, 0, 3);
        String[] stringMovimentos = caminho.split(";");
        int[][] matrizMovimentos = new int[stringMovimentos.length][2];

        for(int i = 0; i < stringMovimentos.length; i++){
            String[] mov = stringMovimentos[i].split(",");
            matrizMovimentos[i][0] = Integer.parseInt(mov[0]);
            matrizMovimentos[i][1] = Integer.parseInt(mov[1]);
        }

        return matrizMovimentos;
    }

    public void percorrerRota(){
        int[][] movimentos = decodificarRota();
        for (int[] mov : movimentos) {

            if(mov[0] == 1 && mov[1] == 0) goDown();
            else if(mov[0] == -1 && mov[1] == 0) goUp();
            else if(mov[0] == 0 && mov[1] == 1) goRight();
            else goLeft();

            try{
                Thread.sleep(200);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            gp.repaint();
        }
    }
    
    public void start(){
       
        
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

