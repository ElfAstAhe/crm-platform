package dto.audit;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum SecurityAuditEventEnum {
    LOGIN,
    LOGOFF;
}
