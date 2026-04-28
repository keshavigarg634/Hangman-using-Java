import javax.sound.sampled.*;
import java.io.File;

public class SoundPlayer {

    public static void play(String file) {
        try {
            File soundFile = new File(file);

            if (!soundFile.exists()) {
                System.out.println("Sound file not found!");
                return;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

        } catch (Exception e) {
            System.out.println("Sound error");
        }
    }
}