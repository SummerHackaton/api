package org.example.summerhackaton.domain.model;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@ToString
public class Token {
    private final String access;
    private final String refresh;

    public Token(String access, String refresh) {
        this.access = access;
        this.refresh = refresh;
    }
}
