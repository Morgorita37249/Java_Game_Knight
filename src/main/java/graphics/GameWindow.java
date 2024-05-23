package graphics;
import engine.Game;
import content.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameWindow extends Frame implements KeyListener{

    private final Rectangle[][] rectangles = new Rectangle[3][3];
    private final Point[][] rectangleCorners = new Point[3][3]; // Массив углов верхних левых углов прямоугольников
    private final Image[] menuImages = new Image[]{
            Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/menu1.png")),
            Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/menu2.png")),
            Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/menu3.png"))
    };
    private int currentImageIndex = 0;

    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isUp = false;
    private boolean isDown = false;
    private boolean isKeyPressed = false;



    private boolean gameOverFlag = false;
    private boolean Enter = false;

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
        startMenuImageTimer();
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Dimension size = getSize();
        g.setColor(yellow);
        Font titleFont = new Font("Arial", Font.BOLD, 24);
        g.setFont(titleFont);
        FontMetrics metrics = g.getFontMetrics();

        if (!Enter) {
            if (menuImages[currentImageIndex] != null) {
                g.drawImage(menuImages[currentImageIndex], (size.width - menuImages[currentImageIndex].getWidth(this)) / 2, 0, this);
                g.drawString( "Game Knight", (size.width - metrics.stringWidth("Game Knight"))/2, 50);
                g.drawString( "Tab Enter to begin", 50, size.height - 20);
            }
            Enter = false;
        } else {
            displayGameCircle(g, size);
            if (gameOverFlag) displayGameOver(g);
        }
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
        }else if (keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_SPACE){
            Enter = true;
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
    private void startMenuImageTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isKeyPressed || Enter) {
                    timer.cancel();
                } else {
                    currentImageIndex = (currentImageIndex + 1) % menuImages.length;
                    repaint();
                }
            }
        }, 0, 500);
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
                        rectangles[row][col].y);
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
    private void displayGameOver(Graphics g){
        Image gameOverImage = Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/gameOVER.png"));;
        g.drawImage(gameOverImage, 0, 0, this);
    }
    public void gameOver(){
        gameOverFlag = true;
    }
    public void waitForKey() {
        while (!isKeyPressed) {
            try {
                Thread.sleep(10); // Пауза в миллисекундах
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
