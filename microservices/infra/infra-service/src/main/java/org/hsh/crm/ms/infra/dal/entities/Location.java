package org.hsh.crm.ms.infra.dal.entities;

import org.hsh.ms.common.dal.entity.BaseIdEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.StringJoiner;

@Entity
@Table(name = "locations")
@Cacheable(value = false)
public class Location extends BaseIdEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "location_item_id", nullable = false)
    private Long locationItemId;

    @Column(name = "short_name", length = 50)
    private String shortName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "network_id", referencedColumnName = "id")
    private Network network;

    public Location() {
        // default
    }

    public Long getLocationItemId() {
        return locationItemId;
    }

    public void setLocationItemId(Long locationItemId) {
        this.locationItemId = locationItemId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
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
        Location other = (Location) object;
        return super.equals(object);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Location.class.getSimpleName() + "[", "]")
                .add("locationItemId=" + locationItemId)
                .add("shortName='" + shortName + "'")
                .add("network=" + network)
                .toString();
    }
}
