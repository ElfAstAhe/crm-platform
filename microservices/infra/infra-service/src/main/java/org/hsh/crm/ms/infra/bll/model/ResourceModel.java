package org.hsh.crm.ms.infra.bll.model;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "resource")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class ResourceModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "id")
    @JsonbProperty("id")
    private Long id;

    public ResourceModel() {
        // default
    }


}
