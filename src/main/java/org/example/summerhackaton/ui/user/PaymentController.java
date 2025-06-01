package org.example.summerhackaton.ui.user;


import org.example.summerhackaton.domain.model.products.factory.Product;
import org.example.summerhackaton.domain.model.user.QRCode;
import org.example.summerhackaton.domain.service.user.PaymentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.summerhackaton.common.Constantes.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/payment")
public class PaymentController {
    private final PaymentServiceImpl paymentServiceImpl;

    public PaymentController(PaymentServiceImpl paymentServiceImpl) {
        this.paymentServiceImpl = paymentServiceImpl;
    }

    // This endpoint is user to pay for the products, updating the user balance, and the user owned products list
    @PutMapping("/pay")
    public ResponseEntity<String> payForProducts(@RequestBody List<Product> products,@RequestBody String userId) {
        // Logic to pay for products using QR code
        paymentServiceImpl.payForProducts(products, userId);
        return ResponseEntity.ok("Payment successful");
    }

    // qr scan logic, checks if the user has enough products to retrieve,
    // if so, retrieves the products updating the user product list, not the balance
    @PutMapping("/retrieve/{qrCode}")
    public ResponseEntity<String> retrieveProducts(@PathVariable QRCode qrCode) {
        // Logic to retrieve products after QR scan
        paymentServiceImpl.retrieveProducts(qrCode);
        return ResponseEntity.ok("Products retrieved successfully");
    }
}
