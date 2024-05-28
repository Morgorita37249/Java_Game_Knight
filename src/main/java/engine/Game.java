package engine;
import content.*;
import graphics.*;
import java.util.Random;
public class Game {
    private final static int sizeX = 3;
    private final static int sizeY = 3;
    private static final CellContents [][] field = new CellContents[sizeX][sizeY];
    private final GameWindow window;
    private static int gainedMoney = 0;

    private void setLocationOnField(CellContents content) {
        if (content.location.GetLocationX() >= 0 && content.location.GetLocationX() < sizeX &&
                content.location.GetLocationY() >= 0 && content.location.GetLocationY() < sizeY) {
            field[content.location.GetLocationX()][content.location.GetLocationY()] = content;
        } else {
            System.err.println("Error: Invalid location for content.");
        }
    }
    public static CellContents whatMonsterOnField(int coordinateX, int coordinateY) {
        try {
            return field[coordinateX][coordinateY];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: Coordinates out of bounds - " + e.getMessage());
            return null;
        }
    }
    private void contentSelectionOnField(int xCoordinate, int yCoordinate){
        try {
            Random random = new Random();
            int randomNumber = random.nextInt(5);
            if (randomNumber == 0) {
                Enemy enemy = new Enemy();
                enemy.setLocation(xCoordinate, yCoordinate);
                setLocationOnField(enemy);
            } else if (randomNumber == 1) {
                Coin coin = new Coin();
                coin.setLocation(xCoordinate, yCoordinate);
                setLocationOnField(coin);
            } else if (randomNumber == 2) {
                Weapon weapon = new Weapon();
                weapon.setLocation(xCoordinate, yCoordinate);
                setLocationOnField(weapon);
            } else if (randomNumber == 3){
                HealBottle bottle = new HealBottle();
                bottle.setLocation(xCoordinate, yCoordinate);
                setLocationOnField(bottle);
            } else {
                Poison poison = new Poison();
                poison.setLocation(xCoordinate, yCoordinate);
                setLocationOnField(poison);
            }
        } catch (Exception e) {
            System.out.println("Error during content selection and setting: " + e.getMessage());
        }
    }
    private void setNewField(){
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++){
                contentSelectionOnField(i,j);
            }
        }
    }

    Game() {
        window = new GameWindow();
    }

    public void startGame() {
        Knight knight = new Knight();
        setLocationOnField(knight);
        setNewField();
        GameCircle(knight, window);
    }


    void newGame(){
        try {
            window.setVisible(true);

            while (!window.getGameOverFlag()) {
                window.setMenuWindow();
                startGame();
                window.setGameOverFlag(true);
                long startTime = System.currentTimeMillis();
                long checkInterval = 300;
                while (window.getGameOverFlag()) {
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - startTime >= checkInterval) {
                        startTime = currentTime;
                        if (window.tryAgain()) {
                            window.setTryAgain();
                            window.setGameOverFlag(false);
                            window.repaint();
                        }
                        if (window.exit()) {
                            window.dispose();
                            System.exit(0);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error in new game loop: " + e.getMessage());
        }
    }

    private void GameCircle(Knight knight, GameWindow window){
        long startTime = System.currentTimeMillis();
        long checkInterval = 300;
        try {
            while (!(knight.getHealth() <= 0)) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - startTime >= checkInterval) {
                    startTime = currentTime;


                    if (window.isKeyPressed()) {
                        int previousXCoordinate = knight.getLocation().GetLocationX();
                        int previousYCoordinate = knight.getLocation().GetLocationY();
                        window.updateKnightLocation(knight);
                        int newXCoordinate = knight.getLocation().GetLocationX();
                        int newYCoordinate = knight.getLocation().GetLocationY();


                        if (newXCoordinate < 0 || newYCoordinate < 0 || newXCoordinate >= 3 || newYCoordinate >= 3) {
                            window.setBanBorder(newXCoordinate, newYCoordinate);
                            knight.setLocation(previousXCoordinate, previousYCoordinate);
                            window.repaint();
                            continue;
                        }

                        CellContents content = whatMonsterOnField(newXCoordinate, newYCoordinate);
                        if (content != null) {

                            if (content instanceof Enemy) {
                                if (knight.getWeapon() != 0) {
                                    int enemyHealth = content.getHealth();
                                    content.setHealth(content.getHealth() - knight.getWeapon());
                                    knight.setWeapon(knight.getWeapon() - enemyHealth);
                                    if (knight.getWeapon() <= 0) knight.setWeapon(0);
                                    if (content.getHealth() <= 0) {
                                        contentSelectionOnField(previousXCoordinate, previousYCoordinate);
                                        if (knight.getWeapon() < 0) knight.setWeapon(0);
                                    } else {
                                        knight.setLocation(previousXCoordinate, previousYCoordinate);
                                    }
                                } else {
                                    knight.setHealth(knight.getHealth() - content.getHealth());
                                    contentSelectionOnField(previousXCoordinate, previousYCoordinate);
                                }
                            }

                            if (content instanceof Coin) {
                                int coins = content.getHealth();
                                knight.setWallet(coins);
                                contentSelectionOnField(previousXCoordinate, previousYCoordinate);
                            }

                            if (content instanceof Weapon) {
                                if (knight.getWeapon() != 0) {
                                    if (knight.getWeapon() < content.getHealth()) {
                                        knight.setWallet(knight.getWeapon());
                                        knight.setWeapon(content.getHealth());
                                    } else {
                                        knight.setWallet(content.getHealth());
                                    }
                                } else {
                                    knight.setWeapon(content.getHealth());
                                }
                                contentSelectionOnField(previousXCoordinate, previousYCoordinate);
                            }
                            if (content instanceof HealBottle) {
                                if (knight.getHealth() < knight.getBaseHealth()) {
                                    knight.setHealth(knight.getHealth() + content.getHealth());
                                    if (knight.getHealth() > knight.getBaseHealth())
                                        knight.setHealth(knight.getBaseHealth());
                                }
                                knight.setPoisonTime(0);
                                contentSelectionOnField(previousXCoordinate, previousYCoordinate);
                            }
                            if (content instanceof Poison) {
                                knight.setPoisonTime(knight.getPoisonTime() + content.getHealth());
                                contentSelectionOnField(previousXCoordinate, previousYCoordinate);
                            }
                        }
                        if (knight.getPoisonTime() > 0) {
                            int poisonTime = knight.getPoisonTime();
                            knight.setPoisonTime(poisonTime - 1);
                            knight.setHealth(knight.getHealth() - 1);
                        }
                        setLocationOnField(knight);
                        window.repaint();
                    }
                }
            }
            gainedMoney = gainedMoney + knight.getWallet();
        } catch (Exception e) {
            System.out.println("Error in game loop: " + e.getMessage());
        }
    }

    public static int getGainedMoney() {
        return gainedMoney;
    }
}

