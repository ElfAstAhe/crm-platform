package org.hsh.crm.ms.common.entities;

import org.hsh.common.util.HshStringUtils;
import org.hsh.ms.common.dal.entity.BaseIdEntity;

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
    public String toString() {
        return new StringJoiner(HshStringUtils.DELIMITER, HshStringUtils.buildPrefix(this), HshStringUtils.SUFFIX)
                .add(HshStringUtils.buildKeyValue("id", HshStringUtils.toNullString(getId())))
                .add(HshStringUtils.buildKeyValue("version", HshStringUtils.toNullString(version)))
                .add(HshStringUtils.buildKeyValue("code", HshStringUtils.toNullString(code)))
                .add(HshStringUtils.buildKeyValue("name", HshStringUtils.toNullString(name)))
                .add(HshStringUtils.buildKeyValue("value", HshStringUtils.toNullString(value)))
                .toString();
    }
}