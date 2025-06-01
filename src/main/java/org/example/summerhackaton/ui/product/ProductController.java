package org.example.summerhackaton.ui.product;

import lombok.RequiredArgsConstructor;
import org.example.summerhackaton.domain.model.products.factory.Product;
import org.example.summerhackaton.domain.service.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/1.0/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add/{type}")
    public ResponseEntity<Product> createProduct(
            @PathVariable String type,
            @RequestBody Map<String, Object> attributes
    ) {
        Product product = productService.createProduct(type, attributes);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/clean_cart")
    public ResponseEntity<List<Product>> cleanCart () {
        return ResponseEntity.ok(productService.cleanCart(SecurityContextHolder.getContext().getAuthentication().getName()));
    }
}
