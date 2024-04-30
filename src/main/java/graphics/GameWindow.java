package graphics;

import content.CellContents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends Frame implements KeyListener{
    private Image imageKnight;
    private Image imageEnemy;

    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isUp = false;
    private boolean isDown = false;

    public GameWindow() {
        super("Game Window");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension mySize = new Dimension(720, 810);
        setLocation((d.width - mySize.width) / 2, (d.height - mySize.height) / 2);
        setSize(mySize);
        imageKnight = Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/knightDEFAULT.png"));
        imageEnemy = Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/enemy.png"));
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        });
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        int rows = 3;
        int cols = 3;
        int gap = 10;
        int labelHeight = 30;
        int availableHeight = 810 - labelHeight - gap;

        int rectangleWidth = (720 - (cols + 1) * gap) / cols;
        int rectangleHeight = (availableHeight - (rows + 1) * gap) / rows;


        g.setColor(Color.GRAY);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = j * (rectangleWidth + gap) + gap;
                int y = i * (rectangleHeight + gap) + gap + labelHeight + gap;
                g.fillRect(x, y, rectangleWidth, rectangleHeight);
            }
        }

        // Рисуем надпись
        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier", Font.BOLD | Font.ITALIC, 20));
        g.drawString("Game Knight", 720 / 2 - 50, gap + labelHeight + 10);
        g.setColor(Color.BLACK);
        if (imageKnight != null) {
            g.drawImage(imageKnight, 260, 305, this); // Рисуем изображение с координатами (50, 250)
            g.drawImage(imageEnemy, 495, 305, this);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("Key pressed: " + KeyEvent.getKeyText(keyCode));
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            isUp = true;
        } else if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            isLeft = true;
        } else if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            isDown = true;
        } else if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            isRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            isUp = false;
        } else if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            isLeft = false;
        } else if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            isDown = false;
        } else if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            isRight = false;
        }
    }
    public void updateCellContentLocation(CellContents content) {
        int newX = content.getLocation().x;
        int newY = content.getLocation().y;
        if (isLeft) {
            newX = newX-1;
            content.setLocation(newX,newY);
        }
        if (isRight) {
            newX = newX+1;
            content.setLocation(newX,newY);
        }
        if (isUp) {
            newY = newY+1;
            content.setLocation(newX,newY);
        }
        if (isDown) {
            newY = newY - 1;
            content.setLocation(newX, newY);
        }
    }

}
