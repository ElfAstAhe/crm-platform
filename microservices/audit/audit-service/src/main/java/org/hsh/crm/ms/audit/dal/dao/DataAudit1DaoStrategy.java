package org.hsh.crm.ms.audit.dal.dao;

import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.audit.dal.entities.BaseDataAudit;
import org.hsh.crm.ms.audit.dal.entities.DataAudit1;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

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
    protected CrudDao<DataAudit1, Long> getDao() {
        return dao;
    }

    @Override
    public AuditDaoStrategyKeyEnum getStrategyKey() {
        return AuditDaoStrategyKeyEnum.FIRST;
    }

    @Override
    public Class<? extends BaseDataAudit> getStrategyEntityClass() {
        return getEntityClass();
    }
}
