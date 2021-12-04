package objects;

import java.util.List;

public interface IObjectFactory {

    IObject getClouds();

    IObject getLand();

    CharacterDecorator getCharacterDecorator();

    IEnemyManager getEnemyManager();

    PowerUpsManager getPowerUpManager();

    List<IObject> getObjects();

//    void initialise(int character);
}
