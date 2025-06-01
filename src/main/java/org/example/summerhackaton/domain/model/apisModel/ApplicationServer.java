package org.example.summerhackaton.domain.model.apisModel;

public class ApplicationServer {
        private String ipv4Address; // Formato: "1.2.3.4" o "1.2.3.4/24"
        private String ipv6Address; // Opcional

    public String getIpv4Address() {
        return ipv4Address;
    }

    public void setIpv4Address(String ipv4Address) {
        this.ipv4Address = ipv4Address;
    }

    public String getIpv6Address() {
        return ipv6Address;
    }

    public void setIpv6Address(String ipv6Address) {
        this.ipv6Address = ipv6Address;
    }
}
