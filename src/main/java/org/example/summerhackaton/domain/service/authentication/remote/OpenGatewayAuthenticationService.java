package org.example.summerhackaton.domain.service.authentication.remote;

import lombok.RequiredArgsConstructor;
import org.example.summerhackaton.domain.model.open_gateway_authentication.CIBAAuthorizationResponse;
import org.example.summerhackaton.domain.model.security.OpenGatewayToken;
import org.example.summerhackaton.domain.service.security.KeyStoreService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OpenGatewayAuthenticationService {

    @Value("${telefonica.client-id}")
    private String clientId;

    @Value("${telefonica.client-secret}")
    private String clientSecret;

    @Value("${telefonica.login-hint}")
    private String loginHint;

    @Value("${security.token-password}")
    private String tokenPassword;

    private final RestTemplate restTemplate;
    private final KeyStoreService keyStoreService;

    private static final String AUTH_URL = "https://sandbox.opengateway.telefonica.com/apigateway/bc-authorize";
    private static final String TOKEN_URL = "https://sandbox.opengateway.telefonica.com/apigateway/token";


    public OpenGatewayToken provideToken(String scope) {
        // Step 1: Get auth_req_id
        CIBAAuthorizationResponse authResponse = getAuthReqId(scope);

        // Step 2: Exchange auth_req_id for access token
        OpenGatewayToken openGatewayToken = getToken(authResponse.getAuthReqId());
        keyStoreService.saveToken(openGatewayToken.getAccessToken(),scope,tokenPassword.toCharArray());
        System.out.println("actual -> " + openGatewayToken.getAccessToken() + ", " +
                "saved in keystore -> " + keyStoreService.getToken(scope,tokenPassword.toCharArray()));
        System.out.println(openGatewayToken.getAccessToken().equals(keyStoreService.getToken(scope,tokenPassword.toCharArray())));
        return openGatewayToken;
    }

    private CIBAAuthorizationResponse getAuthReqId(String scope) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(clientId, clientSecret);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("login_hint", loginHint);
        body.add("scope", scope);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        return restTemplate.postForObject(AUTH_URL, request, CIBAAuthorizationResponse.class);
    }

    private OpenGatewayToken getToken(String authReqId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(clientId, clientSecret);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "urn:openid:params:grant-type:ciba");
        body.add("auth_req_id", authReqId);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        return restTemplate.postForObject(TOKEN_URL, request, OpenGatewayToken.class);
    }
}
