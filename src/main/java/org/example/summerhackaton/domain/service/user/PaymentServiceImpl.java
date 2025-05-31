package org.example.summerhackaton.domain.service.user;

import org.example.summerhackaton.dao.UserRepository;
import org.example.summerhackaton.domain.model.exceptions.BadRequestException;
import org.example.summerhackaton.domain.model.products.factory.Product;
import org.example.summerhackaton.domain.model.user.QRCode;
import org.example.summerhackaton.domain.model.user.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final UserRepository userRepository;

    public PaymentServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void retrieveProducts(QRCode qrCode) {
        // find the products to retrieve based on the userId in the QRCode
        UserEntity user = userRepository.findById(qrCode.getUserId())
                .orElseThrow(() -> new BadRequestException("User not found"));
        // Check if the user has enough products to retrieve
        if (user.getBoughtProducts().containsAll(qrCode.getProductsToRetrieve())) {
            // Remove the products from the user's product list
            user.getBoughtProducts().removeAll(qrCode.getProductsToRetrieve());
            // Save the updated user
            userRepository.save(user);
        } else {
            throw new BadRequestException("Not enough products to retrieve");
        }
    }

    @Override
    public void payForProducts(List<Product> products, String userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User not found"));

        // Sumar los precios usando BigDecimal
        java.math.BigDecimal totalCost = products.stream()
                .map(product -> product.getPrice())
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);

        if (user.getBalance().compareTo(totalCost) < 0) {
            throw new BadRequestException("Insufficient balance");
        }
        user.setBalance(user.getBalance().subtract(totalCost));
        user.getBoughtProducts().addAll(products);
        userRepository.save(user);
    }
}
