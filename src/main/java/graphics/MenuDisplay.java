package graphics;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class MenuDisplay {
    private int currentImageIndex = 0;
    private final Image[] menuImages = new Image[]{
            Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/menu1.png")),
            Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/menu2.png")),
            Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/images/menu3.png"))
    };

    void startMenuImageTimer(GameWindow g) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (KeyHandler.getIsKeyPressed()) {
                    timer.cancel();
                } else {
                    currentImageIndex = (currentImageIndex + 1) % menuImages.length;
                    g.repaint();
                }
            }
        }, 0, 500);
    }
    public Image getMenuImage(int index){
        return menuImages[index];
    }
    public int getCurrentImageIndex(){
        return currentImageIndex;
    }
}