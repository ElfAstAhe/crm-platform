package org.hsh.crm.ms.users.dal.entities;

import org.hsh.common.util.HshStringUtils;
import org.hsh.ms.common.dal.entity.BaseIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "users")
@Cacheable(false)
public class User extends BaseIdEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Version
    @Column(name = "version", nullable = false)
    @SuppressWarnings("FieldMayBeFinal")
    private long version;

    @Column(name = "username", length = 100, nullable = false)
    private String username;

//    @Column(name = "password", length = 100)
//    private String password;

    @Column(name = "password_encrypted", length = 200)
    private String passwordEncrypted;

    @Column(name = "private_key", length = 4096)
    private String privateKey;

    @Column(name = "public_key", length = 4096)
    private String publicKey;

//    @Column(name = "person", length = 200)
//    private String person;

    @Column(name = "e_mail", length = 1024)
    private String eMail;

    @Column(name = "cell_phone", length = 50)
    private String cellPhone;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStateEnum state;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
    joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles = new ArrayList<>();

    // default
    public User() {
        super();
        this.version = 0L;
    }

    // full
    public User(Long id,
                String username,
//                String password,
                String passwordEncrypted,
                String privateKey,
                String publicKey,
//                String person,
                String eMail,
                String cellPhone,
                List<Role> roles) {
        super(id);
        this.version = 0L;
        this.username = username;
//        this.password = password;
        this.passwordEncrypted = passwordEncrypted;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
//        this.person = person;
        this.eMail = eMail;
        this.cellPhone = cellPhone;
        this.roles = roles;
    }

    public long getVersion() {
        return version;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordEncrypted() {
        return passwordEncrypted;
    }

    public void setPasswordEncrypted(String passwordEncrypted) {
        this.passwordEncrypted = passwordEncrypted;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public UserStateEnum getState() {
        return state;
    }

    public void setState(UserStateEnum state) {
        this.state = state;
    }

    public boolean isActive() {
        return UserStateEnum.ACTIVE.equals(this.state);
    }

    public boolean isBlocked() {
        return UserStateEnum.BLOCKED.equals(this.state);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;

        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner(HshStringUtils.DELIMITER,
                                HshStringUtils.buildPrefix(this),
                                HshStringUtils.SUFFIX).add(HshStringUtils.buildKeyValue("id", HshStringUtils.toNullString(getId())))
                                                      .add(HshStringUtils.buildKeyValue("version", HshStringUtils.toNullString(version)))
                                                      .add(HshStringUtils.buildKeyValue("username", HshStringUtils.toNullString(username)))
//                                                      .add(HshStringUtils.buildKeyValue("person", HshStringUtils.toNullString(person)))
                                                      .add(HshStringUtils.buildKeyValue("state", state))
                                                      .toString();
    }
}
