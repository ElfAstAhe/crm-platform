package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.audit.dal.entities.BaseDataAudit;
import org.hsh.crm.ms.audit.dal.entities.DataAudit2;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.time.OffsetDateTime;

@RequestScoped
@Named
@Transactional(Transactional.TxType.REQUIRED)
public class DataAudit2DaoStrategy extends BaseAuditDaoStrategy<BaseDataAudit, DataAudit2, Long> implements DataAuditDaoStrategy {
    @Inject
    private DataAudit2Dao dao;

    public DataAudit2DaoStrategy() {
        super(DataAudit2.class);
    }

    @Override
    public AuditDaoStrategyKeyEnum getStrategyKey() {
        return AuditDaoStrategyKeyEnum.SECOND;
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
    protected CrudDao<DataAudit2, Long> getDao() {
        return dao;
    }
}
