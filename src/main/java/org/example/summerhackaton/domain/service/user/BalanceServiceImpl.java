package org.example.summerhackaton.domain.service.user;

import org.example.summerhackaton.dao.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class BalanceServiceImpl implements BalanceService{
    private final UserRepository userRepository;

    public BalanceServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean addBalance(BigDecimal balanceAmount, String userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setBalance(user.getBalance().add(balanceAmount));
                    userRepository.save(user);
                    return true;
                })
                .orElse(Boolean.FALSE);
    }
}
