package org.example.summerhackaton.domain.model.products.factory.food;

import lombok.Getter;
import lombok.Setter;
import org.example.summerhackaton.domain.model.products.factory.Product;

import java.time.LocalDate;

@Getter
@Setter
public class FoodProduct extends Product {
    private LocalDate expirationDate;
    private boolean isPerishable;

    public FoodProduct () {
        super();
    }
}