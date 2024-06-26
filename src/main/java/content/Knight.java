package content;

public class Knight extends CellContents {

    static String description = "Описание игрока. Рыцарь";
    private final int maxHealth = 30;
    private int weapon;
    private int wallet = 0;
    private int poisonTime = 0;

    public Knight(){
        super.setHealth(30);
        super.setLocation(1,1);
    }


    public int getPoisonTime() {
        return poisonTime;
    }
    public void setPoisonTime(int newTime) {
        poisonTime= newTime;
    }
    public int getBaseHealth(){
        return maxHealth;
    }
    public void setWeapon(int newWeapon){weapon = newWeapon;}
    public int getWeapon(){return weapon;}
    public void setWallet(int coins){wallet = wallet + coins;}
    public int getWallet(){return wallet;}
}
