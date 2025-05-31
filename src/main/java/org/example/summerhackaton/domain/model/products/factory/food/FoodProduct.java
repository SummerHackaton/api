package org.example.summerhackaton.domain.model.products.factory.food;

import lombok.Getter;
import lombok.Setter;
import org.example.summerhackaton.domain.model.products.factory.Product;

@Getter
@Setter
public class FoodProduct extends Product {
    private String expirationDate;
    private boolean isPerishable;

    public FoodProduct () {
        super();
    }
}