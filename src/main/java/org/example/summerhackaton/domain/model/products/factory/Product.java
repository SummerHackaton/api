package org.example.summerhackaton.domain.model.products.factory;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
public abstract class Product {
    @Id
    private String id;
    private String name;
    private String type;

    protected Product () {
        super();
    }
}
