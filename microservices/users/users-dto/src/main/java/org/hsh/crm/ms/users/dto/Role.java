package org.hsh.crm.ms.users.dto;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "role")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonbPropertyOrder({PropertyOrderStrategy.ANY})
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "id")
    @JsonbProperty("id")
    private Long id;

    @XmlElement(name = "code")
    @JsonbProperty(value = "code")
    private String code;

    @XmlElement(name = "name")
    @JsonbProperty(value = "name")
    private String name;

    public Role() {
        // default
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
