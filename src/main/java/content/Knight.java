package content;

import engine.Location;

public class Knight implements CellContents{
    private static int health = 30;
    private static int coins = 0;
    static String description = "Описание игрока. Рыцарь";
    private Weapon weapon = new Weapon();
    //расположение на поле
    private Location location = new Location(1,1);



    //снимает очки здоровья врагу,
    public void attackEnemy(Enemy enemy){
        if (this.weapon.getHealth()!=0){
            enemy.setHealth(enemy.getHealth() - this.weapon.getHealth());
        }
    }




    public int getHealth(){
        return health;
    }
    public int getCoins(){
        return coins;
    }
    public Weapon getWeapon(){
        return weapon;
    }

    public void setHealth(int newHealth){
        health =  newHealth;
    }
    public void setCoins(int newCoins){
        coins = newCoins;
    }
    public void setWeapon(Weapon newWeapon){
        weapon = newWeapon;
    }
    public void setLocation(int x, int y){location = new Location(x,y);}
    public Location getLocation(){return location;}
}
