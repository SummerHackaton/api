package org.example.summerhackaton.ui.apis;

import lombok.RequiredArgsConstructor;
import org.example.summerhackaton.common.Constantes;
import org.example.summerhackaton.domain.model.quality_on_demand_api.QualityOnDemandResponse;
import org.example.summerhackaton.domain.service.apis.QualityOnDemandService;
import org.example.summerhackaton.domain.model.quality_on_demand_api.QualityOnDemandSessionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constantes.BASE_URL)
public class QualityOnDemandController {

    private final QualityOnDemandService qodService;

    @PostMapping("/sessions")
    public ResponseEntity<QualityOnDemandResponse> createSession(@RequestBody QualityOnDemandSessionRequest request) {
        return ResponseEntity.ok(qodService.createSession(request));
    }
}
