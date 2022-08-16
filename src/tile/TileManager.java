package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import java.awt.*;


import main.GamePanel;

public class TileManager {
    private GamePanel gp;
    private Tile[] tile;
    private int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxSCreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("/res/maps/map01.txt");
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

    public void loadMap(String NameMap){
        try {
            InputStream is = getClass().getResourceAsStream(NameMap);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;

            while (col < gp.maxSCreenCol && row < gp.maxScreenRow) {
                String line =  br.readLine();

                while (col < gp.maxSCreenCol && row < gp.maxScreenRow) {
                    String numbers[] = line.split(" ");  
                    int num = Integer.parseInt(numbers[col]);  

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxSCreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
                
            

        } catch (Exception e) {
            
        }
    }

    public void draw(Graphics2D g2 ){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxSCreenCol && row < gp.maxScreenRow){

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].getImage(), x, y, gp.getTileSize(),gp.getTileSize(), null);
            col++;
            x += gp.getTileSize();
            if(col == gp.maxSCreenCol){
                col = 0;
                row++;
                x = 0;
                y += gp.getTileSize();
            }
        }
    }
}
