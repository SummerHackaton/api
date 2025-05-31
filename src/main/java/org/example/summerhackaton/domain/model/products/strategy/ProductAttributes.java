package org.example.summerhackaton.domain.model.products.strategy;

import java.util.Map;

public interface ProductAttributes {
    ProductType getType();
    Map<String, Object> getSpecificAttributes();
    void validate() throws IllegalArgumentException;
}
