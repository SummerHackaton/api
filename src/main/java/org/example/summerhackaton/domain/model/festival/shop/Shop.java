package org.example.summerhackaton.domain.model.festival.shop;

import lombok.Getter;
import lombok.Setter;
import org.example.summerhackaton.domain.model.products.factory.Product;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "shops")
@Getter
@Setter
public class Shop {
    @Id
    private String id;
    private String name;
    private String owner;
    private List<Product> stock;

    public Shop () {
        super();
    }
}
