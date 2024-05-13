package graphics;
import engine.Game;
import content.*;

import java.awt.*;
import java.awt.event.*;

public class GameWindow extends Frame implements KeyListener{

    private Rectangle[][] rectangles = new Rectangle[3][3];
    private Point[][] rectangleCorners = new Point[3][3]; // Массив углов верхних левых углов прямоугольников

    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isUp = false;
    private boolean isDown = false;

    public GameWindow() {
        super("Game Window");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension mySize = new Dimension(800, 900);
        setLocation((d.width - mySize.width) / 2, (d.height - mySize.height) / 2);
        setSize(mySize);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
                System.exit(0); // Добавляем завершение программы
            }
        });
        addKeyListener(this);
        setFocusable(true);

    }
    public Image currentImage(CellContents content){
        if (content instanceof Coin){
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/coin.png"));
        }if (content instanceof Enemy){
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/enemy.png"));
        }if (content instanceof Knight){
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/knightDEFAULT.png"));
        }if (content instanceof Weapon){
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/weapon.png"));
        }
        return null;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Dimension size = getSize();
        int rectWidth = size.width / 3;
        int rectHeight = size.height / 3;

        // Рисуем красивое название игры
        Font titleFont = new Font("Arial", Font.BOLD, 24);
        g.setFont(titleFont);
        FontMetrics metrics = g.getFontMetrics();
        int titleWidth = metrics.stringWidth("GameKnight");
        int titleX = (size.width - titleWidth) / 2;
        int titleY = 50;
        g.drawString("GameKnight", titleX, titleY);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                rectangles[row][col] = new Rectangle(col * rectWidth, row * rectHeight, rectWidth, rectHeight);
                rectangleCorners[row][col] = new Point(rectangles[row][col].x,
                        rectangles[row][col].y); // Угол верхнего левого угла прямоугольника
                g.drawRect(rectangles[row][col].x, rectangles[row][col].y,
                        rectangles[row][col].width, rectangles[row][col].height);
            }
        }
        for (int x=0;x<3;x++) {
            for (int y = 0; y<3; y++) {
                CellContents content = Game.whatMonsterOnField(x,y);
                Image currentImage = currentImage(content);
                Point point = rectangleCorners[x][y];
                int xCoordinate = point.x;
                int yCoordinate = point.y;
                g.drawImage(currentImage, xCoordinate+30, yCoordinate+50, this);
            }
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
    public void updateKnightLocation(Knight knight) {
        int newX = knight.getLocation().x;
        int newY = knight.getLocation().y;
        if (isLeft) {
            newX = newX-1;
            knight.setLocation(newX,newY);
        }
        if (isRight) {
            newX = newX+1;
            knight.setLocation(newX,newY);
        }
        if (isUp) {
            newY = newY+1;
            knight.setLocation(newX,newY);
        }
        if (isDown) {
            newY = newY - 1;
            knight.setLocation(newX, newY);
        }
    }

}
