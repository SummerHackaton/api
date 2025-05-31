package org.example.summerhackaton.domain.model.apisModel;

import org.example.summerhackaton.domain.service.apis.QoDService;

import java.util.List;

public class DevicePorts {
    private List<PortRange> ranges; // Opcional
    private List<Integer> ports; // Opcional

    public List<PortRange> getRanges() {
        return ranges;
    }

    public void setRanges(List<PortRange> ranges) {
        this.ranges = ranges;
    }

    public List<Integer> getPorts() {
        return ports;
    }

    public void setPorts(List<Integer> ports) {
        this.ports = ports;
    }
}
