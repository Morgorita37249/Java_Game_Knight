package graphics;

import java.awt.event.*;
import java.awt.*;

public class MouseHandler extends MouseAdapter {

    private final boolean[][] mouseClickedRectangle = new boolean[3][3];
    private final KeyHandler keyHandler;
    private boolean mouseClick = false;
    public MouseHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked at: " + e.getX() + ", " + e.getY());
        Point point = e.getPoint();
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                if (GameDisplay.getRectangle(col, row).contains(point)) {
                    mouseClickedRectangle[col][row] = true;
                    keyHandler.setIsKeyPressed(true);
                    mouseClick = true;
                }
            }
        }
    }

    Point getMouseClickedRectangle() {
        for (int row = 0; row < 3; row++) {
            for  (int col = 0; col < 3; col++){
                if (mouseClickedRectangle[col][row]) {
                    resetMouseClickedRectangle(col, row);
                    mouseClick = false;
                    return new Point( row,col);
                }
            }
        }
        return null;
    }
    boolean getMouseClick(){
        return mouseClick;
    }
    void resetMouseClickedRectangle(int row, int col) {
        mouseClickedRectangle[row][col] = false;
    }
}