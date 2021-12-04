package objects;

import java.util.ArrayList;
import java.util.List;

import userinterface.GameWindow;

public class MarioObjectFactory implements IObjectFactory {
    private IObject clouds, land, enemyManager, powerUpManager;
    private IObject mainCharacter;
    private List<IObject> objects = new ArrayList<>();

    public MarioObjectFactory() {
        this.mainCharacter = new CharacterDecorator(new CharacterMario());

        this.clouds = new Clouds(GameWindow.SCREEN_WIDTH, (CharacterDecorator) mainCharacter);
        this.land = new Land(GameWindow.SCREEN_WIDTH, (CharacterDecorator) mainCharacter);
        this.enemyManager = new BulletEnemyManager((CharacterDecorator) this.mainCharacter);
        this.powerUpManager = new PowerUpsManager((CharacterDecorator) this.mainCharacter);
        this.objects.add(clouds);
        this.objects.add(land);
        this.objects.add(enemyManager);
        this.objects.add(mainCharacter);
        this.objects.add(powerUpManager);
    }

}
