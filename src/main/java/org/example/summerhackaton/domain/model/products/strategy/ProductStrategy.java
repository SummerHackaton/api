package org.example.summerhackaton.domain.model.products.strategy;

import java.util.Map;

public interface ProductStrategy {
    ProductType getSupportedType();
    Product createProduct(Product baseProduct, ProductAttributes attributes);
    ProductAttributes convertToAttributes(Map<String, Object> attributes);
    void validateAttributes(ProductAttributes attributes);
}