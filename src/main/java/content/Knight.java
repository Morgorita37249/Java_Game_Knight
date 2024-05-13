package content;

import engine.Location;

public class Knight extends CellContents {

    static String description = "Описание игрока. Рыцарь";
    private int weapon;//сюда должно класться здоровье оружия

    public Knight(){
        super.setHealth(30);
        super.setLocation(1,1);
    }

    //снимает очки здоровья врагу,
    public void attackEnemy(CellContents enemy) {
        if (weapon != 0) {
            enemy.setHealth(enemy.getHealth() - weapon);
        } else {
            setHealth(health - weapon);
        }
    }

    public void setWeapon(int newWeapon){weapon = newWeapon;}
    public int getWeapon(){return weapon;}
}
