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
        if (this.weapon.getWeaponHealth()!=0){
            enemy.setEnemyHealth(enemy.getEnemyHealth() - this.weapon.getWeaponHealth());
        }
    }



    public int getPlayerHeath(){
        return health;
    }
    public int getPlayerCoins(){
        return coins;
    }
    public Weapon getPlayerWeapon(){
        return weapon;
    }

    public void setPlayerHeath(int newHealth){
        health =  newHealth;
    }
    public void setPlayerCoins(int newCoins){
        coins = newCoins;
    }
    public void setPlayerWeapon(Weapon newWeapon){
        weapon = newWeapon;
    }
}
