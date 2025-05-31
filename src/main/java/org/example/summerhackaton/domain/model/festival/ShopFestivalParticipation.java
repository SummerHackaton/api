package org.example.summerhackaton.domain.model.festival;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ShopFestivalParticipation {
    private String festivalId;
    private LocalDate setupDate;
    private String boothLocation;
    private double earnings;
    private List<String> productTypesSold;

    public ShopFestivalParticipation() {
        super();
    }
}
