package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.audit.dal.entities.BaseDataAudit;

public interface DataAuditDaoStrategy extends CrudDao<BaseDataAudit, Long> {
    AuditDaoStrategyKeyEnum getStrategyKey();
    Class<? extends BaseDataAudit> getStrategyEntityClass();
}
