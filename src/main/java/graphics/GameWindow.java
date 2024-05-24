package graphics;
import engine.Game;
import content.*;

import java.awt.*;
import java.awt.event.*;


public class GameWindow extends Frame{

    private boolean gameOverFlag = false;
    private final GameDisplay gameDisplay = new GameDisplay();
    private final KeyHandler keyHandler = new KeyHandler();
    private final MenuDisplay menuDisplay = new MenuDisplay();

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
                System.exit(0);
            }
        });

        addKeyListener(keyHandler);
        setFocusable(true);
        requestFocusInWindow();
        menuDisplay.startMenuImageTimer(this);
        SoundPlayer.playSound("/music/soundMenu.wav", 0.2);
    }



    @Override
    public void paint(Graphics g) {
        Color yellow = new Color(224, 166, 41);
        super.paint(g);
        Dimension size = getSize();
        g.setColor(yellow);
        Font titleFont = new Font("Arial", Font.BOLD, 24);
        g.setFont(titleFont);
        FontMetrics metrics = g.getFontMetrics();

        if (!keyHandler.getIsEnter()) {
            if (menuDisplay.getMenuImage(menuDisplay.getCurrentImageIndex()) != null) {
                g.drawImage(menuDisplay.getMenuImage(menuDisplay.getCurrentImageIndex()),
                        (size.width - menuDisplay.getMenuImage(menuDisplay.getCurrentImageIndex()).getWidth(this)) / 2, 0, this);
                g.drawString( "Game Knight", (size.width - metrics.stringWidth("Game Knight"))/2, 50);
                g.drawString( "Tab Enter to begin", (size.width - menuDisplay.getMenuImage(menuDisplay.getCurrentImageIndex()).getWidth(this)) / 2 +30,
                        menuDisplay.getMenuImage(menuDisplay.getCurrentImageIndex()).getHeight(this) - 20);

            }
            keyHandler.setIsEnter(false);
        } else {
            gameDisplay.displayGameCircle(g, size,this);
            if (gameOverFlag) gameDisplay.displayGameOver(g,size,this);
        }
    }


    public void updateKnightLocation(Knight knight) {
        int newX = knight.getLocation().GetLocationX();
        int newY = knight.getLocation().GetLocationY();
        if (keyHandler.getIsLeft()) {
            newX = newX-1;
            knight.setLocation(newX,newY);
            keyHandler.setIsLeft(false);
        }
        if (keyHandler.getIsRight()) {
            newX = newX+1;
            knight.setLocation(newX,newY);
            keyHandler.setIsRight(false);
        }
        if (keyHandler.getIsUp()) {
            newY = newY - 1;
            knight.setLocation(newX,newY);
            keyHandler.setIsUp(false);
        }
        if (keyHandler.getIsDown()) {
            newY = newY + 1;
            knight.setLocation(newX, newY);
            keyHandler.setIsDown(false);
        }
        KeyHandler.setIsKeyPressed(false);
    }


    public void gameOver(){gameOverFlag = true;}
    public void waitForKey() {
        while (!isKeyPressed()) {
            try {
                Thread.sleep(10); // Пауза в миллисекундах
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isKeyPressed() {
        return KeyHandler.getIsKeyPressed();
    }
}

