package org.example.summerhackaton.domain.model.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_roles")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRolesEntity {
    @Id
    private String id;

    private RolesEntity rol;

    private UserEntity user;
}