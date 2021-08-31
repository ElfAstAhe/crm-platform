package org.hsh.crm.ms.audit.dto;

import com.migesok.jaxb.adapter.javatime.OffsetDateTimeXmlAdapter;
import org.hsh.common.util.HshStringUtils;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@XmlRootElement(name = "securityAudit")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class SecurityAudit implements Serializable {
    private static final long serialVersionUID = 1;

    @XmlElement(name = "id")
    @JsonbProperty(value = "id")
    private Long id;

    @XmlJavaTypeAdapter(OffsetDateTimeXmlAdapter.class)
    @XmlElement(name = "date")
    @JsonbProperty(value = "date")
    private OffsetDateTime date;

    @XmlElement(name = "source")
    @JsonbProperty(value = "source")
    private String source;

    @XmlElement(name = "requestId")
    @JsonbProperty(value = "requestId")
    private String requestId;

    @XmlElement(name = "event")
    @JsonbProperty(value = "event")
    private SecurityAuditEventEnum event;

    @XmlElement(name = "user")
    @JsonbProperty(value = "user")
    private String user;

    @XmlElement(name = "status")
    @JsonbProperty(value = "status")
    private AuditStatusEnum status;

    public SecurityAudit() {
        // default
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public AuditStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AuditStatusEnum status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!((obj instanceof SecurityAudit)))
            return false;
        if (obj == this)
            return true;
        SecurityAudit other = (SecurityAudit) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return new StringJoiner(HshStringUtils.DELIMITER, HshStringUtils.buildPrefix(this), HshStringUtils.SUFFIX)
                .add(HshStringUtils.buildKeyValue("id", id))
                .toString();
    }
}
