package org.example.summerhackaton.domain.service.apis;


import org.example.summerhackaton.domain.model.apisModel.QoDSessionListResponse;
import org.example.summerhackaton.domain.model.apisModel.QoDSessionRequest;
import org.example.summerhackaton.domain.model.apisModel.QoDSessionResponse;
import org.example.summerhackaton.domain.model.exceptions.QoDException;
import org.example.summerhackaton.domain.model.security.OpenGatewayToken;
import org.example.summerhackaton.domain.service.authentication.remote.OpenGatewayAuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class QoDService {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final OpenGatewayAuthenticationService openGatewayAuthenticationService;


    public QoDService(RestTemplate restTemplate,
                      @Value("${qod.api.base-url}") String baseUrl,
                      OpenGatewayAuthenticationService openGatewayAuthenticationService) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.openGatewayAuthenticationService = openGatewayAuthenticationService;
    }

    /**
     * Crea una nueva sesión QoD
     * @param request Datos de la sesión a crear
     * @return Respuesta con los detalles de la sesión creada
     */
    public QoDSessionResponse createSession(QoDSessionRequest request) {
        try {
            // 1. Obtener el token de autenticación
            OpenGatewayToken tokenResponse = openGatewayAuthenticationService.getAccessToken();
            String accessToken = tokenResponse.getAccessToken();

            // 2. Crear headers con el token
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(accessToken);  // Usar el token real

            // 3. Crear la entidad HTTP con headers
            HttpEntity<QoDSessionRequest> entity = new HttpEntity<>(request, headers);

            // 4. Realizar la llamada a la API
            ResponseEntity<QoDSessionResponse> response = restTemplate.exchange(
                    baseUrl + "/qod/v1/sessions",  // Asegurar versión correcta (v0/v1)
                    HttpMethod.POST,
                    entity,
                    QoDSessionResponse.class
            );

            return response.getBody();
        } catch (HttpClientErrorException e) {
            String responseBody = e.getResponseBodyAsString();
            throw new QoDException("Error QoD: " + e.getStatusCode() + " - " + responseBody, e);
        }
    }

    /**
     * Obtiene información de una sesión existente
     * @param sessionId ID de la sesión
     * @return Información de la sesión
     */
    public QoDSessionResponse getSession(String sessionId) {
        try {
            HttpHeaders headers = createHeaders();
            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<QoDSessionResponse> response = restTemplate.exchange(
                    baseUrl + "/qod/v1/sessions/" + sessionId,
                    HttpMethod.GET,
                    entity,
                    QoDSessionResponse.class
            );

            return response.getBody();
        } catch (HttpClientErrorException e) {
            String responseBody = e.getResponseBodyAsString();
            throw new QoDException("Error QoD: " + e.getStatusCode() + " - " + responseBody, e);
        }
    }

    /**
     * Elimina una sesión QoD
     * @param sessionId ID de la sesión a eliminar
     */
    public void deleteSession(String sessionId) {
        try {
            HttpHeaders headers = createHeaders();
            HttpEntity<Void> entity = new HttpEntity<>(headers);

            restTemplate.exchange(
                    baseUrl + "/qod/v1/sessions/" + sessionId,
                    HttpMethod.DELETE,
                    entity,
                    Void.class
            );
        } catch (HttpClientErrorException e) {
            String responseBody = e.getResponseBodyAsString();
            throw new QoDException("Error QoD: " + e.getStatusCode() + " - " + responseBody, e);
        }
    }

    /**
     * Lista todas las sesiones activas
     * @return Lista de sesiones
     */
    public List<QoDSessionResponse> listSessions() {
        try {
            HttpHeaders headers = createHeaders();
            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<QoDSessionListResponse> response = restTemplate.exchange(
                    baseUrl + "/qod/v1/sessions",
                    HttpMethod.GET,
                    entity,
                    QoDSessionListResponse.class
            );

            return response.getBody().getSessions();
        } catch (HttpClientErrorException e) {
            String responseBody = e.getResponseBodyAsString();
            throw new QoDException("Error QoD: " + e.getStatusCode() + " - " + responseBody, e);
        }
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }


}
