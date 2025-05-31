package org.example.summerhackaton.ui.user;


import org.example.summerhackaton.domain.model.user.QRCode;
import org.example.summerhackaton.domain.service.user.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.example.summerhackaton.common.Constantes.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }



    // qr scan logic, checks if the user has enogh products to retrieve,
    // if so, retrieves the products updating the user product list, not the balance
    @PutMapping("/retrieve")
    public ResponseEntity<String> retrieveProducts(QRCode qrCode) {
        // Logic to retrieve products after QR scan
        paymentService.retrieveProducts(qrCode);
        return ResponseEntity.ok("Products retrieved successfully");
    }
}
