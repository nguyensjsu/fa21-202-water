package objects;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MainCharacter implements IObject {

    private AudioClip jumpSound;
    private AudioClip deadSound;
    private AudioClip scoreUpSound;
    private Clip clip;

    public MainCharacter() {
        File themeSoundFile = new File("data/theme.wav");
        try {
            AudioInputStream audioInputStream;
            audioInputStream = AudioSystem.getAudioInputStream(themeSoundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            jumpSound = Applet.newAudioClip(new URL("file", "", "data/jump.wav"));
            deadSound = Applet.newAudioClip(new URL("file", "", "data/dead.wav"));
            scoreUpSound = Applet.newAudioClip(new URL("file", "", "data/scoreup.wav"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playThemeSound() {
        if (!clip.isActive()) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    public void playDeadSound() {
        clip.stop();
        deadSound.play();
    }

}
