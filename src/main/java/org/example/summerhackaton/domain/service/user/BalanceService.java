package org.example.summerhackaton.domain.service.user;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface BalanceService {
    public boolean addBalance(BigDecimal balanceAmount, String userId);
}
