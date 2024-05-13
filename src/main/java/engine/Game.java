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

    private void setLocationOnField(CellContents content){
        field[content.location.x][content.location.y]=content;
    }
    public static CellContents whatMonsterOnField(int coordinateX, int coordinateY){
        return field[coordinateX][coordinateY];
    }
    private void contentSelectionAndSetOnField(int xCoordinate, int yCoordinate){
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        if (randomNumber==0){
            Enemy enemy = new Enemy();
            enemy.setLocation(xCoordinate,yCoordinate);
            setLocationOnField(enemy);
        }if (randomNumber==1){
            Coin coin = new Coin();
            coin.setLocation(xCoordinate,yCoordinate);
            setLocationOnField(coin);
        }if (randomNumber==2){
            Weapon weapon = new Weapon();
            weapon.setLocation(xCoordinate,yCoordinate);
            setLocationOnField(weapon);
        }
    }
    private void setNewField(){
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++){
                contentSelectionAndSetOnField(i,j);
                // центральный инициализируем отдельно, тк нельзя иначе обращаться к главвному герою;
            }
        }
    }


    public void newGame(){
        int generalCoins = 0;//хочу тут сохранить монетки с цикла

        GameWindow window = new GameWindow();

        window.setVisible(true);
        Knight knight = new Knight();
        setLocationOnField(knight);
        setNewField();
        //нереально навалить перемещения клеток
        while (!(knight.getHealth() == 0)){
            int previousXCoordinate = knight.getLocation().x;
            int previousYCoordinate = knight.getLocation().y;
            window.updateKnightLocation(knight);
            //это игровой цикл. в нем надо гонять по кругу
            int newXCoordinate = knight.getLocation().x;
            int newYCoordinate = knight.getLocation().y;
            CellContents content = whatMonsterOnField(newXCoordinate, newYCoordinate);
            if (content instanceof Enemy) {
                knight.attackEnemy(content);
            } if (content instanceof Coin) {
                int coins = content.getCoins();
                generalCoins = generalCoins + coins;
            } if (content instanceof Weapon) {
                generalCoins = generalCoins + knight.getWeapon();
                knight.setWeapon(content.getHealth());
            }
            setLocationOnField(knight);

            //window.repaint();
        }
    }
}
