package graphics;
import java.awt.*;
import java.awt.event.*;

//- W: KeyEvent.VK_W
//- A: KeyEvent.VK_A
//- S: KeyEvent.VK_S
//- D: KeyEvent.VK_D
//- KeyEvent.VK_UP - клавиша "Вверх"
//- KeyEvent.VK_DOWN - клавиша "Вниз"
//- KeyEvent.VK_LEFT - клавиша "Влево"
//- KeyEvent.VK_RIGHT - клавиша "Вправо"
public class Clicks extends Frame implements KeyListener {
    protected StringBuffer m_currentText = new StringBuffer();
    public Clicks() {
        addKeyListener(this);
        setSize(400, 400);
        setVisible(true);
    }
    public void keyPressed(KeyEvent e) {
        System.out.println("Char: \'"+e.getKeyCode()+"\' "+Integer.toHexString(e.getKeyCode()));
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {

        } else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){};
    }
    public void keyReleased(KeyEvent e) {
        System.out.println("Key released: " + e.getKeyChar());
    }
    public void keyTyped(KeyEvent e) {
        System.out.println("Key typed: " + e.getKeyChar());
    }


}
