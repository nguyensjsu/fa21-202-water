package objects;

import java.util.ArrayList;
import java.util.List;

import userinterface.GameWindow;
import util.Settings;

import static util.Settings.Character.*;

public class ClassicObjectFactory implements IObjectFactory {

    private IObject clouds, land, enemyManager, powerUpManager;
    private IObject mainCharacter;
    private List<IObject> objects = new ArrayList<>();
    private Settings settings;

    public ClassicObjectFactory() {
        settings = Settings.getInstance();
        Settings.Character character = settings.getCharacter();

        if (character == CHARACTER_BRUCE_LEE) {
            this.mainCharacter = new CharacterDecorator(new CharacterBruceLee());
        } else if (character == CHARACTER_DINOSAUR) {
            this.mainCharacter = new CharacterDecorator(new CharacterDinosaur());
        } else if (character == CHARACTER_MARIO) {
            this.mainCharacter = new CharacterDecorator(new CharacterMario());
        }

        this.clouds = new Clouds(GameWindow.SCREEN_WIDTH, (CharacterDecorator) mainCharacter);
        this.land = new Land(GameWindow.SCREEN_WIDTH, (CharacterDecorator) mainCharacter);
        this.enemyManager= new EnemiesManager((CharacterDecorator) this.mainCharacter);
        this.powerUpManager = new PowerUpsManager((CharacterDecorator) this.mainCharacter);
        this.objects.add(clouds);
        this.objects.add(land);
        this.objects.add(enemyManager);
        this.objects.add(mainCharacter);
        this.objects.add(powerUpManager);
    }

    @Override
    public IObject getClouds() {
        return clouds;
    }

    @Override
    public IObject getLand() {
        return land;
    }

    @Override
    public CharacterDecorator getCharacterDecorator() {
        return (CharacterDecorator) mainCharacter;
    }

    @Override
    public IEnemyManager getEnemyManager() {
        return (IEnemyManager) enemyManager;
    }

    @Override
    public PowerUpsManager getPowerUpManager() {
        return (PowerUpsManager) powerUpManager;
    }

    @Override
    public List<IObject> getObjects() {
        return objects;
    }
}
