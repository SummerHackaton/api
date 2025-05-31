package org.example.summerhackaton.domain.model.products.factory;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "products")
@Data
@Builder
@NoArgsConstructor
public abstract class Product {
    @Id
    private String id;
    private String name;
    private ProductType type;
    private BigDecimal price;
    private int quantity;

}