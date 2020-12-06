package dto.audit;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum DataAuditEventEnum {
    @XmlEnumValue("CREATED")
    CREATED,

    @XmlEnumValue("MODIFIED")
    MODIFIED,

    @XmlEnumValue("REMOVED")
    REMOVED;
}
