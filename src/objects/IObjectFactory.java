package objects;

import java.util.List;

public interface IObjectFactory {

    IObject getClouds();

    IObject getLand();

    CharacterDecorator getCharacterDecorator();

    EnemiesManager getEnemyManager();

    PowerUpsManager getPowerUpManager();

    List<IObject> getObjects();

    void initialise(int character);
}
