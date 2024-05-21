package engine;
import content.*;
import graphics.*;
import java.util.Random;
public class Game {
    //движок для передвижения рыцаря по полю и действий с
    private final static int sizeX = 3;
    private final static int sizeY = 3;
    //само поле игры
    private static CellContents [][] field = new CellContents[sizeX][sizeY];

    private void setLocationOnField(CellContents content) {
        if (content.location.x >= 0 && content.location.x < sizeX &&
                content.location.y >= 0 && content.location.y < sizeY) {
            field[content.location.x][content.location.y] = content;
        }
    }
    public static CellContents whatMonsterOnField(int coordinateX, int coordinateY) {
        return field[coordinateX][coordinateY];
    }
    private void contentSelectionOnField(int xCoordinate, int yCoordinate){
        try {
            Random random = new Random();
            int randomNumber = random.nextInt(3);
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
            }
        } catch (Exception e) {
            System.out.println("Error during content selection and setting: " + e.getMessage());
        }
    }
    private void setNewField(){
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++){
                if (i==1 && i==j) continue;
                else {
                    contentSelectionOnField(i,j);
                }
                // центральный инициализируем отдельно, тк нельзя иначе обращаться к главвному герою;
            }
        }
    }


    public void newGame(){
        GameWindow window = new GameWindow();

        window.setVisible(true);
        Knight knight = new Knight();
        setLocationOnField(knight);
        setNewField();
        //нереально навалить перемещения клеток
        try {
            while (!(knight.getHealth() <= 0)){
                window.waitForKey();
                if (window.isKeyPressed()){
                    int previousXCoordinate = knight.getLocation().x;
                    int previousYCoordinate = knight.getLocation().y;
                    window.updateKnightLocation(knight);
                    int newXCoordinate = knight.getLocation().x;
                    int newYCoordinate = knight.getLocation().y;


                    if  (newXCoordinate<0 || newYCoordinate<0 || newXCoordinate>=3 || newYCoordinate>=3) {
                        knight.setLocation(previousXCoordinate, previousYCoordinate);
                        continue;
                    }


                    //это игровой цикл. в нем надо гонять по кругу
                    CellContents content = whatMonsterOnField(newXCoordinate, newYCoordinate);
                    if (content != null) {

                        if (content instanceof Enemy) {
                            if (knight.getWeapon() != 0) {
                                int enemyHealth = content.getHealth();
                                content.setHealth(content.getHealth() - knight.getWeapon());
                                knight.setWeapon(knight.getWeapon()-enemyHealth);
                                if (knight.getWeapon()<=0) knight.setWeapon(0);
                                if (content.getHealth()<=0) {
                                    contentSelectionOnField(previousXCoordinate, previousYCoordinate);
                                    if (knight.getWeapon()<0)  knight.setWeapon(0);
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
                            knight.setWallet(knight.getWeapon());
                            knight.setWeapon(content.getHealth());
                            contentSelectionOnField(previousXCoordinate, previousYCoordinate);
                        }
                    }
                    setLocationOnField(knight);
                    window.repaint();
                }
            }
        } catch (Exception e) {
            System.out.println("Error in game loop: " + e.getMessage());
        }




    }
}
