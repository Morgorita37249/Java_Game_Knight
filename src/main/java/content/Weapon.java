package content;

import engine.Location;

//класс оружие, его рыцарь собирает из соседней клетки. У оружия есть очки здоровья, которые рыцарь тратит на врагов
public class Weapon implements CellContents{
    //у рыцаря пусть по дефолту всегда есть оружие. Но если здоровье оружия достигает нуля - то его считай нет
    private static final int health = 1 + (int) ( Math.random() * 10 );
    //сделать каждое описание под описание конкретного оружия
    static String description = "Описание оружия";//todo: разные свойства для разных оружий
    private Location location = new Location(0,0);





    public int getWeaponHealth(){
        return health;
    }
}
