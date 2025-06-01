package org.example.summerhackaton.domain.service.product;

import org.example.summerhackaton.domain.model.products.factory.Product;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class ProductService {
    private final MongoTemplate mongoTemplate;

    public ProductService( MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

}
