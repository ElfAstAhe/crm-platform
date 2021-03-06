package dto.audit;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum SecurityAuditEventEnum {
    @XmlEnumValue("LOGIN")
    LOGIN,

    @XmlEnumValue("LOGOFF")
    LOGOFF;
}
