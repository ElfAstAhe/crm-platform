package org.hsh.crm.ms.users.dto;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonbPropertyOrder(PropertyOrderStrategy.ANY)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "id")
    @JsonbProperty("id")
    private Long id;

    @XmlElement(name = "username")
    @JsonbProperty("username")
    private String username;

    @XmlElement(name = "password")
    @JsonbProperty("password")
    private String password;

    @XmlElement(name = "passwordEncrypted")
    @JsonbProperty("passwordEncrypted")
    private String passwordEncrypted;

    @XmlElement(name = "privateKey")
    @JsonbProperty("privateKey")
    private String privateKey;

    @XmlElement(name = "publicKey")
    @JsonbProperty("publicKey")
    private String publicKey;

    @XmlElement(name = "person")
    @JsonbProperty("person")
    private String person;

    @XmlElement(name = "eMail")
    @JsonbProperty("eMail")
    private String eMail;

    @XmlElement(name = "cellPhone")
    @JsonbProperty("cellPhone")
    private String cellPhone;

    @XmlElementWrapper(name = "roles")
    @XmlElement(name = "role")
    @JsonbProperty("roles")
    private List<Role> roles = new ArrayList<>();

    public User() {
        // default
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
