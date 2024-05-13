package content;
import engine.*;
public abstract class CellContents {
    protected int health = 1 + (int) ( Math.random() * 10 );;
    public static String description = "Пустая клетка";
    public Location location = new Location(0,0);
    private static int coins = 0;


    public int getHealth(){
        return health;
    }
    public void setHealth(int newHealth){
        health = newHealth;
    }
    public void setLocation(int x, int y){
        location.x = x;
        location.y = y;
    }
    public Location getLocation(){
        return location;
    }
    public void setCoins(int newCoins){
        coins = newCoins;
    }
    public int getCoins(){
        return coins;
    }

}
