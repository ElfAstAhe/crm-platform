package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.audit.dal.entities.BaseDataAudit;

import java.time.OffsetDateTime;

public interface DataAuditDaoStrategy extends CrudDao<BaseDataAudit, Long> {
    AuditDaoStrategyKeyEnum getStrategyKey();
    Class<? extends BaseDataAudit> getStrategyEntityClass();
    boolean isEarlyExists(OffsetDateTime markerDate);
}
