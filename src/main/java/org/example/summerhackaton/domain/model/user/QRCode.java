package org.example.summerhackaton.domain.model.user;

import lombok.*;
import org.example.summerhackaton.domain.model.products.factory.Product;

import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QRCode {
    // user id whos gonna affect the product retrieval
    private String userId;
    //List of products to retrieve
    List<Product> productsToRetrieve;
}
