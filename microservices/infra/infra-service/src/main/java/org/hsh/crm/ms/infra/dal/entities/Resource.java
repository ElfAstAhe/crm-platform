package org.hsh.crm.ms.infra.dal.entities;

import org.hsh.ms.common.dal.entity.BaseIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.StringJoiner;

@Entity
@Table(name = "resources", uniqueConstraints = {@UniqueConstraint(name = "resources_uk", columnNames = {"code", "network_id"})})
@Cacheable(value = false)
public class Resource extends BaseIdEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "code", length = 50, nullable = false)
    private String code;

    @Column(name = "name", length = 512)
    private String name;

    @Column(name = "port")
    private int port;

    @Column(name = "url", length = 1024)
    private String url;

    @Column(name = "description", length = 512)
    private String description;

    public Resource() {
        // default
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

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if(object == null)
            return false;
        if(object == this)
            return true;
        if(this.getClass() != object.getClass())
            return false;
        return super.equals(object);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Resource.class.getSimpleName() + "[", "]")
                .add("code='" + code + "'")
                .add("name='" + name + "'")
                .add("port=" + port)
                .add("url='" + url + "'")
                .add("description='" + description + "'")
                .toString();
    }
}
