package objects;

import userinterface.GameWindow;

import java.util.ArrayList;
import java.util.List;

public class ClassicObjectFactory implements IObjectFactory {

    private IObject clouds, land, enemyManager, powerUpManager;
    private IObject mainCharacter;
    private List<IObject> objects = new ArrayList<>();

    private final int CHARACTER_BRUCE_LEE = 1;
    private final int CHARACTER_DINOSAUR = 2;
    private final int CHARACTER_MARIO = 3;

    public ClassicObjectFactory() {
//        this.mainCharacter = new CharacterDecorator();
//        this.clouds = new Clouds(GameWindow.SCREEN_WIDTH, (CharacterDecorator) mainCharacter);
//        this.land = new Land(GameWindow.SCREEN_WIDTH, (CharacterDecorator) mainCharacter);
//        this.enemyManager= new EnemiesManager((CharacterDecorator) this.mainCharacter);
//        this.objects.add(clouds);
//        this.objects.add(land);
//        this.objects.add(enemyManager);
//        this.objects.add(mainCharacter);

    }

    public void initialise(int character){
        if(character == CHARACTER_BRUCE_LEE){
            this.mainCharacter = new CharacterDecorator(new CharacterBruceLee());
        } else if(character == CHARACTER_DINOSAUR){
            this.mainCharacter = new CharacterDecorator(new CharacterDinosaur());
        }else if(character == CHARACTER_MARIO) {
            this.mainCharacter = new CharacterDecorator(new CharacterMario());
        }


        this.clouds = new Clouds(GameWindow.SCREEN_WIDTH, (CharacterDecorator) mainCharacter);
        this.land = new Land(GameWindow.SCREEN_WIDTH, (CharacterDecorator) mainCharacter);
        this.enemyManager= new EnemiesManager((CharacterDecorator) this.mainCharacter);
        this.powerUpManager= new PowerUpsManager((CharacterDecorator) this.mainCharacter);
        this.objects.add(clouds);
        this.objects.add(land);
        this.objects.add(enemyManager);
        this.objects.add(powerUpManager);
        this.objects.add(mainCharacter);

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
    public EnemiesManager getEnemyManager() {
        return (EnemiesManager) enemyManager;
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
