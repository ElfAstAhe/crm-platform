package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.audit.dal.entities.SecurityAudit2;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

@RequestScoped
@Named
@Transactional(Transactional.TxType.REQUIRED)
public class SecurityAudit2Dao extends BaseAuditDao<SecurityAudit2, Long> implements CrudDao<SecurityAudit2, Long> {
    public SecurityAudit2Dao() {
        super(SecurityAudit2.class);
    }
}
