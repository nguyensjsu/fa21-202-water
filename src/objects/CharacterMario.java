package objects;

import util.Animation;
import util.Resource;

import java.awt.image.BufferedImage;

public class CharacterMario extends Character {

    private Animation normalRunAnim;
    private BufferedImage jumping;
    private Animation downRunAnim;
    private BufferedImage deathImage;

    public Animation getNormalRunAnimation(){
        normalRunAnim = new Animation(90);
        normalRunAnim.addFrame(Resource.getResouceImage("data/Mario/mario-1.png"));
        normalRunAnim.addFrame(Resource.getResouceImage("data/Mario/mario-2.png"));
        normalRunAnim.addFrame(Resource.getResouceImage("data/Mario/mario-3.png"));
        return normalRunAnim;
    }
    public BufferedImage getJumpingImage(){
        jumping = Resource.getResouceImage("data/Mario/mario-3.png");
        return jumping;
    }

    public Animation getDownRunAnimation(){
        downRunAnim = new Animation(90);
        downRunAnim.addFrame(Resource.getResouceImage("data/Mario/mario-2.png"));
        downRunAnim.addFrame(Resource.getResouceImage("data/Mario/mario-3.png"));
        return downRunAnim;
    }

    public BufferedImage getDeathImage(){
        deathImage = Resource.getResouceImage("data/Mario/mario-1.png");
        return deathImage;
    }
}
