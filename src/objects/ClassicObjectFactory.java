package objects;

import userinterface.GameWindow;

import java.util.ArrayList;
import java.util.List;

public class ClassicObjectFactory implements IObjectFactory {

    private final IObject clouds, land, mainCharacter, enemyManager;
    private List<IObject> objects = new ArrayList<>();

    public ClassicObjectFactory() {
        this.mainCharacter = new MainCharacter();
        this.clouds = new Clouds(GameWindow.SCREEN_WIDTH, (MainCharacter) mainCharacter);
        this.land = new Land(GameWindow.SCREEN_WIDTH, (MainCharacter) mainCharacter);
        this.enemyManager= new EnemiesManager((MainCharacter) this.mainCharacter);
        this.objects.add(clouds);
        this.objects.add(land);
        this.objects.add(enemyManager);
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
    public MainCharacter getMainCharacter() {
        return (MainCharacter) mainCharacter;
    }

    @Override
    public EnemiesManager getEnemyManager() {
        return (EnemiesManager) enemyManager;
    }

    @Override
    public List<IObject> getObjects() {
        return objects;
    }
}
