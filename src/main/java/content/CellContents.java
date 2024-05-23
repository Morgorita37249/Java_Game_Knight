package content;
import engine.Location;
public abstract class CellContents {
    protected int health = 1 + (int) ( Math.random() * 10 );;
    public static String description = "Содержимое клетки";
    public Location location = new Location(0,0);


    public int getHealth(){
        return health;
    }
    public void setHealth(int newHealth){
        health = newHealth;
    }
    public void setLocation(int x, int y){
        location.SetLocationX(x);
        location.SetLocationY(y);
    }
    public Location getLocation(){
        return location;
    }


}
