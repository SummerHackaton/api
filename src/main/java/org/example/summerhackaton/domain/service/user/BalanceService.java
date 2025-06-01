package org.example.summerhackaton.domain.service.user;

import java.math.BigDecimal;

public interface BalanceService {
    boolean addBalance(BigDecimal balanceAmount, String userId);
}
