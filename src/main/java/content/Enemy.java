package content;

import engine.Location;

public class Enemy implements CellContents{
    static int health =  1 + (int) ( Math.random() * 10 );
    static String description = "Враг, с какими-либо свойствами";//todo: Разные свойства для каждого врага
    private Location location = new Location(0,0);


    public int getEnemyHealth(){
        return health;
    }
    public void setEnemyHealth(int newHealth){
        this.health = newHealth;
    }

}

