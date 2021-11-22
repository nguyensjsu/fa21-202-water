package objects;

import java.util.List;

public interface IObjectFactory {

    IObject getClouds();

    IObject getLand();

    MainCharacter getMainCharacter();

    EnemiesManager getEnemyManager();

    List<IObject> getObjects();

}
