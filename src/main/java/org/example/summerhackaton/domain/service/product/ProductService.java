package org.example.summerhackaton.domain.service.product;

import org.example.summerhackaton.domain.model.products.factory.Product;
import org.example.summerhackaton.domain.model.products.factory.ProductFactory;
import org.example.summerhackaton.domain.model.products.factory.ProductFactoryRegistry;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductService {
    private final ProductFactoryRegistry registry;
    private final MongoTemplate mongoTemplate;

    public ProductService(ProductFactoryRegistry registry, MongoTemplate mongoTemplate) {
        this.registry = registry;
        this.mongoTemplate = mongoTemplate;
    }

    public Product createProduct(String type, Map<String, Object> attributes) {
        ProductFactory factory = registry.getFactory(type);
        if (factory == null) {
            throw new IllegalArgumentException("Unknown product type: " + type);
        }
        Product product = factory.createProduct(attributes);
        return mongoTemplate.save(product);
    }
}

