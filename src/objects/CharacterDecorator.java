package objects;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import util.Animation;

public class CharacterDecorator extends Character implements IObject, ICharacter {

    Character wrapped;

    public static final int LAND_POSY = 280;
    public static final float GRAVITY = 0.4f;

    private static final int NORMAL_RUN = 0;
    private static final int JUMPING = 1;
    private static final int DOWN_RUN = 2;
    private static final int DEATH = 3;

    private float posY;
    private float posX;
    private float speedX;
    private float speedY;
    private Rectangle rectBound;

    public int score = 0;
    public int health = 100;

    private int state = NORMAL_RUN;

    private Animation normalRunAnim;
    private BufferedImage jumping;
    private Animation downRunAnim;
    private BufferedImage deathImage;

    private AudioClip jumpSound;
    private AudioClip deadSound;
    private AudioClip scoreUpSound;
    private Clip clip;

    public CharacterDecorator(Character wrapped) {
        this.wrapped = wrapped;
        posX = 50;
        posY = LAND_POSY;
        rectBound = new Rectangle();

        // normalRunAnim = new Animation(90);
        // normalRunAnim.addFrame(Resource.getResouceImage("data/bl1.jpeg"));
        // normalRunAnim.addFrame(Resource.getResouceImage("data/bl2.jpeg"));

        normalRunAnim = wrapped.getNormalRunAnimation();

        // jumping = Resource.getResouceImage("data/bl3.jpeg");
        jumping = wrapped.getJumpingImage();

        // downRunAnim = new Animation(90);
        // downRunAnim.addFrame(Resource.getResouceImage("data/main-character5.png"));
        // downRunAnim.addFrame(Resource.getResouceImage("data/main-character6.png"));

        downRunAnim = wrapped.getDownRunAnimation();

        // deathImage = Resource.getResouceImage("data/main-character4.png");
        deathImage = wrapped.getDeathImage();
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
    //
    // static private CharacterDecorator instance;
    //
    // public static CharacterDecorator getInstance() {
    // if (instance == null) {
    // instance = new CharacterDecorator();
    // }
    // return instance;
    // }

    public float getSpeedX() {
        return speedX;
    }

    public void playThemeSound() {
        if (!clip.isActive()) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void draw(Graphics g) {
        switch (state) {
            case NORMAL_RUN:
                g.drawImage(normalRunAnim.getFrame(), (int) posX, (int) posY, null);
                break;
            case JUMPING:
                g.drawImage(jumping, (int) posX, (int) posY, null);
                break;
            case DOWN_RUN:
                g.drawImage(downRunAnim.getFrame(), (int) posX, (int) (posY), null);
                break;
            case DEATH:
                g.drawImage(deathImage, (int) posX, (int) posY, null);
                break;
        }
        // Rectangle bound = getBound();
        // g.setColor(Color.RED);
        // g.drawRect(bound.x, bound.y, bound.width, bound.height);
    }

    public void update() {
        normalRunAnim.updateFrame();
        downRunAnim.updateFrame();
        if (posY >= LAND_POSY) {
            posY = LAND_POSY;
            if (state != DOWN_RUN) {
                state = NORMAL_RUN;
            }
        } else {
            speedY += GRAVITY;
            posY += speedY;
        }
    }

    public void jump() {
        if (posY >= LAND_POSY) {
            if (jumpSound != null) {
                jumpSound.play();
            }
            speedY = -7.5f;
            posY += speedY;
            state = JUMPING;
        }
    }

    public void down(boolean isDown) {
        if (state == JUMPING) {
            return;
        }
        if (isDown) {
            state = DOWN_RUN;
        } else {
            state = NORMAL_RUN;
        }
    }

    public Rectangle getBound() {
        rectBound = new Rectangle();
        if (state == DOWN_RUN) {
            rectBound.x = (int) posX + 5;
            rectBound.y = (int) posY + 20;
            rectBound.width = downRunAnim.getFrame().getWidth() - 10;
            rectBound.height = downRunAnim.getFrame().getHeight();
        } else {
            rectBound.x = (int) posX + 5;
            rectBound.y = (int) posY;
            rectBound.width = normalRunAnim.getFrame().getWidth() - 10;
            rectBound.height = normalRunAnim.getFrame().getHeight();
        }
        return rectBound;
    }

    public void dead(boolean isDeath) {
        if (isDeath) {
            state = DEATH;
        } else {
            state = NORMAL_RUN;
        }
    }

    public void reset() {
        posY = LAND_POSY;
    }

    public void playDeadSound() {
        clip.stop();
        deadSound.play();
    }

    public void upScore() {
        score += 20;
        if (score % 100 == 0) {
            scoreUpSound.play();
        }
    }

    public void resetScore() {
        score = 0;
    }

    public void resetHealth() {
        health = 100;
    }

    public void DecreaseHealth() {
        health -= 1;
    }

    public void IncreaseHealth() {
        health += 1;
    }

}
