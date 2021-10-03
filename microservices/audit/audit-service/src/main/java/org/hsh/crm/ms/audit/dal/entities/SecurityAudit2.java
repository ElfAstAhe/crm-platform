package org.hsh.crm.ms.audit.dal.entities;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "security_audit_2")
@Cacheable(false)
public class SecurityAudit2 extends BaseSecurityAudit implements Serializable {
    private static final long serialVersionUID = 1L;

    public SecurityAudit2() {
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
