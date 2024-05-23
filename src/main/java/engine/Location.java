package engine;

public class Location{
    private int x;
    private int y;

    public Location(int x, int y){
        SetLocationX(x);
        SetLocationY(y);
    }
    public int GetLocationX(){
        return x;
    }
    public int GetLocationY(){
        return y;
    }
    public void SetLocationX(int newX){
        x = newX;
    }
    public void SetLocationY(int newY){
        y = newY;
    }
}
