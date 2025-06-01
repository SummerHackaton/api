package org.example.summerhackaton.domain.model.quality_on_demand_api;

public class QualityOnDemandResponse {
    private String sessionId;


    public QualityOnDemandResponse (String sessionId) {
        this.sessionId = sessionId;
    }

    public QualityOnDemandResponse() {
        super();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
