package dal.entities;

import common.dal.entity.BaseIdEntity;
import dal.DalConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author elf
 */
@Entity
@Table(name = "settings", schema = DalConstants.SCHEMA_NAME)
@NamedQuery(lockMode = LockModeType.NONE, name = "Setting.findAll", query = "SELECT s FROM Setting s")
@NamedQuery(lockMode = LockModeType.NONE, name = "Setting.findById", query = "SELECT s FROM Setting s WHERE s.id = :id")
@NamedQuery(lockMode = LockModeType.NONE, name = "Setting.findByCode", query = "select s from Setting s where s.code = :code")
@NamedQuery(lockMode = LockModeType.NONE, name = "Setting.findByName", query = "select s from Setting s where s.name = :name")
public class Setting extends BaseIdEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Column(length = 100)
    private String name;

    @Column(length = 1024)
    private String value;

    @Version
    @Column(name = "version", nullable = false)
    @SuppressWarnings("FieldMayBeFinal")
    private long version;

    public Setting(){
        super();
        version = 0L;
    }

    public Setting(Long id) {
        super(id);
        version = 0L;
    }

    public Setting(Long id,
                   String code,
                   String name,
                   String value) {
        super(id);
        this.code = code;
        this.name = name;
        this.value = value;
        version = 0L;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public long getVersion() {
        return version;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof Setting)) {
            return false;
        }
        Setting other = (Setting) object;

        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return "dal.entities.Setting[ id=" + this.getId() + " ]";
    }
}