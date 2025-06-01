package org.example.summerhackaton.domain.model.user;

import lombok.*;
import org.example.summerhackaton.domain.model.products.factory.Product;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Document(collection = "user_login")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private boolean enabled;
    private String codigo;
    private Set<RolesEntity> roles;
    private List<Product> boughtProducts;
    private BigDecimal balance;

}