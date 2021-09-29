package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.crm.ms.audit.dal.entities.SecurityAudit;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

@RequestScoped
@Named
@Transactional(Transactional.TxType.REQUIRED)
public class SecurityAuditDaoImpl extends BaseAuditDao<SecurityAudit, Long> implements SecurityAuditDao {
    public SecurityAuditDaoImpl() {
        super(SecurityAudit.class);
    }
}
