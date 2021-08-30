package org.hsh.crm.ms.audit.dal.entities;

import org.hsh.common.util.HshStringUtils;
import org.hsh.ms.common.dal.entity.BaseIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.StringJoiner;

@Entity
@Table(name = "data_audit")
@Cacheable(false)
public class DataAudit extends BaseIdEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "event_date")
    private OffsetDateTime eventDate;

    @Column(name = "source", length = 100)
    private String source;

    @Column(name = "request_id", length = 50)
    private String requestId;

    @Enumerated(EnumType.STRING)
    @Column(name = "event", length = 50)
    private DataAuditEventEnum event;

    @Column(name = "class_name", length = 512)
    private String className;

    @Column(name = "class_description", length = 512)
    private String classDescription;

    @Column(name = "object_id", length = 50)
    private String objectId;

    @Column(name = "object_name", length = 100)
    private String objectName;

    @Lob
    @Column(name = "values")
    private String values;

    @Column(name = "user_login", length = 100)
    private String userLogin;

    @Column(name = "run_as_user", length = 100)
    private String runAsUser;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private AuditStatusEnum status;

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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getRunAsUser() {
        return runAsUser;
    }

    public void setRunAsUser(String runAsUser) {
        this.runAsUser = runAsUser;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public AuditStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AuditStatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new StringJoiner(HshStringUtils.DELIMITER, HshStringUtils.buildPrefix(this), HshStringUtils.SUFFIX)
                .add(HshStringUtils.buildKeyValue("id", HshStringUtils.toNullString(getId())))
                .add(HshStringUtils.buildKeyValue("event", HshStringUtils.toNullString(event)))
                .add(HshStringUtils.buildKeyValue("className", HshStringUtils.toNullString(className)))
                .toString();
    }
}
