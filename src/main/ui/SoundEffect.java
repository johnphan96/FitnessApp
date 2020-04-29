package ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundEffect {
    private Clip clip;

    public void setFile(String soundFilePath) {
        try {
            File file = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            System.out.println("Sound effect error in setFile");
        }
    }

    public void playSound() {
        clip.setFramePosition(0);
        clip.start();
    }


}
