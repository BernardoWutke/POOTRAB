package entity;

import main.GamePanel;
import main.KeyInput;
import main.MouseInput;

import javax.imageio.ImageIO;

import ia.CarrinhoMaps;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;


public class Player extends  Entity {
    
    GamePanel gp;
    KeyInput KeyInput;
    int sizeMovement;

    Queue<Movimento> filaMovimento = new LinkedList<Movimento>();
    CarrinhoMaps carrinhoMaps;

    Entity entity = new Entity();

    public final int screenX;
    public final int screenY;

    private int x_buff;
    private int y_buff;

    private boolean isMoving = false;

    public Player(GamePanel gp, KeyInput KeyInput) {
        this.gp = gp;
        this.KeyInput = KeyInput;

        carrinhoMaps = new CarrinhoMaps(gp.mapPath);

        screenX = gp.getWidth() / 2;
        screenY = gp.getHeight() / 2;


        setDefaultValues();
        getPLayerImage();
        sizeMovement = gp.tileSize/this.speed;
    }

    public void setDefaultValues() {
        this.x = 0;
        this.y = 0;
        this.speed = 2;
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
        this.y = y;
    }

    int getY(int y){
        return this.y;
    }

    public boolean getIsMoving(){
        return isMoving;
    }

    public void decodificarRota(int ponto_x, int ponto_y){
        filaMovimento.clear();

        String caminho = carrinhoMaps.gerarCaminho(this.y/gp.tileSize,this.x/gp.tileSize , ponto_y, ponto_x);
        
        if (caminho.length() != 0){
            String[] stringMovimentos = caminho.split(";");
            for(int i = 0; i < stringMovimentos.length; i++){
                String[] moveText = stringMovimentos[i].split(",");
                Movimento movimento = new Movimento(Integer.parseInt(moveText[0]), Integer.parseInt(moveText[1]));
                filaMovimento.add(movimento);
            }
            proximoMovimento();
        }
    }

    public void proximoMovimento(){
        isMoving = true;
        Movimento movimento = null;
        if(!filaMovimento.isEmpty()){
            movimento = filaMovimento.poll();
            if(movimento.getX() == 1 && movimento.getY() == 0) goDown();
            else if(movimento.getX() == -1 && movimento.getY() == 0) goUp();
            else if(movimento.getX() == 0 && movimento.getY() == 1) goRight();
            else goLeft();
        } else {
            stop();
            isMoving = false;
        }
    }

    public void update() {
        if(Direction.up) {
            entity.setDirection("up");
            y -= speed;
        } else if (Direction.down) {
            entity.setDirection("down");
            y += speed;
        } else if (Direction.left) {
            entity.setDirection("left");
            x -= speed;
        } else if (Direction.right) {
            entity.setDirection("right");
            x += speed;
        }
    }

    public void goDown(){
        entity.setDirection("down");
        Direction.down = true;
    }
    public void goUp(){
        entity.setDirection("up");  
        Direction.up = true;
    }
    public void goRight(){
        entity.setDirection("right");
        Direction.right = true;

    }
    public void goLeft(){
        entity.setDirection("left");
        Direction.left = true;
    }

    public void stop(){
        Direction.down = false;
        Direction.up = false;
        Direction.left = false;
        Direction.right = false;
    }

    public void vereficarMovimento(){ 
        if (Math.abs(y_buff - y) >= gp.tileSize || Math.abs(x_buff - x) >= gp.tileSize) {
            x_buff = x;
            y_buff = y;
            stop();
            proximoMovimento();
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

    static class Direction {
        public static boolean up, down, left, right;
    }

    private class Movimento {
        private int x;
        private int y;

        Movimento(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
    }

    
}

