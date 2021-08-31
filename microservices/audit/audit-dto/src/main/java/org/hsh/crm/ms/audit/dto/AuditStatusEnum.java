package org.hsh.crm.ms.audit.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum AuditStatusEnum {
    @XmlEnumValue("FAIL")
    FAIL,

    @XmlEnumValue("SUCCESS")
    SUCCESS;

}
