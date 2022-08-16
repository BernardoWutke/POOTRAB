package roads;

import entity.Player;

public class Trip {
    private int speed = 4;

    

    void goRight(int atual,int goTo){
        int atual_buff = (atual * 16) + 8;

        int goTo_buff = (goTo * 16) + 8;
        for (int i = atual_buff ; atual_buff < goTo; i += 4){
            
        }
    }
}
