package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomFactoryFactory implements IObjectFactoryFactory {

    List<IObjectFactory> factoryList = new ArrayList<>();

    public RandomFactoryFactory() {
        factoryList.add(new DinoObjectFactory());
        factoryList.add(new MarioObjectFactory());
    }

    @Override
    public IObjectFactory getRandomFactory() {
        int index = new Random().nextInt(factoryList.size());
        return factoryList.get(index);
    }
}
