package main;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static com.sun.java.accessibility.util.AWTEventMonitor.*;

public class MouseInput implements MouseListener {
    private boolean clicked = false;
    private int mousePositionX, mousePositionY;

    @Override
    public void mouseClicked(MouseEvent e) {

        if(!clicked){
            clicked = true;
            mousePositionX = e.getX();
            mousePositionY = e.getY(); 
        }
    }


    public int getMousePositionX() {
        return mousePositionX;
    }
    public int getMousePositionY() {
        return mousePositionY;
    }
    public boolean hasClicked(){
        return clicked;
    }
    public void setClicked(boolean bool){
        this.clicked = bool;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
