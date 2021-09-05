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

    @XmlElement(name = "code")
    @JsonbProperty(value = "code")
    private String code;

    @XmlElement(name = "name")
    @JsonbProperty(value = "name")
    private String name;

    public Role() {
        // default
    }
}
