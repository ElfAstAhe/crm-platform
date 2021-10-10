package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.audit.dal.entities.BaseDataAudit;
import org.hsh.crm.ms.audit.dal.entities.DataAudit1;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.time.OffsetDateTime;

@RequestScoped
@Named
@Transactional(Transactional.TxType.REQUIRED)
public class DataAudit1DaoStrategy extends BaseAuditDaoStrategy<BaseDataAudit, DataAudit1, Long> implements DataAuditDaoStrategy {
    @Inject
    private DataAudit1Dao dao;

    public DataAudit1DaoStrategy() {
        super(DataAudit1.class);
    }

    @Override
    public AuditDaoStrategyKeyEnum getStrategyKey() {
        return AuditDaoStrategyKeyEnum.FIRST;
    }

    @Override
    public Class<? extends BaseDataAudit> getStrategyEntityClass() {
        return getEntityClass();
    }

    @Override
    public boolean isEarlyExists(OffsetDateTime markerDate) {
        return dao.getEarlyCount(markerDate) > 0L;
    }

    @Override
    protected CrudDao<DataAudit1, Long> getDao() {
        return dao;
    }
}
