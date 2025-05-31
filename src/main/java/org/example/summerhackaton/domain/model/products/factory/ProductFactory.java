package org.example.summerhackaton.domain.model.products.factory;

import java.util.Map;

public interface ProductFactory {
    Product createProduct(Map<String, Object> attributes);
    String getType();
}
