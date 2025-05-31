package org.example.summerhackaton.domain.model.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//Esto es lo que devuelve la segunda llamada
@Data
public class OpenGatewayToken {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("id_token")
    private String idToken;

    private String scope;
    private String purpose;
}
