package org.hsh.crm.ms.audit.dal.entities;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "data_audit_history")
@Cacheable(false)
public class DataAuditHistory extends BaseDataAudit implements Serializable {
    private static final long serialVersionUID = 1L;

    public DataAuditHistory() {
        // default constructor
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
        return super.toString();
    }
}
