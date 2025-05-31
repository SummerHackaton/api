package org.example.summerhackaton.domain.model.products.factory;

import org.example.summerhackaton.domain.model.products.factory.clothes.ClothingProductFactory;
import org.example.summerhackaton.domain.model.products.factory.food.FoodProductFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductFactoryRegistry {
    private final Map<String, ProductFactory> factoryMap = new HashMap<>() {
        {
            put("clothing", new ClothingProductFactory());
            put("food", new FoodProductFactory());
        }
    };

    public ProductFactory getFactory(String type) {
        return factoryMap.get(type);
    }
}
