package common.dal.entity;

import common.dal.CommonEntityConstants;
import common.util.StringUtils;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Базовая реализация entity c id
 *
 * @author elf
 */
@MappedSuperclass
public abstract class BaseIdEntity implements IdEntity {
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @GeneratedValue(
            generator = CommonEntityConstants.GENERATOR_OBJECT,
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = CommonEntityConstants.GENERATOR_OBJECT,
            sequenceName = CommonEntityConstants.SEQUENCE_OBJECTS,
            schema = CommonEntityConstants.SCHEMA_NAME,
            allocationSize = 1)
    private Long id;

    public BaseIdEntity() {
    }
    
    public BaseIdEntity(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public Long getId() {
        return this.id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "common.dal.entity.BaseIdentity[", "]")
                .add(StringUtils.buildKeyValue("id", id))
                .toString();
    }
}
