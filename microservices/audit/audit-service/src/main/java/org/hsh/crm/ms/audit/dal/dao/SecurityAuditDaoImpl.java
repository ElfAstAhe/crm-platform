package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.crm.ms.audit.dal.entities.SecurityAudit;

import javax.ejb.Stateless;

@Stateless
public class SecurityAuditDaoImpl extends BaseAuditDao<SecurityAudit, Long> implements SecurityAuditDao {
    public SecurityAuditDaoImpl() {
        super(SecurityAudit.class);
    }
}
