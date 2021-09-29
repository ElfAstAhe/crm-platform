package org.hsh.crm.ms.audit.dal.entities;

import org.hsh.common.util.HshStringUtils;
import org.hsh.ms.common.dal.entity.BaseIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.StringJoiner;

@MappedSuperclass
public class SecurityAudit extends BaseIdEntity {
    @Column(name = "event_date")
    private OffsetDateTime eventDate;

    @Column(name = "source", length = 100)
    private String source;

    @Column(name = "request_id", length = 50)
    private String requestId;

    @Enumerated(EnumType.STRING)
    @Column(name = "event", length = 50)
    private SecurityAuditEventEnum event;

    @Column(name = "user_login", length = 100)
    private String userLogin;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private AuditStatusEnum status;

    public SecurityAudit() {
        // default
    }

    public OffsetDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(OffsetDateTime eventDate) {
        this.eventDate = eventDate;
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

    public AuditStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AuditStatusEnum status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override
    public String toString() {
        return new StringJoiner(HshStringUtils.DELIMITER, HshStringUtils.buildPrefix(this), HshStringUtils.SUFFIX)
                .add(HshStringUtils.buildKeyValue("id", HshStringUtils.toNullString(getId())))
                .add(HshStringUtils.buildKeyValue("event", HshStringUtils.toNullString(event)))
                .add(HshStringUtils.buildKeyValue("user", HshStringUtils.toNullString(userLogin)))
                .toString();
    }
}
