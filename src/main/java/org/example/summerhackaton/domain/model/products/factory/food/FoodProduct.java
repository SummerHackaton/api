package org.example.summerhackaton.domain.model.products.factory.food;

import org.example.summerhackaton.domain.model.products.factory.Product;

import java.time.LocalDate;

public class FoodProduct extends Product {
    private LocalDate expirationDate;
    private boolean isPerishable;

    public FoodProduct () {
        super();
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isPerishable() {
        return isPerishable;
    }

    public void setPerishable(boolean perishable) {
        isPerishable = perishable;
    }
}