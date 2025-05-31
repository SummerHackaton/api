package org.example.summerhackaton.domain.model;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Token {
    private final String access;
    private final String refresh;
}
