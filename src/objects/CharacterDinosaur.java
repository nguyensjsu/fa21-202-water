package objects;

import util.Animation;
import util.Resource;

import java.awt.image.BufferedImage;

public class CharacterDinosaur extends Character{

    private Animation normalRunAnim;
    private BufferedImage jumping;
    private Animation downRunAnim;
    private BufferedImage deathImage;

    public Animation getNormalRunAnimation(){
        normalRunAnim = new Animation(90);
        normalRunAnim.addFrame(Resource.getResouceImage("data/main-character1.png"));
        normalRunAnim.addFrame(Resource.getResouceImage("data/main-character2.png"));
        normalRunAnim.addFrame(Resource.getResouceImage("data/main-character3.png"));
        return normalRunAnim;
    }
    public BufferedImage getJumpingImage(){
        jumping = Resource.getResouceImage("data/main-character3.png");
        return jumping;
    }

    public Animation getDownRunAnimation(){
        downRunAnim = new Animation(90);
        downRunAnim.addFrame(Resource.getResouceImage("data/main-character5.png"));
        downRunAnim.addFrame(Resource.getResouceImage("data/main-character6.png"));
        return downRunAnim;
    }

    public BufferedImage getDeathImage(){
        deathImage = Resource.getResouceImage("data/main-character4.png");
        return deathImage;
    }
}