package org.example.summerhackaton.domain.model.products.factory.clothes;

import org.example.summerhackaton.domain.model.products.factory.Product;
import org.example.summerhackaton.domain.model.products.factory.ProductFactory;
import org.example.summerhackaton.domain.model.products.factory.ProductType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Document(collection = "products")
public class ClothingProductFactory implements ProductFactory {
    @Override
    public Product createProduct(Map<String, Object> attributes) {
        ClothingProduct clothing = new ClothingProduct();
        clothing.setName((String) attributes.get("name"));
        clothing.setType(ProductType.CLOTHING);
        clothing.setSize((String) attributes.get("size"));
        clothing.setMaterial((String) attributes.get("material"));
        return clothing;
    }

    @Override
    public String getType() {
        return "clothing";
    }
}
