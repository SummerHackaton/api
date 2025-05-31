package org.example.summerhackaton.domain.model.apisModel;

import java.util.List;

public class QoDSessionListResponse {
    private List<QoDSessionResponse> sessions;

    public List<QoDSessionResponse> getSessions() { return sessions; }
    public void setSessions(List<QoDSessionResponse> sessions) { this.sessions = sessions; }
}
