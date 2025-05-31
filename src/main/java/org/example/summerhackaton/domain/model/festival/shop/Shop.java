package org.example.summerhackaton.domain.model.festival.shop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shops")
@Getter
@Setter
public class Shop {
    @Id
    private String id;
    private String name;
    private String owner;

    public Shop () {
        super();
    }
}
