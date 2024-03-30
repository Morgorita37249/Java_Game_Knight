package content;
import engine.*;
public interface CellContents {
    int health = 0;
    static String description = "Пустая клетка";
    Location location = new Location(0,0);
    default int getHealth(){
        return health;
    }
    default void setLocation(int x, int y){}
    default Location getLocation(){
        return location;
    }
}
