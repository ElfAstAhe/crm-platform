package org.hsh.crm.ms.infra.dal.entities;

import org.hsh.ms.common.dal.entity.BaseIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "networks", uniqueConstraints = {@UniqueConstraint(name = "networks_uk", columnNames = {"code"})})
@Cacheable(value = false)
public class Network extends BaseIdEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "code", length = 50, nullable = false)
    private String code;

    @Column(name = "name", length = 100)
    private String name;

    @Version
    private Long version;

    // Ссылка на экземпляр контрагента, микро-сервис contragents
    @Column(name = "provider_id")
    private Long providerId;

    // Ссылка на экземпляр договора, микро-сервис documents
    @Column(name = "contract_id")
    private Long contractId;

    @OneToMany(targetEntity = Device.class, mappedBy = "network", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Device> devices;

    @OneToMany(targetEntity = Location.class, mappedBy = "network", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Location> locations;

    public Network() {
        // default
    }

    public Long getVersion() {
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

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
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
        if(object.getClass() != this.getClass())
            return false;

        return super.equals(object);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Network.class.getSimpleName() + "[", "]")
                .add("code='" + code + "'")
                .add("name='" + name + "'")
                .add("providerId=" + providerId)
                .add("contractId=" + contractId)
                .add("locations=" + locations)
                .toString();
    }
}
