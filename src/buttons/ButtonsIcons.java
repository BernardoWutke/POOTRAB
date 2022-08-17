package buttons;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ButtonsIcons {
    BufferedImage cone_click, cone_unclick;

    public ButtonsIcons(){
        try {
            cone_click = ImageIO.read(getClass().getResourceAsStream("/res/assets/buttons/cone_button_click.png"));
            cone_unclick = ImageIO.read(getClass().getResourceAsStream("/res/assets/buttons/cone_button_noclick.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
