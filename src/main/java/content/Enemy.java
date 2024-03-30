package content;

import engine.Location;

public class Enemy implements CellContents {
    static int health =  1 + (int) ( Math.random() * 10 );
    static String description = "Враг, с какими-либо свойствами";//todo: Разные свойства для каждого врага
    private Location location = new Location(0,0);
    private Coin enemyCoin = new Coin();

    public int getHealth(){
        return health;
    }
    public void setHealth(int newHealth){
        this.health = newHealth;
    }
    public void setLocation(int x, int y){
        this.location = new Location(x,y);
    }
    public Location getLocation(){
        return location;
    }

}

