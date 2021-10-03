package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.audit.dal.entities.BaseSecurityAudit;

public interface SecurityAuditDaoStrategy extends CrudDao<BaseSecurityAudit, Long> {
    AuditDaoStrategyKeyEnum getStrategyKey();
    Class<? extends BaseSecurityAudit> getStrategyEntityClass();
}
