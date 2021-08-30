package org.hsh.crm.ms.audit.dal.entities.dto;

import dto.audit.DataAuditValue;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * содержимое поля values ентити DataAudit.values
 */
@XmlRootElement(name = "dataAuditValues")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonbPropertyOrder({PropertyOrderStrategy.ANY})
public class DataAuditValues implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElementWrapper(name = "values")
    @XmlElement(name = "value")
    @JsonbProperty(value = "values")
    private List<DataAuditValue> values = new ArrayList<>();

    public DataAuditValues() {
        // default constructor
    }

    public DataAuditValues(List<DataAuditValue> values) {
        if (values != null)
            this.values = values;
    }

    public List<DataAuditValue> getValues() {
        return values;
    }

    public void setValues(List<DataAuditValue> values) {
        this.values = values;
    }
}
