package engine;

public class Location{
    public int x = 0;
    public int y = 0;

    public Location(int x, int y){
        this.x = check(x);
        this.y = check(y);
    }
    public int check(int locationValue){
        if (locationValue<0 || locationValue>2){
            // придумать маленькую реакцию на попытку выйти за границы поля
        }
        return locationValue;
    }
}
