package org.example.summerhackaton.domain.model.products.factory.clothes;

import org.example.summerhackaton.domain.model.products.factory.Product;
public class ClothingProduct extends Product {
    private String size;
    private String material;

    public ClothingProduct() {
        super();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
