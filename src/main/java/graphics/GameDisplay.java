package graphics;

import engine.Game;
import content.*;

import java.awt.*;

public class GameDisplay {

    private final Rectangle[][] rectangles = new Rectangle[3][3];
    private final Point[][] rectangleCorners = new Point[3][3]; // Массив углов верхних левых углов прямоугольников
    private final Color pink = new Color(92, 13, 26);
    private final Color grey = new Color(45, 45, 45);
    private final Color yellow = new Color(224, 166, 41);
    private final Color green = new Color(67, 164, 7);

    void displayGameCircle(Graphics g, Dimension size,GameWindow gameWindow){
        Image backgroundImage = Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/background.png"));
        g.drawImage(backgroundImage,0, 0, gameWindow);
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
                int xCoordinate = rectWidth/2- currentImage.getWidth(gameWindow)/2+point.x;
                int yCoordinate = point.y+70;
                String health = String.valueOf(content.getHealth());

                g.drawImage(currentImage,  xCoordinate, yCoordinate, gameWindow);

                if (content instanceof Knight){
                    String coins = String.valueOf(((Knight) content).getWallet());
                    g.drawString( coins, coinsWidth + 20, 50);
                    if (((Knight) content).getWeapon()!=0){
                        Image weaponImage = Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/knightWEAPON.png"));
                        g.drawImage(weaponImage, xCoordinate, yCoordinate, gameWindow);
                        g.setColor(grey);
                        String weapon = String.valueOf(((Knight) content).getWeapon());
                        g.drawString( weapon,  xCoordinate+50, yCoordinate+80);
                    }
                    if (((Knight) content).getPoisonTime()!=0){
                        Image poisonedKnight = Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/knightPOISONED.png"));
                        g.drawImage(poisonedKnight, xCoordinate, yCoordinate, gameWindow);
                        g.setColor(green);
                        String poison = String.valueOf(((Knight) content).getPoisonTime());
                        g.drawString(poison, xCoordinate+160, yCoordinate+40);
                    }
                }
                g.setColor(pink);
                g.drawString(health, xCoordinate+40, yCoordinate+30);
                g.setColor(yellow);

            }
        }
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
        }if (content instanceof Poison){
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/poison.png"));
        }
        return null;
    }
    void displayGameOver(Graphics g, Dimension size, GameWindow gameWindow){
        Image gameOverImage = Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/gameOVER.png"));
        Image background = Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/background.png"));
        g.drawImage(background, 0, 0, gameWindow);
        g.drawImage(gameOverImage, (size.width - gameOverImage.getWidth(gameWindow)) / 2, 0,gameWindow);
    }

}