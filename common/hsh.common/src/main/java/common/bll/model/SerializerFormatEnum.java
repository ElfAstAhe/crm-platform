package common.bll.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum SerializerFormatEnum {
    @XmlEnumValue("NONE")
    NONE,

    @XmlEnumValue("XML")
    XML,

    @XmlEnumValue("JSON")
    JSON,

    @XmlEnumValue("YAML")
    YAML
}
