package org.example.summerhackaton.domain.model.apisModel;

import org.example.summerhackaton.domain.service.apis.QoDService;

import java.util.List;

public class QoDSessionResponse {
    private Device device;
    private ApplicationServer applicationServer;
    private DevicePorts devicePorts;
    private ApplicationServerPorts applicationServerPorts;
    private String qosProfile;
    private Webhook webhook;
    private String sessionId;
    private Integer duration;
    private Long startedAt;
    private Long expiresAt;
    private String qosStatus;
    private List<Message> messages;
}
