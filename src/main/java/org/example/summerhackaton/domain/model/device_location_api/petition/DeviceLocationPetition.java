package org.example.summerhackaton.domain.model.device_location_api.petition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DeviceLocationPetition {
    private UEID ueid;
    private double latitude;
    private double longitude;
    private int accuracy;

    public DeviceLocationPetition(UEID ueid) {

    }
}


