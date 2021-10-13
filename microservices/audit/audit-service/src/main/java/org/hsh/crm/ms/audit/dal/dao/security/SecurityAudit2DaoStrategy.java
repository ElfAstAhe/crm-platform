package org.hsh.crm.ms.audit.dal.dao.security;

import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.audit.dal.dao.AuditDaoStrategyKeyEnum;
import org.hsh.crm.ms.audit.dal.dao.base.BaseAuditDaoStrategy;
import org.hsh.crm.ms.audit.dal.entities.BaseSecurityAudit;
import org.hsh.crm.ms.audit.dal.entities.SecurityAudit2;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@RequestScoped
@Named
@Transactional(Transactional.TxType.REQUIRED)
public class SecurityAudit2DaoStrategy extends BaseAuditDaoStrategy<BaseSecurityAudit, SecurityAudit2, Long> implements SecurityAuditDaoStrategy {
    @Inject
    private SecurityAudit2Dao dao;

    public SecurityAudit2DaoStrategy() {
        super(SecurityAudit2.class);
    }
    @Override
    protected CrudDao<SecurityAudit2, Long> getDao() {
        return dao;
    }

    @Override
    public AuditDaoStrategyKeyEnum getStrategyKey() {
        return AuditDaoStrategyKeyEnum.SECOND;
    }

    @Override
    public Class<? extends BaseSecurityAudit> getStrategyEntityClass() {
        return getEntityClass();
    }
}
