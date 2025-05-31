package org.example.summerhackaton.domain.service.user;

import org.example.summerhackaton.domain.model.user.QRCode;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
     void retrieveProducts(QRCode qrCode);
}
