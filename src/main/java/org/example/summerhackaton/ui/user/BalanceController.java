package org.example.summerhackaton.ui.user;

import org.example.summerhackaton.domain.service.user.BalanceService;
import org.example.summerhackaton.domain.service.user.BalanceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static org.example.summerhackaton.common.Constantes.BALANCE_API;
import static org.example.summerhackaton.common.Constantes.BASE_URL;

@RestController
@RequestMapping(BASE_URL + BALANCE_API)
//se crea un flujo entero para balance en vez de usar el flujo de usuario para futuras expansiones
public class BalanceController {
    private final BalanceServiceImpl balanceServiceImpl;

    public BalanceController(BalanceServiceImpl balanceServiceImpl) {
        this.balanceServiceImpl = balanceServiceImpl;
    }

    // Add Balance
    @PostMapping("/add/{balanceAmount}/{userId}")
    public ResponseEntity<String> addBalance(@PathVariable BigDecimal balanceAmount, @PathVariable String userId) {
        balanceServiceImpl.addBalance(balanceAmount, userId);
        return ResponseEntity.ok("Balance added successfully for user: " + userId + " with amount: " + balanceAmount);
    }
}
