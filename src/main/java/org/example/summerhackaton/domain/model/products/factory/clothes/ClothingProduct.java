package org.example.summerhackaton.domain.model.products.factory.clothes;

import lombok.Getter;
import lombok.Setter;
import org.example.summerhackaton.domain.model.products.factory.Product;

@Getter
@Setter
public class ClothingProduct extends Product {
    private String size;
    private String material;

    public ClothingProduct() {
        super();
    }
}
