package org.hsh.crm.ms.audit.bll.model;

import org.hsh.common.bll.model.Model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

public class DataAudit implements Model, Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private OffsetDateTime eventDate;
    private AuditStatusEnum status;
    private String source;
    private String requestId;
    private DataAuditEventEnum event;
    private String className;
    private String classDescription;
    private String objectId;
    private String objectName;
    private String values;
    private String userLogin;
    private String runAsUser;

    public DataAudit() {
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

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
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

    public AuditStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AuditStatusEnum status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataAudit dataAudit = (DataAudit) o;
        return Objects.equals(id, dataAudit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "DataAudit{" +
                "id=" + id +
                ", eventDate=" + eventDate +
                ", source='" + source + '\'' +
                ", requestId='" + requestId + '\'' +
                ", event=" + event +
                '}';
    }
}
