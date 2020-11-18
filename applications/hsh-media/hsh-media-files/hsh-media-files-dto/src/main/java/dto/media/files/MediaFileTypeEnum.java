package dto.media.files;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum MediaFileTypeEnum {
    @XmlEnumValue("PICTURE")
    PICTURE,

    @XmlEnumValue("AUDIO")
    AUDIO,

    @XmlEnumValue("VIDEO")
    VIDEO;
}
