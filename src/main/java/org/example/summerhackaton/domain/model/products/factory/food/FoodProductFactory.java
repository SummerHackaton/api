package org.example.summerhackaton.domain.model.products.factory.food;

import org.example.summerhackaton.domain.model.products.factory.Product;
import org.example.summerhackaton.domain.model.products.factory.ProductFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Component
public class FoodProductFactory implements ProductFactory {
    @Override
    public Product createProduct(Map<String, Object> attributes) {
        FoodProduct food = new FoodProduct();
        food.setName((String) attributes.get("name"));
        food.setType("food");
        food.setExpirationDate(LocalDate.parse((String)attributes.get("expirationDate")));
        food.setPerishable(Boolean.parseBoolean(attributes.get("isPerishable").toString()));
        return food;
    }

    @Override
    public String getType() {
        return "food";
    }
}

