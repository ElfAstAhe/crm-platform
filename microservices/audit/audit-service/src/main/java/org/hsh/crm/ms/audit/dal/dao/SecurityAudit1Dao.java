package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.audit.dal.entities.SecurityAudit1;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

@RequestScoped
@Named
@Transactional(Transactional.TxType.REQUIRED)
public class SecurityAudit1Dao extends BaseAuditDao<SecurityAudit1, Long> implements CrudDao<SecurityAudit1, Long> {
    public SecurityAudit1Dao() {
        super(SecurityAudit1.class);
    }
}
