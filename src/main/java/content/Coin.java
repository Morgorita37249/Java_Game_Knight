package content;


import engine.Game.*;
import engine.Location;

public class Coin implements CellContents{
    private static final int health = 1 + (int) ( Math.random() * 10 );
    static String description = "Награда для рыцаря";
    private Location location = new Location(0,0);

}
