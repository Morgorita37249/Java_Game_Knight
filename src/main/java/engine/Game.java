package engine;
import content.*;
import graphics.*;
public class Game {
    //движок для передвижения рыцаря по полю и действий с
    private final int sizeX = 3;
    private final int sizeY = 3;
    //само поле игры
    private int [][] field = new int[sizeX][sizeY];

    public void newGame(){
        GameWindow window = new GameWindow();
        window.setVisible(true);
        Knight knight = new Knight();
        Coin coins = new Coin();//хочу тут сохранить монетки с цикла
        //for (int i=0; i<sizeX;i++){
            //for (int j=0; j<sizeY;j++){
                //  TODO:нарандомить поле
            //}
        //}


        while (!(knight.getHealth() == 0)){
            window.updateCellContentLocation(knight);
            window.repaint();
        }
    }
}
