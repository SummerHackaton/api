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
@RequestMapping("/api/1.0/product/deprecated")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    // LA LOGICA DE ESTE MICRO CONTIENE LOS PAGOS DE LOS PRODUCTOS, PERO ESTO YA ESTABA HECHO EN EL PAYMENT CONTROLLER
    // ESTO ES PORQUE EL USUARIO PAGA POR PRODUCTOS, Y NO EL PRODUCTO PAGA POR SI MISMO

    @PostMapping("/add/{type}")
    //ESTO AÑADE UN PRODUCTO AL CARRITO DEL USUARIO, PERO NO ES OPTIMO PORQUE SIEMPRE VIENEN MUCHOS
    public ResponseEntity<Product> createProduct(
            @PathVariable String type,
            @RequestBody Map<String, Object> attributes
    ) {
return ResponseEntity.ok(new Product() {
});
    }

    @DeleteMapping("/clean_cart")
    // ESTO COMO ESTA AHORA BORRA EL CARRITO COMPLETO DEL USUARIO
    public ResponseEntity<List<Product>> cleanCart () {
        return ResponseEntity.ok(productService.cleanCart(SecurityContextHolder.getContext().getAuthentication().getName()));
    }
}
