package objects;

import userinterface.GameWindow;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class BruceLeeFactory implements IObjectFactory {

    private IObject clouds, land, enemyManager, powerUpManager;
    private IObject mainCharacter;
    private List<IObject> objects = new ArrayList<>();

    public BruceLeeFactory() {
         this.mainCharacter = (IObject) new CharacterBruceLee();
         this.clouds = new Clouds(GameWindow.SCREEN_WIDTH, (CharacterDecorator)
         mainCharacter);
         this.land = new Land(GameWindow.SCREEN_WIDTH, (CharacterDecorator)
         mainCharacter);
         this.enemyManager= new EnemiesManager((CharacterDecorator)
         this.mainCharacter);
         this.objects.add(clouds);
         this.objects.add(land);
         this.objects.add(enemyManager);
         this.objects.add(mainCharacter);
    }

    @Override
    public IObject getClouds() {
        return null;
    }

    @Override
    public IObject getLand() {
        return null;
    }

    @Override
    public CharacterDecorator getCharacterDecorator() {
        return null;
    }

    @Override
    public EnemiesManager getEnemyManager() {
        return null;
    }

    @Override
    public PowerUpsManager getPowerUpManager() {
        return null;
    }

    @Override
    public List<IObject> getObjects() {
        return null;
    }

//    @Override
//    public void initialise(int character) {
//
//    }
}
