package dto.audit;

import com.migesok.jaxb.adapter.javatime.OffsetDateTimeXmlAdapter;
import common.util.StringUtils;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@XmlRootElement(name = "dataAudit")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class DataAudit implements Serializable {
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
    private DataAuditEventEnum event;

    @XmlElement(name = "className")
    @JsonbProperty(value = "className")
    private String className;

    @XmlElement(name = "classDescription")
    @JsonbProperty(value = "classDescription")
    private String classDescription;

    @XmlElement(name = "objectId")
    @JsonbProperty(value = "objectId")
    private String objectId;

    @XmlElement(name = "objectName")
    @JsonbProperty(value = "objectName")
    private String objectName;

    @XmlElementWrapper(name = "values")
    @XmlElement(name = "value")
    @JsonbProperty(value = "values")
    private List<DataAuditValue> values = new ArrayList<>();

    @XmlElement(name = "user")
    @JsonbProperty(value = "user")
    private String user;

    @XmlElement(name = "runAsUser")
    @JsonbProperty(value = "runAsUser")
    private String runAsUser;

    @XmlElement(name = "status")
    @JsonbProperty(value = "status")
    private AuditStatusEnum status;

    public DataAudit() {
        // default constructor
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

    public List<DataAuditValue> getValues() {
        return values;
    }

    public void setValues(List<DataAuditValue> values) {
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
        if (!((obj instanceof DataAudit)))
            return false;
        if (obj == this)
            return true;
        DataAudit other = (DataAudit) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return new StringJoiner(StringUtils.DELIMITER, StringUtils.buildPrefix(this), StringUtils.SUFFIX)
                .add(StringUtils.buildKeyValue("id", id))
                .toString();
    }
}
