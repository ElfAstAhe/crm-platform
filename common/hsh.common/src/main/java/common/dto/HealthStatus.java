package common.dto;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "healthStatus")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class HealthStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "status")
    @JsonbProperty(value = "status")
    private HealthStatusEnum status;

    public HealthStatus() {
        status = HealthStatusEnum.OK;
    }

    public HealthStatusEnum getStatus() {
        return status;
    }

    public void setStatus(HealthStatusEnum status) {
        this.status = status;
    }
}
