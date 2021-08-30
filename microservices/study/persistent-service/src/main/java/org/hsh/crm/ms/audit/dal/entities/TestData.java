package org.hsh.crm.ms.audit.dal.entities;

import common.dal.entity.IdEntity;
import org.hsh.crm.ms.audit.dal.DalConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "test_data")
@Cacheable(false)
public class TestData implements IdEntity, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = DalConstants.GENERATOR_OBJECT)
    private Long id;

    @Basic(optional = false)
    @Column(name = "dummy", nullable = false, length = 50)
    private String dummy;

    @Column(name = "dummy_2", length = 200)
    private String dummy2;

    public TestData() {
        // default
    }

    public TestData(String dummy, String dummy2) {
        this.id = null;
        this.dummy = dummy;
        this.dummy2 = dummy2;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDummy() {
        return dummy;
    }

    public void setDummy(String dummy) {
        this.dummy = dummy;
    }

    public String getDummy2() {
        return dummy2;
    }

    public void setDummy2(String dummy2) {
        this.dummy2 = dummy2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof TestData))
            return false;

        TestData other = (TestData) obj;

        return Objects.equals(this.id, other.id);
    }
}
