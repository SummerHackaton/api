package org.example.summerhackaton.domain.model.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "user_login")
@Getter
@Setter
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

    public UserEntity(String username, String password, Set<RolesEntity> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.enabled = true;
    }
}