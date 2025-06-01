package org.example.summerhackaton.domain.model.quality_on_demand_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QualityOnDemandSessionRequest {
    private Device device;
    private ApplicationServer applicationServer;
    private String qosProfile;
    private Integer duration;


    @Data
    @AllArgsConstructor
    public static class Device {
        private String phoneNumber;
    }

    @Data
    @AllArgsConstructor
    public static class ApplicationServer {
        @JsonProperty("ipv4Address")
        private String ipv4Address;
    }
}
