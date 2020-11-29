package test;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@XmlRootElement(name = "mockComplexKey")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class MockComplexKey implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "subStrKey")
    @JsonbProperty(value = "subStrKey")
    private String subStrKey;

    @XmlElement(name = "subLongKey")
    @JsonbProperty(value = "subLongKey")
    private Long subLongKey;

    public MockComplexKey() {
        // default
    }

    public MockComplexKey(String subStrKey, Long subLongKey) {
        this.subStrKey = subStrKey;
        this.subLongKey = subLongKey;
    }

    public String getSubStrKey() {
        return subStrKey;
    }

    public void setSubStrKey(String subStrKey) {
        this.subStrKey = subStrKey;
    }

    public Long getSubLongKey() {
        return subLongKey;
    }

    public void setSubLongKey(Long subLongKey) {
        this.subLongKey = subLongKey;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.subStrKey, this.subLongKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof MockComplexKey))
            return false;
        MockComplexKey other = (MockComplexKey) obj;

        return Objects.equals(this.subStrKey, other.subStrKey) &&
                Objects.equals(this.subLongKey, other.subLongKey);
    }

    @Override
    public String toString() {
        return new StringJoiner(",", this.getClass().getName(), "")
                .add(String.format("subStrKey=[%s]", this.subStrKey))
                .add(String.format("subLongKey=[%s]", this.subLongKey))
                .toString();
    }
}
