package graphics;

import javax.sound.sampled.*;
import java.io.InputStream;

public class SoundPlayer {

    public static void playSound(String soundFilePath, double volume) {
        try {
            InputStream audioInputStream = SoundPlayer.class.getResourceAsStream(soundFilePath);
            if (audioInputStream == null) {
                System.err.println("Error: Sound file not found - " + soundFilePath);
                return;
            }
            AudioInputStream stream = AudioSystem.getAudioInputStream(audioInputStream);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);

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
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }
}
