package graphics;
import content.*;
import engine.Game;

import java.awt.*;
import java.awt.event.*;

public class GameWindow extends Frame{
    private boolean gameOverFlag = false;
    private final GameDisplay gameDisplay = new GameDisplay();
    private final KeyHandler keyHandler = new KeyHandler();
    private final MenuDisplay menuDisplay = new MenuDisplay();
    private final MouseHandler mouseHandler = new MouseHandler(keyHandler);

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

        addMouseMotionListener(mouseHandler);
        addMouseListener(mouseHandler);
        addKeyListener(keyHandler);
        setFocusable(true);
        requestFocusInWindow();


        SoundPlayer.playSound("/music/soundMenu.wav", 0.2);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Dimension size = getSize();
        g.setColor(GameWindowTools.getYellow());
        Font titleFont = new Font("Arial", Font.BOLD, 24);
        g.setFont(titleFont);
        FontMetrics metrics = g.getFontMetrics();


        if (!keyHandler.getMenuEnter()) {
            if (menuDisplay.getMenuImage(menuDisplay.getCurrentImageIndex()) != null) {
                g.drawImage(menuDisplay.getMenuImage(menuDisplay.getCurrentImageIndex()),
                        (size.width - menuDisplay.getMenuImage(menuDisplay.getCurrentImageIndex()).getWidth(this)) / 2, 0, this);
                g.drawString("Game Knight", (size.width - metrics.stringWidth("Game Knight")) / 2, 50);
                g.drawString("Tab Enter to begin", (size.width - menuDisplay.getMenuImage(menuDisplay.getCurrentImageIndex()).getWidth(this)) / 2 + 30,
                        menuDisplay.getMenuImage(menuDisplay.getCurrentImageIndex()).getHeight(this) - 20);
                if (Game.getGainedMoney()!=0)
                    g.drawString("Gained money: " + Game.getGainedMoney(), 50, 50);
            }
            keyHandler.setMenuEnter();
        } else {
            gameDisplay.displayGameCircle(g, size, this);
            if (gameOverFlag) {
                gameDisplay.displayGameOver(g, size, this);
            }
        }
    }


    public void updateKnightLocation(Knight knight) {
        int newX = knight.getLocation().GetLocationX();
        int newY = knight.getLocation().GetLocationY();
        if (keyHandler.getIsLeft()) {
            newX = newX-1;
            knight.setLocation(newX,newY);
            keyHandler.setIsLeft();
        }
        if (keyHandler.getIsRight()) {
            newX = newX+1;
            knight.setLocation(newX,newY);
            keyHandler.setIsRight();
        }
        if (keyHandler.getIsUp()) {
            newY = newY - 1;
            knight.setLocation(newX,newY);
            keyHandler.setIsUp();
        }
        if (keyHandler.getIsDown()) {
            newY = newY + 1;
            knight.setLocation(newX, newY);
            keyHandler.setIsDown();
        }
        if (mouseHandler.getMouseClick()){
            Point rectangle = mouseHandler.getMouseClickedRectangle();
            int Y = rectangle.y;
            int X = rectangle.x;
            if (checkMouseClickedRectangle(rectangle, newX, newY)){
                knight.setLocation(X,Y);
            }
        }
        keyHandler.setIsKeyPressed(false);
    }
    private boolean checkMouseClickedRectangle(Point rectangle,int x,int y){
        int newY = rectangle.y;
        int newX = rectangle.x;
        return x == (newX + 1) && y == newY
                || x == (newX - 1) && y == newY
                || y == (newY + 1) && x == newX
                || y == (newY - 1) && x == newX;
    }

    public void setGameOverFlag(boolean flag){
        gameOverFlag = flag;
    }

    public boolean isKeyPressed() {
        return keyHandler.getIsKeyPressed();
    }

    public boolean getGameOverFlag() {
        return gameOverFlag;
    }

    public boolean tryAgain() {
        return keyHandler.getIsT();
    }
    public void setTryAgain() {
        keyHandler.setIsT();
    }
    public boolean exit(){
        return keyHandler.getIsF();
    }

    public void setBanBorder(int newXCoordinate, int newYCoordinate) {
        gameDisplay.setBanBorder(newXCoordinate,newYCoordinate);
    }
    public void setMenuWindow(){
        menuDisplay.startMenuImage(this);
        keyHandler.setMenuEnter();
    }

}

