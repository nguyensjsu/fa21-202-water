package util;

import objects.IObjectFactory;
import objects.IObjectFactoryFactory;
import objects.RandomFactoryFactory;

public class Settings {

    public enum Character {
        CHARACTER_BRUCE_LEE, CHARACTER_DINOSAUR, CHARACTER_MARIO, CHARACTER_RANDOM
    };

    private final IObjectFactoryFactory factorySelector;
    private Character character;

    private static Settings instance;

    private Settings() {
        this.factorySelector = new RandomFactoryFactory();
        this.character = Character.CHARACTER_DINOSAUR;
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public IObjectFactory getNewFactory() {
        return factorySelector.getRandomFactory();
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return this.character;
    }

}
