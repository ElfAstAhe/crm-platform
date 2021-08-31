package org.hsh.crm.ms.audit.dto;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "transactionAudit")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class TransactionAudit implements Serializable {
    private static final long serialVersionUID = 1;

    // ..
}
