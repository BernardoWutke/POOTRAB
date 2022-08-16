package tile;

import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.*;


import main.GamePanel;

public class TileManager {
    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage(){
        try{

            tile[0] = new Tile();
            tile[0].setTileImage(ImageIO.read(getClass().getResourceAsStream("/res/Assets/verde.png")));
            

            tile[1] = new Tile();
            tile[1].setTileImage(ImageIO.read(getClass().getResourceAsStream("/res/Assets/cinza.png")));
            
            
            tile[2] = new Tile();
            tile[2].setTileImage(ImageIO.read(getClass().getResourceAsStream("/res/Assets/buraco.png")));
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2 ){
        g2.drawImage(tile[0].getImage(), 0, 0, gp.getTileSize(), gp.getTileSize(), null);
        g2.drawImage(tile[1].getImage(), 48, 0, gp.getTileSize(), gp.getTileSize(), null);
        g2.drawImage(tile[2].getImage(), 96, 0, gp.getTileSize(), gp.getTileSize(), null);
    }
}
