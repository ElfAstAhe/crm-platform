package org.hsh.crm.ms.audit.dal.entities;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "security_audit_history")
@Cacheable(false)
public class SecurityAuditHistory extends BaseSecurityAudit implements Serializable {
    private static final long serialVersionUID = 1L;

    public SecurityAuditHistory() {
        // default
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
