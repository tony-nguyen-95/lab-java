package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Fruit.Fruit;

public class MapIdFruitData {
    private static MapIdFruitData instance;
    private Map<Integer, Fruit> idFruitMap;

    private MapIdFruitData() {
        this.idFruitMap = new HashMap<>();
        setIdFruitMap(this.idFruitMap);
    }

    public static MapIdFruitData getInstance() {
        if (instance == null) {
            instance = new MapIdFruitData();
        }
        return instance;
    }

    private void setIdFruitMap(Map<Integer, Fruit> idFruitMap) {
        ArrayList<Fruit> rootListFruits = new ArrayList<>();

        rootListFruits.add(new Fruit(1, "Ổi", 1, "Việt Nam", 10));
        rootListFruits.add(new Fruit(2, "Cherry", 1, "Mỹ", 8));
        rootListFruits.add(new Fruit(3, "Sầu Riêng", 1, "Thái Lan", 9));

        for (int i = 0; i < rootListFruits.size(); i++) {
            Fruit fruit = rootListFruits.get(i);
            idFruitMap.put(fruit.getID(), fruit);
        }
    }

    public Map<Integer, Fruit> getRootIdFruitMap() {
        return idFruitMap;
    }

}
