package engine;

public class Location{
    public int x;
    public int y;

    public Location(int newx, int newy){
       if (newx>=0 && newx<=2 && newy>=0 && newy<=2) {
           this.x = newx;
           this.y = newy;
       }
    }
    //запрет на выход за пределы поля
}
