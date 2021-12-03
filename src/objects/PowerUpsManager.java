package objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.Resource;

public class PowerUpsManager implements IObject {

    private BufferedImage mushroom;
    private Random rand;

    private List<PowerUp> powerups;
    private CharacterDecorator mainCharacter;

    public PowerUpsManager(CharacterDecorator mainCharacter) {
        rand = new Random();
        mushroom = Resource.getResouceImage("data/mushroom.png");
        powerups = new ArrayList<PowerUp>();
        this.mainCharacter = mainCharacter;
        powerups.add(createPowerUp());
    }

    public void update() {
        for(PowerUp p : powerups) {
            p.update();
        }
        PowerUp powerup = powerups.get(0);
        if(powerup.isOutOfScreen()) {
            mainCharacter.upScore();
            powerups.clear();
            powerups.add(createPowerUp());
        }
    }

    public void draw(Graphics g) {
        for(PowerUp p : powerups) {
            p.draw(g);
        }
    }

    private PowerUp createPowerUp() {
        // if (enemyType = getRandom)
//        int type = rand.nextInt(2);
//        if(type == 0) {
//            return new Cactus(mainCharacter, 800, cactus1.getWidth() - 10, cactus1.getHeight() - 10, cactus1);
//        } else {
//            return new Cactus(mainCharacter, 800, cactus2.getWidth() - 10, cactus2.getHeight() - 10, cactus2);
//        }
        return new Mushroom(mainCharacter, 2600, mushroom.getWidth() - 20, mushroom.getHeight() - 10, mushroom);
    }

    public boolean isCollision() {
        for(PowerUp p : powerups) {
            if (mainCharacter.getBound().intersects(p.getBound())) {
                return true;
            }
        }
        return false;
    }

    public void reset() {
        powerups.clear();
        powerups.add(createPowerUp());
    }

}
