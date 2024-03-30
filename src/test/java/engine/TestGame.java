package engine;
import content.Knight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestGame {
    @Test
    public void testMove() {
        Game game = new Game();
        Knight knight = new Knight();
        //базовое размещение рыцаря:(1,1)
        game.move(knight, Game.Move.LEFT);
        assertEquals(knight.getLocation().x,0);
        assertEquals(knight.getLocation().y,1);

        game.move(knight, Game.Move.RIGHT);
        assertEquals(knight.getLocation().x,1);
        assertEquals(knight.getLocation().y,1);

        game.move(knight, Game.Move.DOWN);
        assertEquals(knight.getLocation().x,1);
        assertEquals(knight.getLocation().y,0);

        game.move(knight, Game.Move.UP);
        assertEquals(knight.getLocation().x,1);
        assertEquals(knight.getLocation().y,1);
    }
    @Test
    public void testGame(){
        
    }
}