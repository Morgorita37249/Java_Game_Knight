package content;

public class Knight extends CellContents {

    static String description = "Описание игрока. Рыцарь";
    private final int maxHealth = 30;
    private int weapon;//сюда должно класться здоровье оружия
    private int wallet = 0;

    public Knight(){
        super.setHealth(30);
        super.setLocation(1,1);
    }

    public int getBaseHealth(){
        return maxHealth;
    }
    public void setWeapon(int newWeapon){weapon = newWeapon;}
    public int getWeapon(){return weapon;}
    public void setWallet(int coins){wallet = wallet + coins;}
    public int getWallet(){return wallet;}
}
