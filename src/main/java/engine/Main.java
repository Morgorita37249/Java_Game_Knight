package engine;

import graphics.GraphicWindow;
import java.awt.*;

public class Main {
    public static void main(String [] args)
    {
        Game game = new Game();
        final Frame frame = new GraphicWindow();
        game.newGame();
        frame.setVisible(true);

    }

}
