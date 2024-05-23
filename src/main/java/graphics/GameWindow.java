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
    private boolean Enter = false;

    private boolean isKeyPressed = false;

    private final Color brown = new Color(44, 16, 0);
    private final Color pink = new Color(92, 13, 26);
    private final Color grey = new Color(45, 45, 45);
    private final Color yellow = new Color(224, 166, 41);

    public GameWindow() {
        super("GameKnight");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension mySize = new Dimension(800, 950);
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
        requestFocusInWindow();
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Dimension size = getSize();

        g.setColor(brown);
        Font titleFont = new Font("Arial", Font.BOLD, 24);
        g.setFont(titleFont);
        FontMetrics metrics = g.getFontMetrics();
        g.drawString( "Game Knight", (size.width - metrics.stringWidth("Game Knight"))/2, 50);

        displayGameCircle(g, size);


    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

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
        }
        isKeyPressed = true;  // Убедитесь, что флаг нажатия клавиши устанавливается
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    public void updateKnightLocation(Knight knight) {
        int newX = knight.getLocation().GetLocationX();
        int newY = knight.getLocation().GetLocationY();
        if (isLeft) {
            newX = newX-1;
            knight.setLocation(newX,newY);
            isLeft = false;
        }
        if (isRight) {
            newX = newX+1;
            knight.setLocation(newX,newY);
            isRight = false;
        }
        if (isUp) {
            newY = newY - 1;
            knight.setLocation(newX,newY);
            isUp = false;
        }
        if (isDown) {
            newY = newY + 1;
            knight.setLocation(newX, newY);
            isDown = false;
        }
        isKeyPressed = false;
    }

    // указывает, что клавиша нажата
    public void waitForKey() {
        while (!isKeyPressed) {
            try {
                Thread.sleep(10); // Пауза в миллисекундах
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public boolean isKeyPressed(){
        return isKeyPressed;
    }


    private Image currentImage(CellContents content){
        if (content instanceof Coin){
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/coin.png"));
        }if (content instanceof Enemy){
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/enemy.png"));
        }if (content instanceof Knight){
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/knightDEFAULT.png"));
        }if (content instanceof Weapon){
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/weapon.png"));
        }if (content instanceof HealBottle){
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/heal_bottle.png"));
        }
        return null;
    }

    private void displayGameCircle(Graphics g,Dimension size){
        Image backgroundImage = Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/background.png"));
        g.drawImage(backgroundImage,0, 0, this);
        int rectWidth = size.width / 3;
        int rectHeight = size.height / 3;
        FontMetrics metrics = g.getFontMetrics();
        int coinsWidth = metrics.stringWidth("Coins");
        g.setColor(yellow);
        g.drawString("Coins ", 10, 50);

        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++)  {
                rectangles[row][col] = new Rectangle(col * rectWidth, row * rectHeight, rectWidth, rectHeight);
                rectangleCorners[row][col] = new Point(rectangles[row][col].x,
                        rectangles[row][col].y); // Угол верхнего левого угла прямоугольника
                //g.drawRect(rectangles[row][col].x, rectangles[row][col].y,
                        //rectangles[row][col].width, rectangles[row][col].height);
            }
        }



        for (int y = 0; y<3; y++) {
            for (int x = 0; x < 3; x++) {

                CellContents content = Game.whatMonsterOnField(x,y);
                Image currentImage = currentImage(content);
                Point point = rectangleCorners[y][x];
                int xCoordinate = point.x;
                int yCoordinate = point.y;
                String health = String.valueOf(content.getHealth());
                g.drawImage(currentImage, xCoordinate+30, yCoordinate+70, this);

                if (content instanceof Knight){
                    String coins = String.valueOf(((Knight) content).getWallet());
                    g.drawString( coins, coinsWidth + 20, 50);
                    if (((Knight) content).getWeapon()!=0){
                        Image weaponImage = Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/knightWEAPON.png"));
                        g.drawImage(weaponImage, xCoordinate+30, yCoordinate+70, this);
                        g.setColor(grey);
                        String weapon = String.valueOf(((Knight) content).getWeapon());
                        g.drawString( weapon,  xCoordinate+70, yCoordinate+150);
                    }
                }
                g.setColor(pink);
                g.drawString(health, xCoordinate+70, yCoordinate+100);
                g.setColor(yellow);

            }
        }
    }
    private void displayGameOver(Graphics g){
        Image gameOverImage = Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/gameOVER.png"));;
        g.drawImage(gameOverImage, 0, 0, this);
    }

}
