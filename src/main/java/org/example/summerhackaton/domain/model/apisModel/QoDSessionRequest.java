package org.example.summerhackaton.domain.model.apisModel;

import org.example.summerhackaton.domain.service.apis.QoDService;

public class QoDSessionRequest {
    private Device device;
    private ApplicationServer applicationServer;
    private DevicePorts devicePorts; // Opcional
    private ApplicationServerPorts applicationServerPorts; // Opcional
    private String qosProfile;
    private Webhook webhook; // Opcional
    private Integer duration; // Opcional (default 86400)
}
