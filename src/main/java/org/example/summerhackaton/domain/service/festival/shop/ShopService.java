package org.example.summerhackaton.domain.service.festival.shop;

import org.example.summerhackaton.domain.model.festival.shop.Shop;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    private static final String SHOPS_COLLECTION = "shops";

    private final MongoTemplate mongoTemplate;

    public ShopService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Shop createShop(Shop shop) {
        return mongoTemplate.save(shop, SHOPS_COLLECTION);
    }

    public Optional<Shop> getShopById(String id) {
        Shop shop = mongoTemplate.findById(id, Shop.class, SHOPS_COLLECTION);
        return Optional.ofNullable(shop);
    }

    public List<Shop> getAllShops() {
        return mongoTemplate.findAll(Shop.class, SHOPS_COLLECTION);
    }

    public boolean deleteShopById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.remove(query, Shop.class, SHOPS_COLLECTION).wasAcknowledged();
    }
}
