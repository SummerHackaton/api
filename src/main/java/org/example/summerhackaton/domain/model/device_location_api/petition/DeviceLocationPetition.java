package org.example.summerhackaton.domain.model.device_location_api.petition;

import lombok.Data;

@Data
public class DeviceLocationPetition {
    private UEID ueid;
    private int uePort;
    private double latitude;
    private double longitude;
    private int accuracy;
}


