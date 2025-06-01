package org.example.summerhackaton.domain.service.apis;

import lombok.AllArgsConstructor;
import org.example.summerhackaton.domain.model.quality_on_demand_api.QualityOnDemandResponse;
import org.example.summerhackaton.domain.model.quality_on_demand_api.QualityOnDemandSessionRequest;
import org.example.summerhackaton.domain.service.open_gateway.OpenGatewayAuthenticationService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class QualityOnDemandService {

    private static final String QUALITY_ON_DEMAND_API_URL= "https://sandbox.opengateway.telefonica.com/apigateway/qod/v0";
    private static final String QUALITY_ON_DEMAND_SCOPE = "dpv:RequestedServiceProvision#qod";
    private final RestTemplate restTemplate;
    private final OpenGatewayAuthenticationService openGatewayAuthenticationService;

    public QualityOnDemandResponse createSession(QualityOnDemandSessionRequest request) {
        String apiToken = openGatewayAuthenticationService.provideToken(QUALITY_ON_DEMAND_SCOPE).getAccessToken();
        String url = QUALITY_ON_DEMAND_API_URL + "/sessions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiToken);

        HttpEntity<QualityOnDemandSessionRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<QualityOnDemandResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                QualityOnDemandResponse.class
        );

        return response.getBody();
    }
}
