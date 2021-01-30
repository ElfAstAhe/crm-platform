package common.dal.entity;

import common.dal.CommonDalConstants;
import common.util.StringUtils;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
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
            generator = CommonDalConstants.GENERATOR_OBJECT,
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = CommonDalConstants.GENERATOR_OBJECT,
            sequenceName = CommonDalConstants.SEQUENCE_OBJECTS,
            schema = CommonDalConstants.SCHEMA_NAME,
            allocationSize = 1)
    private Long id;

    public BaseIdEntity() {
    }
    
    public BaseIdEntity(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return this.id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof BaseIdEntity)) {
            return false;
        }
        BaseIdEntity other = (BaseIdEntity) object;

        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return new StringJoiner(StringUtils.DELIMITER, StringUtils.buildPrefix(this), StringUtils.SUFFIX)
                .add(StringUtils.buildKeyValue("id", StringUtils.toNullString(getId())))
                .toString();
    }
}
