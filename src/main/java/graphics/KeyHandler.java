package graphics;

import java.awt.event.*;

public class KeyHandler extends KeyAdapter {
    private boolean isKeyPressed = false;
    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isUp = false;
    private boolean isDown = false;

    private boolean isF = false;
    private boolean isT = false;

    private boolean menuEnter = false;


    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("Key pressed: " + KeyEvent.getKeyText(keyCode));  // Вывод в консоль
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            isUp = true;
        } else if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            isLeft = true;
        } else if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            isDown = true;
        } else if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            isRight = true;
        } else if (keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_SPACE){
            menuEnter = true;
        } else if (keyCode == KeyEvent.VK_F){
            isF = true;
        } else if (keyCode == KeyEvent.VK_T){
            isT = true;
        }
        isKeyPressed = true;
    }


    boolean getIsLeft() {return isLeft;}
    void setIsLeft() {isLeft = false;}

    boolean getIsRight() {return isRight;}
    void setIsRight() {isRight = false;}

    boolean getIsUp() {return isUp;}
    void setIsUp(){isUp = false;}

    boolean getIsDown() {return isDown;}
    void setIsDown(){isDown = false;}

    boolean getMenuEnter() {return menuEnter;}
    void setMenuEnter() {menuEnter = false;}

    void setIsKeyPressed(boolean keyPressed){isKeyPressed = keyPressed;}
    boolean getIsKeyPressed() {return isKeyPressed;}

    boolean getIsT() {
        return isT;
    }
    void setIsT() {
        isT = false;
    }
    boolean getIsF() {
        return isF;
    }

}