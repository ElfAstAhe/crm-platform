package org.hsh.crm.ms.audit.ep.dto;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "testDto")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class TestDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "id")
    @JsonbProperty(value = "id")
    public Long id;

    @XmlElement(name = "dummy")
    @JsonbProperty(value = "dummy")
    public String dummy;

    @XmlElement(name = "dummy2")
    @JsonbProperty(value = "dummy2")
    public String dummy2;

    public TestDto() {
        // default
    }

    public TestDto(Long id, String dummy, String dummy2) {
        this.id = id;
        this.dummy = dummy;
        this.dummy2 = dummy2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDummy() {
        return dummy;
    }

    public void setDummy(String dummy) {
        this.dummy = dummy;
    }

    public String getDummy2() {
        return dummy2;
    }

    public void setDummy2(String dummy2) {
        this.dummy2 = dummy2;
    }
}
