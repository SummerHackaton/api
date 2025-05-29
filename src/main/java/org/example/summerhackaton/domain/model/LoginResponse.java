package org.example.summerhackaton.domain.model;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class LoginResponse {
    private final String token;
    private final String refreshToken;
}
