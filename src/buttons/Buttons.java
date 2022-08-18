package buttons;

import main.MouseInput;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Buttons {

    public  boolean cone_click = false;
    MouseInput mouseInput;
    public Buttons(MouseInput mouseInput){
        this.mouseInput = mouseInput;
    }

    public  void draw(Graphics2D g){
        try {
            if(cone_click){
                g.drawImage(ImageIO.read(getClass().getResourceAsStream("/res/assets/buttons/cone_button_click.png")), 544, 480, null);
            } else {
                g.drawImage(ImageIO.read(getClass().getResourceAsStream("/res/assets/buttons/cone_button_noclick.png")), 544, 480, null);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // public void update(){
    //     if(mouseInput.x >= 544 && mouseInput.x <= 544 + 64 && mouseInput.y >= 480 && mouseInput.y <= 480 + 64){
    //         if(mouseInput.clicked){
    //             cone_click = true;
    //             mouseInput.clicked = false;
    //         }
    //     } else {
    //         cone_click = false;
    //     }
    // }

}
