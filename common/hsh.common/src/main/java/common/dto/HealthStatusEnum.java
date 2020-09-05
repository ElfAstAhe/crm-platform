package common.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum HealthStatusEnum {
    @XmlEnumValue("OK")
    OK;
}
