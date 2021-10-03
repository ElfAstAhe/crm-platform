package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.audit.dal.entities.BaseSecurityAudit;
import org.hsh.crm.ms.audit.dal.entities.SecurityAudit1;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@RequestScoped
@Named
@Transactional(Transactional.TxType.REQUIRED)
public class SecurityAudit1DaoStrategy extends BaseAuditDaoStrategy<BaseSecurityAudit, SecurityAudit1, Long> implements SecurityAuditDaoStrategy {
    @Inject
    private SecurityAudit1Dao dao;

    public SecurityAudit1DaoStrategy() {
        super(SecurityAudit1.class);
    }

    @Override
    protected CrudDao<SecurityAudit1, Long> getDao() {
        return dao;
    }

    @Override
    public AuditDaoStrategyKeyEnum getStrategyKey() {
        return AuditDaoStrategyKeyEnum.FIRST;
    }

    @Override
    public Class<? extends BaseSecurityAudit> getStrategyEntityClass() {
        return getEntityClass();
    }
}
