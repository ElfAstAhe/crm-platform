package dto.audit;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "dataAuditValue")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class DataAuditValue implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "name")
    @JsonbProperty(value = "name")
    private String name;

    @XmlElement(name = "description")
    @JsonbProperty(value = "description")
    private String description;

    @XmlElement(name = "before")
    @JsonbProperty(value = "before")
    private String before;

    @XmlElement(name = "after")
    @JsonbProperty(value = "after")
    private String after;

    public DataAuditValue() {
        // default constructor
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }
}
