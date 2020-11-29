package test;

import common.dal.entity.IdEntity;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@Entity
@XmlRootElement(name = "mockSimpleEntity")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class MockSimpleEntity implements Serializable, IdEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @XmlElement(name = "id")
    @JsonbProperty(value = "id")
    private Long id;

    @XmlElement(name = "name")
    @JsonbProperty(value = "name")
    private String name;

    public MockSimpleEntity() {
        // ..
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return Objects.hash(this.id, this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof MockSimpleEntity))
            return false;
        MockSimpleEntity other = (MockSimpleEntity)obj;
        return Objects.equals(this.id, other.id) &&
                Objects.equals(this.name, other.name);
    }
}
