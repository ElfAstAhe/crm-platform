package microservice.common.dal.entities;

import common.dal.entity.BaseIdEntity;
import common.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

/**
 *
 * @author elf
 */
@Entity
@Table(name = "settings")
@NamedQuery(lockMode = LockModeType.NONE, name = "Setting.findAll", query = "SELECT s FROM Setting s")
@NamedQuery(lockMode = LockModeType.NONE, name = "Setting.findById", query = "SELECT s FROM Setting s WHERE s.id = :id")
@NamedQuery(lockMode = LockModeType.NONE, name = "Setting.findByCode", query = "select s from Setting s where s.code = :code")
@NamedQuery(lockMode = LockModeType.NONE, name = "Setting.findByName", query = "select s from Setting s where s.name = :name")
@Cacheable(false)
public class Setting extends BaseIdEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Version
    @Column(name = "version", nullable = false)
    @SuppressWarnings("FieldMayBeFinal")
    private long version;

    @Basic(optional = false)
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Column(length = 100)
    private String name;

    @Column(length = 1024)
    private String value;

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
        return new StringJoiner(StringUtils.DELIMITER, StringUtils.buildPrefix(this), StringUtils.SUFFIX)
                .add(StringUtils.buildKeyValue("id", StringUtils.toNullString(getId())))
                .add(StringUtils.buildKeyValue("version", StringUtils.toNullString(version)))
                .add(StringUtils.buildKeyValue("code", StringUtils.toNullString(code)))
                .add(StringUtils.buildKeyValue("name", StringUtils.toNullString(name)))
                .add(StringUtils.buildKeyValue("value", StringUtils.toNullString(value)))
                .toString();
    }
}