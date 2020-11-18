package dto.media.files;

import javax.json.bind.annotation.JsonbProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "mediaFile")
@XmlAccessorType(XmlAccessType.FIELD)
public class MediaFile implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "fileName")
    @JsonbProperty(value = "fileName")
    private String fileName;

    @XmlElement(name = "fileType")
    @JsonbProperty(value = "fileType")
    private MediaFileTypeEnum fileType;

    public MediaFile() {
        // default constructor
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MediaFileTypeEnum getFileType() {
        return fileType;
    }

    public void setFileType(MediaFileTypeEnum fileType) {
        this.fileType = fileType;
    }
}
