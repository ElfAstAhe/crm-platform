package dal.entities;

import common.dal.entity.BaseIdEntity;
import common.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "security_audit")
@Cacheable(false)
public class SecurityAudit extends BaseIdEntity implements Serializable {
    private static final long serialVersionUID = 1L;

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
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof SecurityAudit)) {
            return false;
        }
        SecurityAudit other = (SecurityAudit) object;

        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return new StringJoiner(StringUtils.DELIMITER, StringUtils.buildPrefix(this), StringUtils.SUFFIX)
                .add(StringUtils.buildKeyValue("id", StringUtils.toNullString(getId())))
                .add(StringUtils.buildKeyValue("event", StringUtils.toNullString(event)))
                .add(StringUtils.buildKeyValue("user", StringUtils.toNullString(userLogin)))
                .toString();
    }
}
