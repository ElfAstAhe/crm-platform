package org.hsh.crm.ms.infra.dal.entities;

import org.hsh.ms.common.dal.entity.BaseIdEntity;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "devices")
@Cacheable(value = false)
public class Device extends BaseIdEntity {

    @Column(name = "name", length = 256)
    private String name;

    @Version
    private Long version;

    @Column(name = "ip_v4", length = 50)
    private String ipV4;

    @Column(name = "ip_v6", length = 100)
    private String ipV6;

    @Column(name = "device_kind", length = 50)
    @Enumerated(EnumType.STRING)
    private DeviceKindEnum deviceKind;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
    @JoinColumn(name = "network_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "devices_network_fk"))
    private Network network;

    // ссылка на владельца, микро-сервис контрагентов
    @Column(name = "owner_id")
    private Long ownerId;

    public Device() {
        // default
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getIpV4() {
        return ipV4;
    }

    public void setIpV4(String ipV4) {
        this.ipV4 = ipV4;
    }

    public String getIpV6() {
        return ipV6;
    }

    public void setIpV6(String ipV6) {
        this.ipV6 = ipV6;
    }

    public DeviceKindEnum getDeviceKind() {
        return deviceKind;
    }

    public void setDeviceKind(DeviceKindEnum deviceKind) {
        this.deviceKind = deviceKind;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Device.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
