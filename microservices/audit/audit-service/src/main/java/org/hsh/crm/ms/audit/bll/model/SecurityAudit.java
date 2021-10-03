package org.hsh.crm.ms.audit.bll.model;

import org.hsh.common.bll.model.Model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

public class SecurityAudit implements Model, Serializable {
    private Long id;
    private OffsetDateTime eventDate;
    private AuditStatusEnum status;
    private String source;
    private String requestId;
    private SecurityAuditEventEnum event;
    private String userLogin;

    public SecurityAudit() {
        // default
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(OffsetDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public AuditStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AuditStatusEnum status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public SecurityAuditEventEnum getEvent() {
        return event;
    }

    public void setEvent(SecurityAuditEventEnum event) {
        this.event = event;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityAudit that = (SecurityAudit) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SecurityAudit{" +
                "id=" + id +
                ", eventDate=" + eventDate +
                ", status=" + status +
                ", source='" + source + '\'' +
                ", requestId='" + requestId + '\'' +
                ", event=" + event +
                ", userLogin='" + userLogin + '\'' +
                '}';
    }
}
