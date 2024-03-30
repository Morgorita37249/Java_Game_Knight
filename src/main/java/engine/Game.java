package engine;
import content.*;
public class Game {
    //движок для передвижения рыцаря по полю и действий с
    private final int sizeX = 3;
    private final int sizeY = 3;
    //само поле игры
    private int [][] field = new int[sizeX][sizeY];

    enum Move{
        LEFT,
        RIGHT,
        DOWN,
        UP

    }


    //метод должен перемещать по полю любое создание
    public void move(CellContents cellContent, Move move){
        if (cellContent.getHealth()!=0){
            int newX = cellContent.getLocation().x;
            int newY = cellContent.getLocation().y;
            switch (move){
                case LEFT:
                    newX = newX-1;
                    cellContent.setLocation(newX,newY);
                    break;
                case RIGHT:
                    newX = newX+1;
                    cellContent.setLocation(newX,newY);
                    break;
                case DOWN:
                    newY = newY-1;
                    cellContent.setLocation(newX,newY);
                    break;
                case UP:
                    newY = newY+1;
                    cellContent.setLocation(newX,newY);
                    break;
            }
        }
    }

    public void newGame(){
        Knight knight = new Knight();
        Coin coins = new Coin();//хочу тут сохранить монетки с цикла
        while (!(knight.getHealth() == 0)){

        }
    }

}
