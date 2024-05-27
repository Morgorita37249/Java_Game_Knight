package graphics;

import engine.Game;
import content.*;

import java.awt.*;

public class GameDisplay {
    private static final Rectangle[][] rectangles = new Rectangle[3][3];
    private boolean banBorderFlag = false;
    private Image banBorderImage;

    void displayGameCircle(Graphics g, Dimension size, GameWindow gameWindow) {
        Image backgroundImage = Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/background.png"));
        g.drawImage(backgroundImage, 0, 0, gameWindow);
        int rectWidth = size.width / 3;
        int rectHeight = size.height / 3;
        FontMetrics metrics = g.getFontMetrics();
        int coinsWidth = metrics.stringWidth("Coins");
        g.setColor(GameWindowTools.getYellow());
        g.drawString("Coins ", 10, 50);

        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                rectangles[row][col] = new Rectangle(col * rectWidth, row * rectHeight, rectWidth, rectHeight);
            }
        }

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {

                CellContents content = Game.whatMonsterOnField(x, y);
                Image currentImage = currentImage(content);
                Point point = new Point(rectangles[y][x].x, rectangles[y][x].y);
                assert currentImage != null;
                int xCoordinate = rectWidth / 2 - currentImage.getWidth(gameWindow) / 2 + point.x;
                int yCoordinate = point.y + 70;
                String health = String.valueOf(content.getHealth());

                g.drawImage(currentImage, xCoordinate, yCoordinate, gameWindow);


                if (content instanceof Knight) {
                    String coins = String.valueOf(((Knight) content).getWallet());
                    g.drawString(coins, coinsWidth + 20, 50);
                    if (((Knight) content).getWeapon() != 0) {
                        Image weaponImage = Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/knightWEAPON.png"));
                        g.drawImage(weaponImage, xCoordinate, yCoordinate, gameWindow);
                        g.setColor(GameWindowTools.getGrey());
                        String weapon = String.valueOf(((Knight) content).getWeapon());
                        g.drawString(weapon, xCoordinate + 50, yCoordinate + 80);
                    }
                    if (((Knight) content).getPoisonTime() != 0) {
                        Image poisonedKnight = Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/knightPOISONED.png"));
                        g.drawImage(poisonedKnight, xCoordinate, yCoordinate, gameWindow);
                        g.setColor(GameWindowTools.getGreen());
                        String poison = String.valueOf(((Knight) content).getPoisonTime());
                        g.drawString(poison, xCoordinate + 160, yCoordinate + 40);
                    }
                    if (banBorderFlag) {
                        g.drawImage(banBorderImage, xCoordinate, yCoordinate, gameWindow);
                        banBorderFlag = false;
                    }
                }
                g.setColor(GameWindowTools.getPink());
                g.drawString(health, xCoordinate + 40, yCoordinate + 30);
                g.setColor(GameWindowTools.getYellow());

            }
        }
    }

    private Image currentImage(CellContents content) {
        if (content instanceof Coin) {
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/coin.png"));
        }
        if (content instanceof Enemy) {
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/enemy.png"));
        }
        if (content instanceof Knight) {
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/knightDEFAULT.png"));
        }
        if (content instanceof Weapon) {
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/weapon.png"));
        }
        if (content instanceof HealBottle) {
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/heal_bottle.png"));
        }
        if (content instanceof Poison) {
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/poison.png"));
        }
        return null;
    }

    public void setBanBorder(int newXCoordinate, int newYCoordinate) {
        banBorderImage = banBorders(newXCoordinate, newYCoordinate);
        banBorderFlag = true;
    }

    private Image banBorders(int x, int y) {
        if (x < 0) {
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/dontGoLeft.png"));
        }
        if (x > 2) {
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/dontGoRight.png"));
        }
        if (y > 2) {
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/dontGoDown.png"));
        }
        if (y < 0) {
            return Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/dontGoUp.png"));
        }
        return null;
    }

    void displayGameOver(Graphics g, Dimension size, GameWindow gameWindow) {
        Image gameOverImage = Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/gameOVER.png"));
        Image background = Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/background.png"));
        g.drawImage(background, 0, 0, gameWindow);
        g.drawImage(gameOverImage, (size.width - gameOverImage.getWidth(gameWindow)) / 2, 0, gameWindow);
        g.setColor(GameWindowTools.getGrey());
        g.drawString("Press F to pay respect...", size.width / 2 - 125,
                size.height / 2 + 270);
        g.drawString("Press T to try again", size.width / 2 - 110,
                size.height / 2 + 240);

    }

    static Rectangle getRectangle(int x, int y) {
        return rectangles[x][y];
    }
}
