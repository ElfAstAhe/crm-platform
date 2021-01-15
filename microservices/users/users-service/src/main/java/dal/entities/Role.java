package dal.entities;

import common.dal.entity.BaseIdEntity;
import common.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "roles")
public class Role extends BaseIdEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Version
    @Column(name = "version", nullable = false)
    @SuppressWarnings("FieldMayBeFinal")
    private long version;

    @Column(name = "code", length = 50, nullable = false)
    private String code;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "description", length = 512)
    private String description;

    // default
    public Role() {
        super();
        this.version = 0L;
    }

    // full
    public Role(Long id, String code, String name, String description) {
        super(id);
        this.version = 0L;
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public long getVersion() {
        return version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;

        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return new StringJoiner(StringUtils.DELIMITER, StringUtils.buildPrefix(this), StringUtils.SUFFIX)
                .add(StringUtils.buildKeyValue("id", StringUtils.toNullString(getId())))
                .add(StringUtils.buildKeyValue("version", StringUtils.toNullString(version)))
                .add(StringUtils.buildKeyValue("code", StringUtils.toNullString(code)))
                .add(StringUtils.buildKeyValue("name", StringUtils.toNullString(name)))
                .add(StringUtils.buildKeyValue("description", StringUtils.toNullString(description)))
                .toString();
    }
}
