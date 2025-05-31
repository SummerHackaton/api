package org.example.summerhackaton.domain.model.festival;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SimpleLocation {
    private BigDecimal x;
    private BigDecimal y;
}
