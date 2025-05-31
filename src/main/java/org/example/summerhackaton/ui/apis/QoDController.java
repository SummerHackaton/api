package org.example.summerhackaton.ui.apis;


import org.example.summerhackaton.domain.model.apisModel.QoDSessionRequest;
import org.example.summerhackaton.domain.model.apisModel.QoDSessionResponse;
import org.example.summerhackaton.domain.service.apis.QoDService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.summerhackaton.domain.service.apis.QoDService.QoDSessionRequest;
import org.example.summerhackaton.domain.service.apis.QoDService.QoDSessionResponse;

import java.util.List;

@RestController
@RequestMapping("/qod")
public class QoDController {

    private final QoDService qodService;

    public QoDController(QoDService qodService) {
        this.qodService = qodService;
    }

    @PostMapping("/sessions")
    public ResponseEntity<QoDSessionResponse> createSession
            (@RequestBody QoDSessionRequest request) {
        QoDSessionResponse response = qodService.createSession(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sessions/{sessionId}")
    public ResponseEntity<QoDSessionResponse> getSession(@PathVariable String sessionId) {
        QoDSessionResponse response = qodService.getSession(sessionId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/sessions/{sessionId}")
    public ResponseEntity<Void> deleteSession(@PathVariable String sessionId) {
        qodService.deleteSession(sessionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sessions")
    public ResponseEntity<List<QoDSessionResponse>> listSessions() {
        List<QoDSessionResponse> sessions = qodService.listSessions();
        return ResponseEntity.ok(sessions);
    }

}
