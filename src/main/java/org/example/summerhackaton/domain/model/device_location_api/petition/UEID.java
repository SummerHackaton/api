package org.example.summerhackaton.domain.model.device_location_api.petition;

import lombok.Data;

@Data
public class UEID {
    private String externalId;
    private String msisdn;
    private String ipv4Addr;
    private String ipv6Addr;
}
