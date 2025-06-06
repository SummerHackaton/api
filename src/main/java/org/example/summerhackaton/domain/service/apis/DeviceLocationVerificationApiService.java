package org.example.summerhackaton.domain.service.apis;

import lombok.RequiredArgsConstructor;
import org.example.summerhackaton.domain.model.device_location_api.petition.DeviceLocationPetition;
import org.example.summerhackaton.domain.model.device_location_api.response.DeviceLocationResponse;
import org.example.summerhackaton.domain.service.authentication.remote.OpenGatewayAuthenticationService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeviceLocationVerificationApiService {

    private final RestTemplate restTemplate;
    private final OpenGatewayAuthenticationService openGatewayAuthenticationService;

    private static final String DEVICE_VERIFICATION_API_URL = "https://sandbox.opengateway.telefonica.com/apigateway/location/v0/verify";
    private static final String DEVICE_LOCATION_SCOPE = "dpv:FraudPreventionAndDetection#device-location-read";

    public boolean isOnFestivalRange(DeviceLocationPetition petition) {
        String apiToken = openGatewayAuthenticationService.provideToken(DEVICE_LOCATION_SCOPE).getAccessToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiToken);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Create the request body
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> ueId = new HashMap<>();

        if (petition.getUeid().getMsisdn() != null) {
            ueId.put("msisdn", petition.getUeid().getMsisdn());
        }

        requestBody.put("ueId", ueId);
        requestBody.put("latitude", petition.getLatitude());
        requestBody.put("longitude", petition.getLongitude());
        requestBody.put("accuracy", petition.getAccuracy());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<DeviceLocationResponse> response = restTemplate.postForEntity(
                DEVICE_VERIFICATION_API_URL,
                request,
                DeviceLocationResponse.class
        );

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().isVerificationResult();
        }
        return false;
    }
}
