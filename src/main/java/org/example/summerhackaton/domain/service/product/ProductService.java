package org.example.summerhackaton.domain.service.product;

import org.example.summerhackaton.domain.model.products.factory.Product;
import org.example.summerhackaton.domain.model.products.factory.ProductFactory;
import org.example.summerhackaton.domain.model.products.factory.ProductFactoryRegistry;
import org.example.summerhackaton.domain.model.user.UserEntity;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.example.summerhackaton.domain.model.user.UserEntity;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class ProductService {
    private final MongoTemplate mongoTemplate;

    public ProductService( MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Product> cleanCart(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        Update update = new Update().set("boughtProducts", new ArrayList<Product>());
        UserEntity user = mongoTemplate.findAndModify(
                query,
                update,
                new FindAndModifyOptions().returnNew(false),
                UserEntity.class
        );
        return user != null ? user.getBoughtProducts() : new ArrayList<>();
    }
}
