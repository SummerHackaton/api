package org.example.summerhackaton.domain.model.products.factory.DrinkProduct;

import org.example.summerhackaton.domain.model.products.factory.Product;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class DrinkProduct extends Product {

}
