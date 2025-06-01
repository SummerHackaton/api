package org.example.summerhackaton.domain.service.user;

import org.example.summerhackaton.domain.model.products.factory.Product;
import org.example.summerhackaton.domain.model.user.QRCode;

import java.util.List;

public interface PaymentService {
     void retrieveProducts(QRCode qrCode);

    void payForProducts(List<Product> products, String userId);
}
