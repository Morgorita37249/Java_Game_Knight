package engine;

public class Location{
    public int x = 0;
    public int y = 0;

    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }
    //вот эту функцию надо использовать, чтобы разместить содержимое клеток вокруг рыцаря
    public void setLocation(Location location, int x, int y){
        location.x = x;
        location.y = y;
    }
}
