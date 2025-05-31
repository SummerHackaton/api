package org.example.summerhackaton.domain.service.apis;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.summerhackaton.domain.model.exceptions.QoDException;
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
    private final String apiKey;


    public QoDService(RestTemplate restTemplate,
                      @Value("${qod.api.base-url:https://opengateway.telefonica.com}") String baseUrl,
                      @Value("${qod.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    /**
     * Crea una nueva sesión QoD
     * @param request Datos de la sesión a crear
     * @return Respuesta con los detalles de la sesión creada
     */
    public QoDSessionResponse createSession(QoDSessionRequest request) {
        try {
            HttpHeaders headers = createHeaders();
            HttpEntity<QoDSessionRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<QoDSessionResponse> response = restTemplate.exchange(
                    baseUrl + "/qod/v0/sessions",
                    HttpMethod.POST,
                    entity,
                    QoDSessionResponse.class
            );

            return response.getBody();
        } catch (HttpClientErrorException e) {
            String responseBody = e.getResponseBodyAsString();
            throw new QoDException("Error QoD: " + e.getStatusCode() + " - " + responseBody, e);        }
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
                    baseUrl + "/qod/v0/sessions/" + sessionId,
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
                    baseUrl + "/qod/v0/sessions/" + sessionId,
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
                    baseUrl + "/qod/v0/sessions",
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
        headers.setBearerAuth(apiKey);
        return headers;
    }

    // DTOs para las requests y responses
    public static class QoDSessionRequest {
        @JsonProperty("externalSessionId")
        private String externalSessionId;

        @JsonProperty("ueId")
        private UeId ueId;

        @JsonProperty("qosProfile")
        private String qosProfile;  // String con nombre de perfil

        @JsonProperty("notificationUrl")
        private String notificationUrl;

        @JsonProperty("duration")
        private Integer duration;   // Duración en segundos

        @JsonProperty("asId")
        private String asId;

        // Getters y setters

        public String getExternalSessionId() {
            return externalSessionId;
        }

        public void setExternalSessionId(String externalSessionId) {
            this.externalSessionId = externalSessionId;
        }

        public String getQosProfile() {
            return qosProfile;
        }

        public void setQosProfile(String qosProfile) {
            this.qosProfile = qosProfile;
        }

        public UeId getUeId() {
            return ueId;
        }

        public void setUeId(UeId ueId) {
            this.ueId = ueId;
        }

        public String getNotificationUrl() {
            return notificationUrl;
        }

        public void setNotificationUrl(String notificationUrl) {
            this.notificationUrl = notificationUrl;
        }

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        public String getAsId() {
            return asId;
        }

        public void setAsId(String asId) {
            this.asId = asId;
        }
    }

    public static class UeId {
        @JsonProperty("msisdn")
        private String msisdn;       // Formato: +34666555432

        @JsonProperty("ipAddress")
        private String ipAddress;    // IPv4 o IPv6

        // Getters y setters
    }

    // RESPONSE CORREGIDO
    public static class QoDSessionResponse {
        @JsonProperty("sessionId")
        private String sessionId;

        @JsonProperty("qosProfile")
        private String qosProfile;

        @JsonProperty("expiresAt")
        private String expiresAt;    // Fecha ISO 8601

        // Getters y setters

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getQosProfile() {
            return qosProfile;
        }

        public void setQosProfile(String qosProfile) {
            this.qosProfile = qosProfile;
        }

        public String getExpiresAt() {
            return expiresAt;
        }

        public void setExpiresAt(String expiresAt) {
            this.expiresAt = expiresAt;
        }
    }
    public static class QoDSessionListResponse {
        private List<QoDSessionResponse> sessions;

        public List<QoDSessionResponse> getSessions() { return sessions; }
        public void setSessions(List<QoDSessionResponse> sessions) { this.sessions = sessions; }
    }

    public static class Device {
        private String phoneNumber;
        private String ipv4Address;
        private String ipv6Address;

        public Device() {}

        public Device(String phoneNumber, String ipv4Address, String ipv6Address) {
            this.phoneNumber = phoneNumber;
            this.ipv4Address = ipv4Address;
            this.ipv6Address = ipv6Address;
        }

        public String getPhoneNumber() { return phoneNumber; }
        public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

        @JsonProperty("ipv4Address")
        public String getIpv4Address() { return ipv4Address; }
        public void setIpv4Address(String ipv4Address) { this.ipv4Address = ipv4Address; }

        @JsonProperty("ipv6Address")
        public String getIpv6Address() { return ipv6Address; }
        public void setIpv6Address(String ipv6Address) { this.ipv6Address = ipv6Address; }
    }

    public static class ApplicationServer {
        private String ipv4Address;
        private String ipv6Address;

        public ApplicationServer() {}

        public ApplicationServer(String ipv4Address, String ipv6Address) {
            this.ipv4Address = ipv4Address;
            this.ipv6Address = ipv6Address;
        }

        @JsonProperty("ipv4Address")
        public String getIpv4Address() { return ipv4Address; }
        public void setIpv4Address(String ipv4Address) { this.ipv4Address = ipv4Address; }

        @JsonProperty("ipv6Address")
        public String getIpv6Address() { return ipv6Address; }
        public void setIpv6Address(String ipv6Address) { this.ipv6Address = ipv6Address; }
    }

    public static class QosProfile {
        private String name;
        private Integer minUpstreamBandwidth;
        private Integer minDownstreamBandwidth;
        private Integer maxUpstreamBandwidth;
        private Integer maxDownstreamBandwidth;
        private Integer minUpstreamLatency;
        private Integer minDownstreamLatency;
        private Integer maxUpstreamLatency;
        private Integer maxDownstreamLatency;

        public QosProfile() {}

        // Constructor para perfiles predefinidos
        public QosProfile(String name) {
            this.name = name;
        }

        // Getters y setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public Integer getMinUpstreamBandwidth() { return minUpstreamBandwidth; }
        public void setMinUpstreamBandwidth(Integer minUpstreamBandwidth) { this.minUpstreamBandwidth = minUpstreamBandwidth; }

        public Integer getMinDownstreamBandwidth() { return minDownstreamBandwidth; }
        public void setMinDownstreamBandwidth(Integer minDownstreamBandwidth) { this.minDownstreamBandwidth = minDownstreamBandwidth; }

        public Integer getMaxUpstreamBandwidth() { return maxUpstreamBandwidth; }
        public void setMaxUpstreamBandwidth(Integer maxUpstreamBandwidth) { this.maxUpstreamBandwidth = maxUpstreamBandwidth; }

        public Integer getMaxDownstreamBandwidth() { return maxDownstreamBandwidth; }
        public void setMaxDownstreamBandwidth(Integer maxDownstreamBandwidth) { this.maxDownstreamBandwidth = maxDownstreamBandwidth; }

        public Integer getMinUpstreamLatency() { return minUpstreamLatency; }
        public void setMinUpstreamLatency(Integer minUpstreamLatency) { this.minUpstreamLatency = minUpstreamLatency; }

        public Integer getMinDownstreamLatency() { return minDownstreamLatency; }
        public void setMinDownstreamLatency(Integer minDownstreamLatency) { this.minDownstreamLatency = minDownstreamLatency; }

        public Integer getMaxUpstreamLatency() { return maxUpstreamLatency; }
        public void setMaxUpstreamLatency(Integer maxUpstreamLatency) { this.maxUpstreamLatency = maxUpstreamLatency; }

        public Integer getMaxDownstreamLatency() { return maxDownstreamLatency; }
        public void setMaxDownstreamLatency(Integer maxDownstreamLatency) { this.maxDownstreamLatency = maxDownstreamLatency; }
    }


}
