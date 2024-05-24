package graphics;

import javax.sound.sampled.*;
import java.io.InputStream;

public class SoundPlayer {
    private Clip clip;

    public static void playSound(String soundFilePath, double volume) {
        try {
            InputStream audioInputStream = SoundPlayer.class.getResourceAsStream(soundFilePath);
            assert audioInputStream != null;
            AudioInputStream stream = AudioSystem.getAudioInputStream(audioInputStream);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);

            // Получаем управление громкостью и устанавливаем указанное значение
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue((float) (20 * Math.log10(volume)));

            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.setFramePosition(0);
                        clip.start();
                    }
                }
            });

            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}