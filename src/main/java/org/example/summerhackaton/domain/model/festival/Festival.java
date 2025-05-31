package org.example.summerhackaton.domain.model.festival;

import lombok.Getter;
import lombok.Setter;
import org.example.summerhackaton.domain.model.festival.shop.Shop;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "festivals")
@Getter
@Setter
public class Festival {
    @Id
    private String id;
    private String name;
    private LocationRange location;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Shop> stores;
    private List<ShopFestivalParticipation> participations;

    public Festival () {
        super();
        this.participations = new ArrayList<>();
    }
}

