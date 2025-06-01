package org.example.summerhackaton.ui.demo;

import org.example.summerhackaton.domain.model.products.factory.Product;
import org.example.summerhackaton.domain.service.automator.AutomatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.example.summerhackaton.common.Constantes.BASE_URL;


@RestController
@RequestMapping(BASE_URL + "/automator")
public class AutomatorController {
    private final AutomatorService automatorService;

    public AutomatorController(AutomatorService automatorService) {
        this.automatorService = automatorService;
    }

    @GetMapping("/canjeador-demo/{bulkCart}")
    public ResponseEntity<Boolean> canjeadorDemo(@PathVariable String bulkCart) {
        List<Product> cart = automatorService.parseCart(bulkCart);
        return ResponseEntity.ok(automatorService.canjeadorDemo(cart));
    }
}
