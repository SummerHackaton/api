package org.example.summerhackaton.ui.demo;

import org.example.summerhackaton.domain.model.products.factory.Product;
import org.example.summerhackaton.domain.service.automator.AutomatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.summerhackaton.common.Constantes.BASE_URL;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(BASE_URL + "/automator")
public class AutomatorController {
    private final AutomatorService automatorService;

    public AutomatorController(AutomatorService automatorService) {
        this.automatorService = automatorService;
    }

    @GetMapping("/canjeador-demo/{bulkCart}")
    public ResponseEntity<String> canjeadorDemo(@PathVariable String bulkCart) {
        final var cart = automatorService.parseCart(bulkCart);
        return ResponseEntity.ok(cart);
    }
}
