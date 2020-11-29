package test;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@Entity
@XmlRootElement(name = "mockComplexEntity")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class MockComplexEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @XmlElement(name = "id")
    @JsonbProperty(value = "id")
    private MockComplexKey id;

    @XmlElement(name = "name")
    @JsonbProperty(value = "name")
    private String name;

    public MockComplexEntity() {
        // default
    }

    public MockComplexEntity(MockComplexKey id, String name) {
        this.id = id;
        this.name = name;
    }

    public MockComplexKey getId() {
        return id;
    }

    public void setId(MockComplexKey id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof MockComplexEntity))
            return false;

        MockComplexEntity other = (MockComplexEntity) obj;

        return Objects.equals(this.id, other.id);
    }
}
