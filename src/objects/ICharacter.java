package objects;

import java.awt.*;

public interface ICharacter {

    public float getSpeedX();

    public void setSpeedX(int speedX);

    public void draw(Graphics g);

    public void update();

    public void jump();

    public void down(boolean isDown);

    public Rectangle getBound();

    public void dead(boolean isDeath);

    public void reset();

    public void playDeadSound();

    public void upScore();

    public void resetScore();

    public void resetBonus();

    public void DecreaseBonus();

    public void IncreaseBonus();
}