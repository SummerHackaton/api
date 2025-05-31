package org.example.summerhackaton.domain.model.open_gateway_authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

//Esto es lo que devuelve la primera llamada
@Data
public class CIBAAuthorizationResponse {
    @JsonProperty("auth_req_id")
    private String authReqId;

    @JsonProperty("expires_in")
    private int expiresIn;

    private int interval;

    @JsonProperty("consent_url")
    private String consentUrl;
}
