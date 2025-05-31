package org.example.summerhackaton.domain.model.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@Table(name = "user_roles")
@AllArgsConstructor
public class UserRolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "rol", nullable = false)
    private RolesEntity rol;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private UserEntity user;
}
