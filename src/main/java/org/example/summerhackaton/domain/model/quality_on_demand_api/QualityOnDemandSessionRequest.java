package org.example.summerhackaton.domain.model.quality_on_demand_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QualityOnDemandSessionRequest {
    private Device device;
    private ApplicationServer applicationServer;
    private String qosProfile;
    private Integer duration;


    @Data
    public static class Device {
        private String phoneNumber;
    }

    @Data
    public static class ApplicationServer {
        @JsonProperty("ipv4Address")
        private String ipv4Address;
    }
}
