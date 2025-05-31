package org.example.summerhackaton.domain.service.user;

import org.example.summerhackaton.domain.model.products.factory.Product;
import org.example.summerhackaton.domain.model.user.QRCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
     void retrieveProducts(QRCode qrCode);

    void payForProducts(List<Product> products, String userId);
}
