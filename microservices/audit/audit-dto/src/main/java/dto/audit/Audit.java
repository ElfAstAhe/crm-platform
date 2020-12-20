package dto.audit;

import com.migesok.jaxb.adapter.javatime.OffsetDateTimeXmlAdapter;

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

@XmlRootElement(name = "audit")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class Audit implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "id")
    @JsonbProperty(value = "id")
    private Long id;

    @XmlJavaTypeAdapter(OffsetDateTimeXmlAdapter.class)
    @XmlElement(name = "eventDate")
    @JsonbProperty(value = "eventDate")
    private OffsetDateTime eventDate;

    @XmlElement(name = "event")
    @JsonbProperty(value = "event")
    private String event;

    @XmlElement(name = "source")
    @JsonbProperty(value = "source")
    private String source;

    @XmlElement(name = "requestId")
    @JsonbProperty(value = "requestId")
    private String requestId;

    @XmlElement(name = "user")
    @JsonbProperty(value = "user")
    private String user;

    @XmlElement(name = "status")
    @JsonbProperty(value = "status")
    private String status;

    @XmlElement(name = "additional")
    @JsonbProperty(value = "additional")
    private String additional;

    public Audit() {
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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }
}
