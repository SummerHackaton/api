package org.example.summerhackaton.domain.service.automator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.summerhackaton.domain.model.products.factory.Product;
import org.example.summerhackaton.domain.model.user.QRCode;
import org.example.summerhackaton.ui.user.PaymentController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Random;

@Service
public class AutomatorServiceImpl implements AutomatorService{
    //DESDE EL SERVICE NO SE DEBE LLAMAR A OTRO CONTROLLER, PERO PARA ESTE EJEMPLO LO HAREMOS XD
    private final PaymentController paymentController;
    private final Random random;

    public AutomatorServiceImpl(PaymentController paymentController) {
        this.paymentController = paymentController;
        random = new Random();
    }

    @Override
    public Boolean canjeadorDemo(List<Product> cart) {
        try {
            // time sleep random between 1 and 5 seconds
            int sleepTime = random.nextInt(5000) + 1000; // between 1000ms and 6000ms
            Thread.sleep(sleepTime);
            paymentController.retrieveProducts(
                                QRCode.builder()
                                    .productsToRetrieve(cart)
                                    .userId(SecurityContextHolder.getContext().getAuthentication().getName())
                                .build());
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
        return false;
    }

    @Override
    public List<Product> parseCart(String bulkCart) {
        //esta la lista en base 64, viene del frontend
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(bulkCart);
            String json = new String(decodedBytes);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, new TypeReference<List<Product>>() {});
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al parsear el carrito", e);
        }
    }
}
