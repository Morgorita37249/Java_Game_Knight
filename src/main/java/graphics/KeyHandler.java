package graphics;

import java.awt.event.*;

public class KeyHandler implements KeyListener {
    private static boolean isKeyPressed = false;
    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isUp = false;
    private boolean isDown = false;

    private boolean isF = false;
    private boolean isT = false;

    private boolean menuEnter = false;

    boolean getIsT() {
        return isT;
    }
    boolean getIsF() {
        return isF;
    }
    @Override
    public void keyTyped(KeyEvent e) {}

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

    @Override
    public void keyReleased(KeyEvent e) {}

    public boolean getIsLeft() {return isLeft;}
    public void setIsLeft(boolean keyLeft) {isLeft = keyLeft;}

    public boolean getIsRight() {return isRight;}
    public void setIsRight(boolean keyRight) {isRight = keyRight;}

    public boolean getIsUp() {return isUp;}
    public void setIsUp(boolean keyUp){isUp = keyUp;}

    public boolean getIsDown() {return isDown;}
    public void setIsDown(boolean keyDown){isDown = keyDown;}

    public boolean getMenuEnter() {return menuEnter;}
    public void setMenuEnter(boolean isEnter) {menuEnter = isEnter;}

    public static void setIsKeyPressed(boolean keyPressed){isKeyPressed = keyPressed;}
    public static boolean getIsKeyPressed() {return isKeyPressed;}


}