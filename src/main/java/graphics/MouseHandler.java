package graphics;

import java.awt.event.*;
import java.awt.*;
public class MouseHandler implements MouseListener, MouseMotionListener {

    private final boolean[][] mouseClickedRectangle = new boolean[3][3];
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked at: " + e.getX() + ", " + e.getY());
        Point point = e.getPoint();
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                if (GameDisplay.getRectangle(row,col).contains(point)){
                    mouseClickedRectangle[row][col] = true;

                }
            }
        }
        KeyHandler.setIsKeyPressed(true);
    }
    boolean getMouseClickedRectangle(int row,int col){
        return mouseClickedRectangle[row][col];
    }
    void setMouseClickedRectangle(){
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                mouseClickedRectangle[row][col] = false;
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
}