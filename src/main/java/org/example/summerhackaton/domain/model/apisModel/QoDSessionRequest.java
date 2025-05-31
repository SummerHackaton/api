package org.example.summerhackaton.domain.model.apisModel;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QoDSessionRequest {
    private Device device;
    private ApplicationServer applicationServer;
    private String qosProfile;
    private Webhook webhook;
    private Integer duration;
    private String asId;
}
