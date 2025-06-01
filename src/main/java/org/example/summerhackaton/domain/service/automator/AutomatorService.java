package org.example.summerhackaton.domain.service.automator;


import org.example.summerhackaton.domain.model.products.factory.Product;

import java.util.List;

public interface AutomatorService {
    Boolean canjeadorDemo(List<Product> cart);
}
