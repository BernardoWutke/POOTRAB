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
            tile[0].setTileImage(ImageIO.read(getClass().getResourceAsStream("/res/assets/verde.png")));
            

            tile[1] = new Tile();
            tile[1].setTileImage(ImageIO.read(getClass().getResourceAsStream("/res/assets/cinza.png")));
            
            
            tile[2] = new Tile();
            tile[2].setTileImage(ImageIO.read(getClass().getResourceAsStream("/res/assets/buraco.png")));
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2 ){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.getMaxSCreenCol() && row < gp.getMaxScreenRow()){
            g2.drawImage(tile[0].getImage(), x, y, gp.getTileSize(),gp.getTileSize(), null);
            col++;
            x += gp.getTileSize();
            if(col == gp.getMaxSCreenCol()){
                col = 0;
                row++;
                x = 0;
                y += gp.getTileSize();
            }
        }
    }
}
