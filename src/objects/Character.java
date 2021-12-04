package objects;

import util.Animation;
import util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Character implements ICharacter{

    public float getSpeedX() {
        return 0;

    }

    public void setSpeedX(int speedX) {

    }

    public void draw(Graphics g) {

    }

    public void update() {

    }

    public void jump() {

    }

    public void down(boolean isDown) {

    }

    public Rectangle getBound() {
        return null;
    }

    public void dead(boolean isDeath) {

    }

    public void reset() {

    }

    public void playDeadSound() {

    }

    public void upScore() {

    }
    public void resetScore(){

    }

    public void resetBonus(){

    }

    public void DecreaseBonus(){

    }

    public void IncreaseBonus(){

    }

    public Animation getNormalRunAnimation(){
        return null;
    }
    public BufferedImage getJumpingImage(){
        return null;
    }

    public Animation getDownRunAnimation(){
        return null;
    }

    public BufferedImage getDeathImage(){
        return null;
    }
}
