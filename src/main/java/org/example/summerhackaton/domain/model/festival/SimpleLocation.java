package org.example.summerhackaton.domain.model.festival;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleLocation {
    private Double x;
    private Double y;
}
