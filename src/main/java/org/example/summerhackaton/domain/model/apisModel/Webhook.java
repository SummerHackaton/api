package org.example.summerhackaton.domain.model.apisModel;

public class Webhook {
    private String notificationUrl;
    private String notificationAuthToken; // Opcional

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    public String getNotificationAuthToken() {
        return notificationAuthToken;
    }

    public void setNotificationAuthToken(String notificationAuthToken) {
        this.notificationAuthToken = notificationAuthToken;
    }
}
