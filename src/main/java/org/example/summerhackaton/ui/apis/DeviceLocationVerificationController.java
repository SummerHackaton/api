package org.example.summerhackaton.ui.apis;

import lombok.RequiredArgsConstructor;
import org.example.summerhackaton.common.Constantes;
import org.example.summerhackaton.domain.model.device_location_api.petition.DeviceLocationPetition;
import org.example.summerhackaton.domain.service.apis.DeviceLocationVerificationApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constantes.BASE_URL)
@RequiredArgsConstructor
public class DeviceLocationVerificationController {

    private final DeviceLocationVerificationApiService service;

    @PostMapping("/verify_location")
    public ResponseEntity<Boolean> isOnFestivalRange (@RequestBody DeviceLocationPetition petition) {
        return ResponseEntity.ok(service.isOnFestivalRange(petition));
    }
}
