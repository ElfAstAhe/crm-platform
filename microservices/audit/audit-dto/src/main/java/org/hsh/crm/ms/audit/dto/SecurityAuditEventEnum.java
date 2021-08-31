package org.hsh.crm.ms.audit.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum SecurityAuditEventEnum {
    @XmlEnumValue("LOGIN")
    LOGIN,

    @XmlEnumValue("LOGOFF")
    LOGOFF;
}
