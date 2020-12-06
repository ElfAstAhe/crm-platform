package dal.entities;

import common.dal.entity.BaseIdEntity;
import common.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "data_audit")
public class DataAudit extends BaseIdEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "event_date")
    private OffsetDateTime eventDate;

    @Column(name = "source")
    private String source;

    @Column(name = "request_id")
    private String requestId;

    @Enumerated(EnumType.STRING)
    @Column(name = "event")
    private DataAuditEventEnum event;

    @Column(name = "class_name")
    private String className;

    @Column(name = "class_description")
    private String classDescription;

    @Column(name = "object_id")
    private String objectId;

    @Column(name = "object_name")
    private String objectName;

    @Lob
    @Column(name = "values")
    private String values;

    @Column(name = "user")
    private String user;

    @Column(name = "run_as_user")
    private String runAsUser;

    public DataAudit() {
        // default constructor
    }

    public OffsetDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(OffsetDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public DataAuditEventEnum getEvent() {
        return event;
    }

    public void setEvent(DataAuditEventEnum event) {
        this.event = event;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRunAsUser() {
        return runAsUser;
    }

    public void setRunAsUser(String runAsUser) {
        this.runAsUser = runAsUser;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof DataAudit)) {
            return false;
        }
        DataAudit other = (DataAudit) object;

        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return new StringJoiner(StringUtils.DELIMITER, StringUtils.buildPrefix(this), StringUtils.SUFFIX)
                .add(StringUtils.buildKeyValue("id", StringUtils.toNullString(getId())))
                .add(StringUtils.buildKeyValue("event", StringUtils.toNullString(event)))
                .add(StringUtils.buildKeyValue("className", StringUtils.toNullString(className)))
                .toString();
    }
}
